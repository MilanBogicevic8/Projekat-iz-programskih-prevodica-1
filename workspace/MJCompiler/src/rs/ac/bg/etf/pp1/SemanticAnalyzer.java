package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.swing.text.StyledEditorKit;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	public static int nVars;
	public static final int Namespace=8;
	
	public static Struct booleanType = new Struct(Struct.Bool);
	public static Struct namespaceType=new Struct(Namespace);
	
	
	boolean errorDetected = false;
	Logger log=Logger.getLogger(getClass());
	
	private HashMap<Struct,String> klase=new HashMap<>();//sve klase u programu su u ovoj mapi
	
	private Struct currType=null;//oznacava trenutni tip promenjljive
	private Struct natklasaTip=null;//natklasa trenutno bradjivane klase
	private String natklasaIme="";//ime natklase trenutno obradjivane klase
	private Obj potklasaObjekat=null;
	private Struct potklasaTip=null;//tip trenutne potklase
	private String potklasaIme="";//ime trenutne potklase
	private String currMethIme="";//ime metoda koji trenutno obradjujemo
	private Obj currMethType=null;//obj metoda koji trenutno obradjujemo
	private Obj nadjacanObj=null;//ako u potklasi redefinesemo neki metod onda ga trenutno ovd cuvamo
	private Integer brojParametara=0;// broj parametara u metodu definisane klase ili trenutnoj globalnoj funkciji, koristimo pri pozivu da vidimo da li se broj i tipovi slazu
	private Boolean returnStmt=false;//da li ima return statement da bi poredili vraceni tip i tip koji se kaze da ce biti vracen
	private List<String> listaStatickihPoljaKlase=new ArrayList<>();
	
	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", booleanType));
		globalneMetode.add("ord");
		globalneMetode.add("chr");
		globalneMetode.add("len");
	}
	
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	/************************ PROGRAM ******************************/
	/**
	 * Program ::= (Program) PROGRAM ProgName:p NamespaceList ConstDeclList LBRACE MethodDeclList RBRACE;

	   ProgName ::= (ProgName) IDENT:progName;
	 */
	
	static Obj promenjljiva1=null;
	static Obj promenjljiva2=null;
	public void visit(ProgName progName) {
		//obj.vrsta
		//naziv labele za IDENT programa sto smo stavili u mjparser
		//tab.type
		progName.obj=Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();
		promenjljiva1=Tab.insert(Obj.Var, "PROM_1_",new Struct(Struct.Int));
		promenjljiva2=Tab.insert(Obj.Var, "PROM_2_",new Struct(Struct.Int));
		
	}
	
	public void visit(Program program) {
		nVars = Tab.currentScope.getnVars();//za globalne promenjlive u generisanju koda
		report_info("Nvars je:"+nVars,program);
		
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	/*********************** TYPE  *************************/
	/**
	 * 
	 * Type ::= (TypeOptDoubleColon) IDENT:Nname COLON COLON IDENT:typeName
		 		|
		 		(TypeBaseNoColon) IDENT:typeName
		 		;
	 */
	
	//za npr kad imamo napespace pa u njemu klasu i van tog namespacea hocemo da je instanciramo
	public void visit(TypeOptDoubleColon type) {
		String tip=type.getNname();
		String elem=type.getTypeName();
		Obj obj=null;
		if(Tab.find(elem)==Tab.noObj) {
			obj=Tab.find("_"+tip+"_"+elem);
		}else {
			obj=Tab.find(elem);
		}
		
		
		if(obj!=Tab.noObj) {
			
			report_info("Tip: "+tip+" je definisan", type);
			type.struct=obj.getType();
			currType=type.struct;
				
		}else {
			report_error("GRESKA:Tip sa name-om: "+tip+", a elem je:"+elem+" nije definisan u nijednom scope-u.", type);
		}
	}
	
	public void visit(TypeBaseNoColon type) {
		String name=type.getTypeName();
		
		Obj obj=null;
		if(Tab.find(name)==Tab.noObj && imeTrenutnogNamespacea!=null) {
			obj=Tab.find("_"+imeTrenutnogNamespacea+"_"+name);
		}else {
			obj=Tab.find(name);
		}
		
		if(obj==Tab.noObj) {
			report_error("GRESKA:Tip sa name-om: "+name+" nije definisan u nijednom scope-u.", type);
		}else {
			if(obj.getKind()==Obj.Type) {
				report_info("Tip: "+name+" je definisan", type);
				type.struct=obj.getType();
				currType=type.struct;
			}else {
				report_error("GRESKA:Postoji naziv: "+name+" u tabeli, ali on ne predstavja tip",type);
			}
		}
	}
	
	/************************* PROMENJLJIVE *****************************/
	/**
	 * ConstType ::= (ConstNum) NUMCONST:val
					  |
					  (ConstChar) CHARCONST:val
					  |
					  (ConstBool) BOOLCONST:val
					  ;
	 */
	//NUMCONST
	
	public void visit(ConstNum number) {
		number.struct=Tab.intType;
	}
	
	//CharConst
	public void visit(ConstChar charC) {
		charC.struct=Tab.charType;
	}
	
	//BoolConst
	public void visit(ConstBool boolC) {
		boolC.struct=booleanType;// ja sam napravio ovaj simbol i dodao u universe, pa da ne trazim kad je vec lokalno sacuvan
	}
	
	/*Konstavne*/
	//Provera valjanosti tipa i vrednosti
	/**
	 * ConstDecl ::= (ConstDecl) CONST Type IDENT:name EQUALS ConstType ConstDeclTail SEMICOLON;
	 */
	public void visit(ConstDecl cnstDecl) {
		//1. provera da li je konstanta vec deklarisana
		String name=cnstDecl.getName();
		Obj obj=Tab.find(name);
		if(obj!=Tab.noObj) {
			//greska, promenjljiva je deklarisan vec
			report_error("GRESKA:Promenjljiva: "+name+" je vec deklarisan!",cnstDecl);
			return;
		}
		
		//2. provera da li su type i dodeljena vrednost kompatiblni(assignable)
		
		Struct type=cnstDecl.getType().struct; //za struct nema geter nego se to dir. dohvata jer je public
		Struct cnst=cnstDecl.getConstType().struct;
		//currType=type;
		
		if(!cnst.assignableTo(type)) {
			report_error("GRESKA:Tip i vrednost su nekompatibilni!", cnstDecl);
			return;
		}
		
		//Sad je sve u redu, ubacujemo konstantu u tabelu simbola
		
		Obj objInsert=null;
		if(imeTrenutnogNamespacea!=null) {
			objInsert=Tab.insert(Obj.Con,"_"+imeTrenutnogNamespacea+"_"+name,type);
		}else {
			objInsert=Tab.insert(Obj.Con,name,type);
		}
		
		report_info("Definisana je konstanta: "+name, cnstDecl);
		
		ConstType ct = cnstDecl.getConstType();
    	
    	if (ct.getClass() == ConstNum.class) {
    		objInsert.setAdr(((ConstNum) ct).getVal());
    	} else if (ct.getClass() == ConstChar.class) {
    		objInsert.setAdr(((ConstChar) ct).getVal());
    	} else if (ct.getClass() == ConstBool.class) {
    		objInsert.setAdr(((ConstBool) ct).getVal() == true ? 1 : 0);
    	}
		
	}
	/**
	 * ConstDeclTail ::= (ConstDeclT)ConstDeclTail COMMA ConstDeclHead
				|
				(ConstDeclH) \/*epsilon*\/
				;
	 * ConstDeclHead ::= (ConstDeclHead) IDENT:name EQUALS ConstType;
	 */
	public void visit(ConstDeclHead cnstDecl) {
		String name=cnstDecl.getName();
		Obj obj=Tab.find(name);
		
		//1. provera da li je konstanta vec deklarisana
		if(obj!=Tab.noObj) {
			//greska, promenjljiva je deklarisan vec
			report_error("GRESKA:Promenjljiva: "+name+" je vec deklarisan!",cnstDecl);
			return;
		}
		
		//2. provera da li su type i dodeljena vrednost kompatiblni(assignable)
		Struct cnstType=cnstDecl.getConstType().struct;
		
		if(!cnstType.assignableTo(currType)) {
			report_error("GRESKA:Tip i vrednost su nekompatibilni!", cnstDecl);
			return;
		}
		
		Obj objInsert=null;
		if(imeTrenutnogNamespacea!=null) {
			objInsert=Tab.insert(Obj.Con,"_"+imeTrenutnogNamespacea+"_"+name,cnstType);
		}else {
			objInsert=Tab.insert(Obj.Con,name,cnstType);
		}
		report_info("Definisana je konstanta: "+name, cnstDecl);
		
		ConstType ct = cnstDecl.getConstType();
    	
    	if (ct.getClass() == ConstNum.class) {
    		objInsert.setAdr(((ConstNum) ct).getVal());
    		//report_info("Definisana je konstanta: "+name+"ADR: "+obj.getAdr(), cnstDecl);
    	} else if (ct.getClass() == ConstChar.class) {
    		objInsert.setAdr(((ConstChar) ct).getVal());
    	} else if (ct.getClass() == ConstBool.class) {
    		objInsert.setAdr(((ConstBool) ct).getVal() == true ? 1 : 0);
    	}
			
	}
	
	/*Vars*/
	
	//globalne
	/**
	 * VarDecl ::= (VarDecl) Type NewVarDecl OptionalVarDeclList SEMICOLON;

	   NewVarDecl ::= (VarDeclIdent) IDENT:name AdditionalSquare;
	   
	   AdditionalSquare ::= (AdditionalS) LSQUARE RSQUARE
					  |
					  (NoAdditionalS) \/*epsilon*\/
					  ;
	 */
	public void visit(VarDecl varDecl) {
		String name=((VarDeclIdent)varDecl.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana u currentScope-u ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Globalna promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",varDecl);
			return;
		}
		
		String tip=((VarDeclIdent)varDecl.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Globalna promenljiva: "+name+" je dodata u tabelu simbola.", varDecl);
			if(imeTrenutnogNamespacea!=null) {
				Tab.insert(Obj.Var, "_"+imeTrenutnogNamespacea+"_"+name, currType);
			}else {
				Tab.insert(Obj.Var, name, currType);
			}
			
		}else if("[]".equals(tip)) {
			report_info("Globalna promenjliva tipa niz: "+name+" je dodata u tableu simbola.",varDecl);
			if(imeTrenutnogNamespacea!=null) {
				Tab.insert(Obj.Var, "_"+imeTrenutnogNamespacea+"_"+name, new Struct(Struct.Array,currType));
			}else {
				Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
			}
			
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", varDecl);
		}
		
		return;
	}
	
	public void visit(AdditionalS addit) {
		addit.obj=new Obj(-1, "[]", null);
	}
	public void visit(NoAdditionalS noAddit) {
		noAddit.obj=new Obj(-1,"epsilon",null);
	}
	
	/**
	 * 
	 * OptionalVarDeclList ::= (OptionalVarList) OptionalVarDeclList COMMA NewVarDecl
	*					|
	*					(NoOptionalVarList) \/*epsilon*\/
	*					
	 * 
	 */
	///moglo i elegantnije da se koristi odmah NewVarDecl
	public void visit(OptionalVarList ovarDecl) {
		String name=((VarDeclIdent)ovarDecl.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Globalna promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",ovarDecl);
			return;
		}
		
		String tip=((VarDeclIdent)ovarDecl.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Globalna promenljiva: "+name+" je dodata u tabelu simbola.", ovarDecl);
			if(imeTrenutnogNamespacea!=null) {
				Tab.insert(Obj.Var, "_"+imeTrenutnogNamespacea+"_"+name, currType);
			}else {
				Tab.insert(Obj.Var, name, currType);
			}
		}else if("[]".equals(tip)) {
			report_info("Globalna promenjliva tipa niz: "+name+" je dodata u tableu simbola.",ovarDecl);
			if(imeTrenutnogNamespacea!=null) {
				Tab.insert(Obj.Var, "_"+imeTrenutnogNamespacea+"_"+name, new Struct(Struct.Array,currType));
			}else {
				Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
			}
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", ovarDecl);
		}
		
		return;
	}
	
	//class
	/**
	 * 
	 * ClassDecl ::= (ClassDeclaration) ClassDeclBegin LBRACE ClassBody RBRACE;

		ClassDeclBegin ::= (ClassDeclBegin) CLASS IDENT:name AdditionalExtends;
		
		ClassBody ::= (ClassMatch) ClassMat
					  |
					  (ClassUnmatch) ClassUnmat
					  ;
		ClassMat ::= (ClassM) OptionalStatic OptionalVarDecl AdditionalMethodClassDecl;
		ClassUnmat ::= (ClassU) OptionalStatic OptionalStaticInitializer OptionalVarDecl AdditionalMethodClassDecl;
		
					   
		AdditionalExtends ::= (AdditionalExtend) EXTENDS Type:type
							  |
							  (NoAdditionalExtend) \/*epsilon*\/
							  ;
		OptionalStatic ::= (OptionalStat) OptionalStatic STATIC VarDeclStatic
						   |
						   (NoOptionalStatic) \/*epsilon*\/
						   ;
		OptionalVarDecl ::= (OptVarDecl) OptionalVarDecl VarDeclClass
							|
							(NoOptVarDecl) \/*epsilon*\/
							;
		
		AdditionalMethodClassDecl ::= (AddClassMeth) LBRACE MethodDeclList RBRACE
									  |
									  (NoAddClassMeth) \/* epsilon*\/
									  ;
	 * 
	 * VarDeclClass ::= (VarDeclClass) Type NewVarDecl OptionalVarDeclListClass SEMICOLON;
		OptionalVarDeclListClass ::= (OptionalVarListClass) OptionalVarDeclListClass COMMA NewVarDecl
								|
								(NoOptionalVarListClass) \/*epsilon*\/
								;
	 */
	
	
	public void visit(VarDeclClass varClass) {
		String name=((VarDeclIdent)varClass.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",varClass);
			return;
		}
		
		String tip=((VarDeclIdent)varClass.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", varClass);
			Tab.insert(Obj.Fld, name, currType);
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",varClass);
			Tab.insert(Obj.Fld, name, new Struct(Struct.Array,currType));
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", varClass);
		}
		
		return;
	}
	
	public void visit(OptionalVarListClass ovarClass) {
		String name=((VarDeclIdent)ovarClass.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",ovarClass);
			return;
		}
		
		String tip=((VarDeclIdent)ovarClass.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", ovarClass);
			Tab.insert(Obj.Fld, name, currType);
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",ovarClass);
			Tab.insert(Obj.Fld, name, new Struct(Struct.Array,currType));
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", ovarClass);
		}
		
		return;
	}
	
	
	
	//lokalne promenjljive u metodima
	/**
	 * 
	 * VarDeclMeth ::= (VarDeclMeth) Type NewVarDecl OptionalVarDeclListMeth SEMICOLON;
	   OptionalVarDeclListMeth ::= (OptionalVarListMeth) OptionalVarDeclListMeth COMMA NewVarDecl
							|
							(NoOptionalVarListMeth) \/*epsilon*\/
							;
	 * 
	 */
	
	public void visit(VarDeclMeth varMeth) {
		String name=((VarDeclIdent)varMeth.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",varMeth);
			return;
		}
		
		String tip=((VarDeclIdent)varMeth.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", varMeth);
			Tab.insert(Obj.Var, name, currType);
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",varMeth);
			Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", varMeth);
		}
		
		return;
	}
	
	public void visit(OptionalVarListMeth ovarMeth) {
		String name=((VarDeclIdent)ovarMeth.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",ovarMeth);
			return;
		}
		
		String tip=((VarDeclIdent)ovarMeth.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", ovarMeth);
			Tab.insert(Obj.Var, name, currType);
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",ovarMeth);
			Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", ovarMeth);
		}
		
		return;
	}
	/**
	 * 
	 * VarDeclStatic ::= (VarDeclStatic) Type NewVarDecl OptionalVarDeclListStatic SEMICOLON;
	   OptionalVarDeclListStatic ::= (OptionalVarListStatic) OptionalVarDeclListStatic COMMA NewVarDecl
						|
						(NoOptionalVarListStatic) \/*epsilon*\/
						;
	 */
	public void visit(VarDeclStatic varMeth) {
		String name=((VarDeclIdent)varMeth.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",varMeth);
			return;
		}
		
		String tip=((VarDeclIdent)varMeth.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", varMeth);
			Obj o=Tab.insert(Obj.Var, name, currType);
			report_info("lista:"+o.getName(), varMeth);
			listaStatickihPoljaKlase.add(o.getName());
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",varMeth);
			Obj o=Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
			report_info("lista:"+o.getName(), varMeth);
			listaStatickihPoljaKlase.add(o.getName());
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", varMeth);
		}
		
		return;
	}
	
	public void visit(OptionalVarListStatic ovarMeth) {
		String name=((VarDeclIdent)ovarMeth.getNewVarDecl()).getName();
		Obj obj=Tab.find(name);
		//ako se promenjljiva sa istim imenom nalazi vec deklarisana ovde, onda je to greska
		if(obj!=Tab.noObj && Tab.currentScope.findSymbol(name)!=null) {
			report_error("GRESKA:Promenljiva sa nazivom: "+name+" je vec deklarisana u currentScope-u.",ovarMeth);
			return;
		}
		
		String tip=((VarDeclIdent)ovarMeth.getNewVarDecl()).getAdditionalSquare().obj.getName();
		if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", ovarMeth);
			Obj o=Tab.insert(Obj.Var, name, currType);
			report_info("lista:"+o.getName(), ovarMeth);
			listaStatickihPoljaKlase.add(o.getName());
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",ovarMeth);
			Obj o=Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
			report_info("lista:"+o.getName(), ovarMeth);
			listaStatickihPoljaKlase.add(o.getName());
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", ovarMeth);
		}
		
		return;
	}
	//ostalo je staticke metoe(--netacno nema statickih metoda---) i metode namespace da se implementiraju(za namespace treba dodatno ispraviti mjparser)
	
	/*********************CLASS DECL ********************************/
	/**
	 * ClassDecl ::= (ClassDeclaration) ClassDeclBegin LBRACE ClassBody RBRACE;
	 * AdditionalExtends ::= (AdditionalExtend) EXTENDS Type:type
					  |
					  (NoAdditionalExtend) \/*epsilon*\/
					  ;
		ClassDeclBegin ::= (ClassDeclBegin) CLASS IDENT:name AdditionalExtends;
	 */
	
	public void visit(ClassDeclBegin classDecl) {
		String name=classDecl.getName();
		
		Obj classExists=Tab.find(name);
		
		if(classExists!=Tab.noObj) {
			report_error("Vec je deklarisana klasa sa imenom: "+name,classDecl);
			return;
		}
		
		natklasaTip=classDecl.getAdditionalExtends().struct;
		
		natklasaIme=klase.getOrDefault(natklasaTip, null);
		potklasaIme=name;
		//potklasaTip=new Struct(Struct.Class,natklasaTip);-zamena
		potklasaTip=new Struct(Struct.Class);
		potklasaTip.setElementType(natklasaTip);
		report_info("Dodata KLASA: "+name+" u tabelu simbola", classDecl);
		if(imeTrenutnogNamespacea!=null) {
			potklasaObjekat=classDecl.obj=Tab.insert(Obj.Type,"_"+imeTrenutnogNamespacea+"_"+ name, potklasaTip);
		}else {
			potklasaObjekat=classDecl.obj=Tab.insert(Obj.Type, name, potklasaTip);
		}
		
		Tab.openScope();
		
		if(natklasaTip!=null) {
			//ako postoji natklasa
			String extensionType=((TypeBaseNoColon)((AdditionalExtend)classDecl.getAdditionalExtends()).getType()).getTypeName();
			if(imeTrenutnogNamespacea!=null) {
				extensionType="_"+imeTrenutnogNamespacea+"_"+extensionType;
			}
			Obj extType=Tab.find(extensionType);
			
			if(extType==Tab.noObj) {
				//ako ne postoji zadati indentifikator, to znaci da tip jos uvek nije definisan
				report_error("Ne postoji natklasa sa imenom: "+ name, classDecl);
				return;
			}else {
				//pronasli smo tip koji se prosiruje,ali da li je on i klasa
				if(klase.getOrDefault(extType.getType(), null)==null) {
					report_error("Ne postoji zadata natklasa,ona je neki drugi tip, ane klasa",classDecl);
					return;
				}
			}		
		}
		
		//ovde treba dodati i tabelu virtuelnih funkcija kao 0 polje klas
		Tab.insert(Obj.Fld, "VFT", Tab.intType);
		
		if(natklasaTip!=null) {
			for(Obj obj:natklasaTip.getMembers()) {
				if(obj.getName().equals("VFT")) {
					continue;//this parametar stavljamo svoj a ne tudj,ZAPRAVO SAM MISLIO NA vft
				}
				if(obj.getKind()==Obj.Fld) {
					Tab.insert(Obj.Fld, obj.getName(), obj.getType());
				}else if(obj.getKind()==Obj.Meth) {
					//!!!!!!!!!!!!!!!treba jos neki uslovi kasnije da se dodaju ovde !!!!!!!!!!!
					//Tab.insert(Obj.Meth, obj.getName(), obj.getType());
					Tab.currentScope().addToLocals(obj);
				}
			}
		}
		
		
	}
	
	public void visit(ClassDeclaration classDecl) {
		
		//!!!!!!!!!!vrv ovde treba dodati provere i ubacivanje statickih polja!!!!!!
		klase.put(potklasaTip, potklasaIme);
		Tab.chainLocalSymbols(potklasaTip);
		Tab.closeScope();
		potklasaIme="";
		natklasaIme="";
		potklasaTip=null;
		potklasaObjekat=null;
		natklasaTip=null;
		listaStatickihPoljaKlase.clear();
	    
	}
	
	
	public void visit(AdditionalExtend ae) {
		ae.struct=ae.getType().struct;
	}
	
	public void visit(NoAdditionalExtend nae) {
		nae.struct=null;
		//nae.struct=Tab.noType;//suptilnije
	}
	
	/**************************METODE*******************/
	/**
	 * 
	 * MethodDecl ::= (MethodDecl) TypeOrVoid LPAREN AdditionalFormPars RPAREN OptionalMethVarDeclL LBRACE StatementList RBRACE;

		TypeOrVoid ::= (TypeOpt) Type IDENT:name
						|
						(VoidOpt) VOID IDENT:name
						;
		AdditionalFormPars ::= (AddFormPars) FormPars
								|
								(NoAddFormPars) \/* epsilon *\/
								;
		
		MethodDeclList ::= (MethDeclL) MethodDeclList MethodDecl
							|
							(NoMethDeclL) \/*epsilon*\/
							;
		
	 */
	
	
	//globalna deklaracija Metoda
	public void visit(TypeOpt type) {
		
		String name=currMethIme=type.getName();
		//ukoliko je u currentScope-u vec definisana metoda sa tim imenom,a ovo nije potkalsa(radi se o globalnom metodu) GRESKA
		if(Tab.currentScope().findSymbol(name)!=null && potklasaTip==null) {
			report_error("Metoda: "+name+" je vec deklarisana.",type);
			type.obj=Tab.noObj;currMethType=Tab.noObj;
			//Tab.openScope();!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Mozda reba a mozda je greska , proveri kasnije
			return;
		}else if(Tab.currentScope().findSymbol(name)!=null && potklasaTip!=null) {
			//u suprotnom ako je ovo potklasa a metod redefinisan dodajemo redefinisan metod,a brisemo nasledjen
			nadjacanObj=Tab.currentScope().findSymbol(name);
			Tab.currentScope().getLocals().deleteKey(name);
		}
		
		if(imeTrenutnogNamespacea!=null && potklasaTip==null) {
			currMethType=Tab.insert(Obj.Meth,"_"+imeTrenutnogNamespacea+"_"+ name, type.getType().struct);
		}else {
			currMethType=Tab.insert(Obj.Meth, name, type.getType().struct);
		}
		
		type.obj=currMethType;
		Tab.openScope();
		
		if(potklasaTip!=null) {
			//ako je metod klase moramo da dodamo this
			brojParametara++;
			report_info("Dodata metoda klase: "+name+" klase "+potklasaIme, type);
			Tab.insert(Obj.Var, "this", potklasaTip);
			return;
		}//else
		report_info("Dodata metoda koja je globalna", type);
		
	}
	
	public void visit(VoidOpt type) {
		String name=currMethIme=type.getName();
		//ukoliko je u currentScope-u vec definisana metoda sa tim imenom,a ovo nije potkalsa GRESKA
		if(Tab.currentScope().findSymbol(name)!=null && potklasaTip==null) {
			report_error("Metoda: "+name+" je vec deklarisana.",type);
			currMethType=Tab.noObj;type.obj=Tab.noObj;
			Tab.openScope();
			return;
		}else if(Tab.currentScope().findSymbol(name)!=null && potklasaTip!=null) {
			//u suprotnom ako je ovo potklasa a metod redefinisan dodajemo redefinisan metod,a brisemo nasledjen
			nadjacanObj=Tab.currentScope().findSymbol(name);
			Tab.currentScope().getLocals().deleteKey(name);
		}
		
		if(imeTrenutnogNamespacea!=null && potklasaTip==null) {
			currMethType=Tab.insert(Obj.Meth,"_"+imeTrenutnogNamespacea+"_"+ name, Tab.noType);
		}else {
			currMethType=Tab.insert(Obj.Meth, name, Tab.noType);
		}
		
		type.obj=currMethType;
		Tab.openScope();
		
		if(potklasaTip!=null) {
			//ako je metod klase moramo da dodamo this
			brojParametara++;
			report_info("Dodata metoda klase: "+name+" klase "+potklasaIme, type);
			Tab.insert(Obj.Var, "this", potklasaTip);
			return;
		}//else
		report_info("Dodata metoda koja je globalna", type);
	}
	
	//globalna metoda--kraj deklaracije
	List<String> globalneMetode=new ArrayList<String>();
	public void visit(MethodDecl methodDecl) {
		methodDecl.obj=currMethType;
		//provera da li main ima argumente
		if("main".equals(currMethIme) && brojParametara>0) {
			report_error("Funkcija main ne sme da ima argumente", methodDecl);
		}
		//otkomentarisi kad dodas proveru za return!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		//provera da li funkcija vraca nesto ako je tip razlicit od void
		if(currMethType.getType()!=Tab.noType && !returnStmt ) {
			report_error("Funkcijiji: "+currMethIme+" fali return", methodDecl);
		}
		if(imeTrenutnogNamespacea!=null && potklasaTip==null) {
			globalneMetode.add("_"+imeTrenutnogNamespacea+"_"+currMethIme);
		}else {
			globalneMetode.add(currMethIme);
		}
		
		//report_info("-------||||||||||||||Broj parametara je:"+brojParametara+" u metodu:"+((VoidOpt)methodDecl.getTypeOrVoid()).getName(),methodDecl);
		currMethType.setLevel(brojParametara);
		brojParametara=0;
		Tab.chainLocalSymbols(currMethType);
		Tab.closeScope();
		
		currMethIme=null;
		currMethType=null;
		nadjacanObj=null;
		returnStmt=null;
		
		
	}
	
	//klasne metode
		public void visit(MethodDeclClass mc) {
			mc.obj=currMethType;
			//provera da li main ima argumente
			if("main".equals(currMethIme) && brojParametara>0) {
				report_error("Funkcija main ne sme da ima argumente", mc);
			}
			//otkomentarisi kad dodas proveru za return!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			//provera da li funkcija vraca nesto ako je tip razlicit od void
			if(currMethType.getType()!=Tab.noType && !returnStmt ) {
				report_error("Funkcijiji: "+currMethIme+" fali return", mc);
			}
			
			//report_info("-------||||||||||||||Broj parametara je:"+brojParametara+" u metodu:"+((VoidOpt)methodDecl.getTypeOrVoid()).getName(),methodDecl);
			currMethType.setLevel(brojParametara);
			brojParametara=0;
			Tab.chainLocalSymbols(currMethType);
			Tab.closeScope();
			
			currMethIme=null;
			currMethType=null;
			nadjacanObj=null;
			returnStmt=null;
		}
		
	//deklaracija Metoda
	
	
	
	
	
	/************************Statements***************/
	//returnStmt
	//Matched = (StmtReturn) RETURN AdditionalExpr SEMICOLON
	//AdditionalExpr ::= (AddExpr) Expr
	//			|
	//		   (NoAddExpr) /*epsilon*/
	//		    ;
	public void visit(StmtReturn ret) {
		//ako je return van metode ili funkcije
		if(currMethType==Tab.noObj || currMethType==null) {
			report_error("GRESKA:Return je van metoda ili funkcije", ret);
			ret.struct=Tab.noType;
			return;
		}
		AdditionalExpr a=ret.getAdditionalExpr();
		//1. provera da li je povratni tip funcije kompatibilan sa onim sto je receno da ce se vratiti
		
		if(a.getClass()==AddExpr.class) {
			//ako je povratni tip null a mi vracamo nesto
			if(currMethType.getType()==Tab.noType) {
				report_error("GRESKA: Metoda vraca nesto a povratni tip je void", a);
				ret.struct=Tab.noType;
				returnStmt=true;
				return;
			}else if(currMethType.getType()!=((AddExpr)a).getExpr().struct) {
				report_error("GRESKA: Retun vraca tip koji nije postavljen u deklaraciji", a);
				ret.struct=Tab.noType;
				returnStmt=true;
				return;
			}else {
				returnStmt=true;
				ret.struct=((AddExpr)a).getExpr().struct;
			}
			
		}else {
			ret.struct=Tab.noType;
			returnStmt=true;
			//provera jos da li je metoda voidposto ne vracamo nista
			if(currMethType.getType()!=Tab.noType) {
				report_error("GRESKA:Metoda nije void a ne vracamo nista", a);
				return;
			}
		}
		report_info("Obidjen return stmt",ret);
	}
	/**
	 * 
	 *Matched ::= ....(StmtReturn) RETURN AdditionalExpr SEMICOLON
	 * AdditionalExpr ::= (AddExpr) Expr
					|
				   (NoAddExpr) \/*epsilon*\/
				    ;
	 */
	public void visit(AddExpr ret) {
		//prvo se proverava da li se return statement koji vraca nesto nalazi uopste unutar funkcije
		//ili da li je povratni tip void, ako je nesto od ta dva tacno onda je to greska
		if(currMethType==null || currMethType==Tab.noObj) {
			report_error("Izraz return nije u metodi ili funkciji, vec izvan njih", ret);
			ret.struct=Tab.noType;
			return;
		}
		
		returnStmt=true;
		
		//greska ako je void povratna vrednost a postoji return
		if(currMethType.getType()==Tab.noType) {
			report_error("Return, koji vraca neku vrednost, u void funkciji ili metodi", ret);
			ret.struct=Tab.noType;
			return;
		}
		
		/****DOPUNI KASNIJE**///dopunjeno ali treba testirati
		//provera da li return vraca
		if(currMethType.getType()!=ret.getExpr().struct) {
			report_error("Pogresan tip se vraca, funkcija ima drugaciju povratnu vrednost", ret);
			ret.struct=Tab.noType;
			return;
		}
		
		ret.struct=ret.getExpr().struct;
		report_info("Naisao na RETURN",ret);
		return;
	}
	
	public void visit(NoAddExpr noret) {
		if(currMethType==null || currMethType==Tab.noObj) {
			report_error("Return nije u metodi ili funkciji, vec izvan njih", noret);
			noret.struct=Tab.noType;
			return;
		}
		
		returnStmt=false;
		noret.struct=Tab.noType;
		
		if(currMethType.getType()!=Tab.noType) {
			//povratna vrednost ne postoji, a fukcije/metode nije void
			report_error("Prazan return, za non-void tip", noret);
			noret.struct=Tab.noType;
			return;
		}
		report_info("Prazna return naredba", noret);
		return;
	}
	
	//*ovde treba nastaviti ostale statemente
	/**
	 * Matched ::= (DesStm) DesignatorStatement SEMICOLON
			|
			(IfElseMatched) IF IfBegin Condition IfEpsilon RPAREN Matched ELSE Matched
			|
			(StmtBreak) BREAK SEMICOLON
			|
			(StmtContinue) CONTINUE SEMICOLON
			|
			(StmtReturn) RETURN AdditionalExpr SEMICOLON
			|
			(StmtRead) READ LPAREN Designator RPAREN SEMICOLON
			|
			(StmtPrint) PRINT LPAREN Expr AdditionalPrintNum RPAREN SEMICOLON
			|
			(StmtFor) ForBegin Matched ForEnd
			|
			(StmtFor2) ForBegin Unmatched ForEnd
			|
			(StmtStmt) LBRACE StatementList RBRACE
			;
	
		ForBegin ::= (ForBegin)	FOR LPAREN AdditionalDesignatoStmtList SEMICOLON AdditionalCondFact SEMICOLON AdditionalDesignatoStmtList RPAREN;
		ForEnd ::= (ForEnd) \/*epsilon*\/;

	 */
	/*****************FOR*********************/
	//boolean uPetlji=false;
	Integer uPetlji=0;
	public void visit(ForBegin fb) {
		uPetlji+=1;
	}
	
	public void visit(ForEnd fe) {
		uPetlji-=1;
	}
	
	/******************BREAK*****************/
	public void visit(StmtBreak sb) {
		if(uPetlji<=0) {
			report_error("GRESKA: Niste u petlji a koristite break", sb);
			return;
		}
		report_info("Naisli smo na break tokom semanticke analize",sb);
	}
	
	/****************CONTINUE****************/
	public void visit(StmtContinue sc) {
		if(uPetlji<=0) {
			report_error("GRESKA: Niste u petlji a koristite continue", sc);
			return;
		}
		report_info("Naisli smo na continue u petlji tokom semanticke analize",sc);
	}
	/*******************READ****************/
	/**
	 * 
	 */
	public void visit(StmtRead sr) {
		Obj objDesignator=sr.getDesignator().obj;
		Struct structDesignator=objDesignator.getType();
		
		//1. provera da li je designator bool,int ili char
		if(structDesignator!=Tab.intType && structDesignator!=Tab.charType && structDesignator!=booleanType) {
			report_error("GRESKA: Tip designatora nije ni char ni bool ni int",sr);
			return;
		}
		//provera da li je designator polje klase element niza ili promenjljiva
		if(objDesignator.getKind()!=Obj.Elem && objDesignator.getKind()!=Obj.Fld && objDesignator.getKind()!=Obj.Var) {
			report_error("GRESKA: Polje designator nije ni element niza, ni element klase, ni varijabla", sr);
			return;
		}
	}
	
	/******************PRINT****************/
	/**
	 * Statement = "print" "(" Expr ["," numConst] ")" ";".
			Expr mora biti tipa int, char ili bool.
	 */
	public void visit(StmtPrint p) {
		Struct structExpr=p.getExpr().struct;
		if(structExpr!=Tab.intType && structExpr!=Tab.charType && structExpr!=booleanType) {
			report_error("GRESKA:Print statement sadrzi expr koji nije ni bool, ni int, ni char"+" vec:"+structExpr.getKind(), p);
			return;
		}
		return;
	}
	/******************TERM******************/
	
	/**
	 * 
	 * Term ::= (Term) Factor OptionalTermMulop;

		OptionalTermMulop ::= (OptTermMul) OptionalTermMulop Mulop Factor
							  |
							  (NoOptTermMul) \/*epsilon*\/
							  ;
	 */
	
	int listTerm=0;
	public void visit(NoOptTermMul noTerm) {
		noTerm.struct=Tab.intType;
	}
	
	public void visit(OptTermMul optTerm) {
		Struct optTermMulOp=optTerm.getOptionalTermMulop().struct;
		Struct factor=optTerm.getFactor().struct;
		listTerm++;
		if(optTermMulOp!=factor || factor!=Tab.intType) {
			report_error("GRESKA:Oba operanda izraza moraju biti tipa int, a prvi optTermMul je tipa "+optTermMulOp.getKind()+" a drugi je tipa "+factor.getKind(), optTerm);
			optTerm.struct=Tab.noType;//suptilan nacin za reci null
		}else {
			optTerm.struct=Tab.intType;
		}
	}
	//ovde negde je greska factor je char a opttermmulop je int
	public void visit(Term term) {
		String mess="";
		Struct factor=term.getFactor().struct;
		Struct optTerMulOp=term.getOptionalTermMulop().struct;
		mess+="Factor je tipa:"+factor.getKind()+" naziv";
		report_info("#######################"+mess, term);
		if(listTerm==0) {
			term.struct=factor;
			report_info("Tip prvog je:"+factor.getKind()+" a drugog je:"+optTerMulOp.getKind(),term);
			return;// ako nema sabiranja, mnozenja...,prvi operand moze biti bilo kog tipa
		}else {
			listTerm=0;
		}
		
		if(factor!=optTerMulOp || factor!=Tab.intType) {
			report_error("GRESKA:Oba operanda izraza moraju biti tipa int, a prvi Factor je tipa "+factor.getKind()+" a drugi optTermMulOp je tipa"+optTerMulOp.getKind(), term);
			term.struct=Tab.noType;
		}else {
			term.struct=Tab.intType;
		}
	}
	
	/***********************EXPR**************************/
	
	/**
	 * Expr ::= (Expr) AdditionalMinus Term OptionalAddopTerm;

		AdditionalMinus ::= (AdditMinus) MINUS
							|
							(NoAdditMinus) \/*epsilon*\/
							;
		OptionalAddopTerm ::= (OptAddTerm) OptionalAddopTerm Addop Term
							  |
							  (NoOptAddTerm) \/*epsilon*\/
							  ;

	 */
	
	boolean negativno=false,pozitivno=false;
	int listaExpr=0;
	public void visit(OptAddTerm optAddTerm) {
		Struct addTerm=optAddTerm.getOptionalAddopTerm().struct;
		Struct term=optAddTerm.getTerm().struct;
		listaExpr+=1;
		if(addTerm!=term || term!=Tab.intType) {
			report_error("GRESKA:Oba operanda izraza moraju biti tipa int, a oni su: "+term.getKind(), optAddTerm);
			optAddTerm.struct=Tab.noType;
		}else {
			optAddTerm.struct=Tab.intType;
		}
	}
	
	public void visit(NoOptAddTerm noOptAddTerm) {
		noOptAddTerm.struct=Tab.intType;
	}
	
	public void visit(AdditMinus minus) {
		negativno=true;
	}
	
	public void visit(Expr expr) {
		String msg="";
		Struct term=expr.getTerm().struct;
		Struct optAddopTerm=expr.getOptionalAddopTerm().struct;
		msg+="Tip izraza je:"+term.getKind();
		report_info("$$$$$$$$$$$$$$$$$$$"+msg, expr);
		if(listaExpr==0) {
			expr.struct=term;
			return;//ako nema drugog operanda prvi izraz moze biti bilo kog tipa
		}else {
			listaExpr=0;
		}
		
		if(term!=optAddopTerm || term!=Tab.intType) {
			report_error("GRESKA:Oba operanda izraza moraju biti tipa int, a oni su: "+term.getKind(), expr);
			expr.struct=Tab.noType;
		}else {
			expr.struct=Tab.intType;
		}
	}
	
	/****************ASSIGNOP| MULOP | RELOP | ADDOP ************************/
	/**
	 * Assignop ::= (Assignop) EQUALS;	
	 * Mulop ::= (MulopMul) MUL //*
		   |
		   (MulopDiv) DIV// /
		   |
		   (MulopPercent) PERCENT // %
		   ;
		   
		Relop ::= (RelEqEq) EQUALS_EQUALS // ==
		  |
		  (RelNotEq) NOT_EQUALS //!=
		  |
		  (RelGrt) GT // >
		  |
		  (RelGrtEq) GTE // >=
		  |
		  (RelLt) LT // <
		  |
		  (RelLtEq) LTE  // <=
		  ;
		 Addop ::= (AddOpPlus) PLUS // +
		  |
		  (AddOpMinus) MINUS // -
		  ;
	 * @return
	 */
	public String currOperator=null;
	
	//Asignop
	public void visit(Assignop a) {
		currOperator="=";
	}
	
	//Mulop
	public void visit(MulopMul m) {
		currOperator="*";
	}
	
	public void visit(MulopDiv d) {
		currOperator="/";
	}
	
	public void visit(MulopPercent p) {
		currOperator="%";
	}
	
	//Addop
	public void visit(AddOpPlus a) {
		currOperator="+";
	}
	
	public void visit(AddOpMinus m) {
		currOperator="-";
	}
	
	//Relop
	public void visit(RelEqEq eq) {
		currOperator="==";
	}
	public void visit(RelNotEq neq) {
		currOperator="!=";
	}
	public void visit(RelGrt g) {
		currOperator=">";
	}
	public void visit(RelGrtEq gr) {
		currOperator=">=";
	}
	public void visit(RelLt l) {
		currOperator="<";
	}
	public void visit(RelLtEq lt) {
		currOperator="<=";
	}
	
	/*********************FACTOR*******************/
	
	/**
	 *  FactorDesignator ::= (FactorDesignator) Designator;
		Factor ::= (FactorDesign) FactorDesignator AdditionalParentheses
					|
					(FactorIdent) Designator
					|
					(FactorNum) NUMCONST
					|
					(FactorChar) CHARCONST
					|
					(FactorBool) BOOLCONST
					|
					(FactorNew) NEW Type OrExprActPars
					|
					(FactorExpr) LPAREN Expr RPAREN
					;
			
		AdditionalParentheses ::= (AddParen) LPAREN AdditionalActParOp RPAREN
							|
							(NoAddParen) \/*epsilon*\/
							;
		AdditionalActParOp ::= (AddActPar2) ActPars
					   |
					   (NoAddActPar2) \/*epsilon*\/
					   ;
	    OrExprActPars ::= (OrExprop) LSQUARE Expr RSQUARE
				   |
				   (OrActPar) LPAREN AdditionalActPars RPAREN
				   ;
	 * @return
	 */
	
	//naknadno dodato -------------!!!!!!!!!!!!!!!!!!!!!!!!!!!PROVERI
	public void visit(FactorIdent fi) {
		fi.struct=fi.getDesignator().obj.getType();
	}
	//stakMetoda ce da sadrzi objekte u stablu koji su metode 
	Stack<Obj> stekMetoda=new Stack<>();
	//a stek argumenata ce da sadrzi argumente koji se koriste pri pozivu tih metoda
	//moglo je sve i lokalno da se cuva posto nece se pozvati naredna metoda dok se ne zavrsi
	//prethodna, ali je ovako bolje zbog prosirivosti
	Stack<List<Struct>> stekArgumenata=new Stack<>();
	
	
	public void visit(FactorDesignator fd) {
		
		stekMetoda.push(fd.getDesignator().obj);
		//za sada cemo samo postaviti precagu da bi kasnije umesto nje bili argumenti
		stekArgumenata.push(new ArrayList<Struct>());
	}
	
	//factor se koristi pri racunu npr: a=b+c+f(1,2)
	
	public void visit(FactorDesign fd) {//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!111Pogledaj kasnije
		/**
		//if(fd.getFactorDesignator().getDesignator().obj==null) return; //!!!!TEMP DOK NE IMPLEMENTIRAM DESIGNATOR!!!!
		Integer type=fd.getFactorDesignator().getDesignator().obj.getKind();
		//ako nije poziv funkcije ili broj onda je to greska
		if(type!=Obj.Meth && type!=Obj.Var && type!=Obj.Con && type!=Obj.Elem && type!=Obj.Fld) {
			report_error("GRESKA:Zove se metoda koja nije deklarisana(designator je ne prepoznaje)",fd);
			fd.struct=Tab.noType;//elegantan nacin a reci null
		}else {
			report_info("Prepoznata metoda klase ili funkcija", fd);
			//vracamo povratnu vrednost funkcije da bi se na visi nivoima dobro sabiraju vrednosti
			fd.struct=fd.getFactorDesignator().getDesignator().obj.getType();
		}
		*/
		fd.struct=fd.getFactorDesignator().getDesignator().obj.getType();
	}
	/**
	 * cond fact
	 * 
	 * CondFact ::= (CondFact) Expr AdditionalRelopExpr;

		AdditionalRelopExpr ::= (AddRelopExpr) Relop Expr
								|
								(NoAddRelopExpr) \/*epsilon*\/
								;
		AdditionalCondFact ::= (AddCondFact) CondFact
								|
								(NoAddCondFact) \/*epsilon*\/
								;
	 */
	
	public void visit(FactorNew fn) {
		OrExprActPars orActPars=fn.getOrExprActPars();
		if(orActPars.getClass()==OrExprop.class) {
			if(((OrExprop)fn.getOrExprActPars()).getExpr().struct!=Tab.intType) {
				report_error("GRESKA:Operator new mora imati int tip za generisanje niza npr. new int[8]", orActPars);
				fn.struct=Tab.noType;
			}else {
				report_info("Kreiran je niz objekata tipa:"+fn.getType(), orActPars);
				fn.struct=new Struct(Struct.Array,fn.getType().struct);
			}
		}else if(orActPars.getClass()==OrActPar.class) {
			Integer klasa= fn.getType().struct.getKind();
			if(klasa!=Struct.Class) {
				report_error("GRESKA:Operatorom new pokusavate da napravite klasu (poziva konstruktor) koja nije deklarisana",fn);
				fn.struct=Tab.noType;
			}else {
				report_info("Kreirana je klasa operatorom new: "+fn.getType(), orActPars);
				fn.struct=fn.getType().struct;
				//!!!!!!!mozda jos nesto za trenutnu konstrukciju klase treba dodati ovde!!!!!!!!!!!!!!!!
				//fn.getCuvarObjekta()
			}
		}else {
			report_error("Desila se neka greska, new prima operator koji nije ni ActPars, a ni Expr,cudno(vrv greska neka)",fn);
		}
	}
	
	public void visit(FactorExpr fe) {
		fe.struct=fe.getExpr().struct;
		
	}
	
	public void visit(FactorNum fn) {
		fn.struct=Tab.intType;
		report_info("---------Otkrivena konstanta int: "+fn.getVal(), fn);
	}
	
	public void visit(FactorBool bt) {
		bt.struct=booleanType;
	}
	
	public void visit(FactorChar fc) {
		fc.struct=Tab.charType;
		report_info("---------Otkriveno slovo : "+fc.getVal(), fc);
	}
	
	/***************************** DESIGNATOR *************************/
	/**
	 * 	Designator ::= (DesignatorM) DesignatorMatched
				|
				(DesignatorU) DesignatorUnmatched
			    ;

		DesPom ::= (DesPom) IDENT:name;
		DesignatorMatched ::= (DesMatch) DesPom OptionalDesignatorOpp;
		DesignatorUnmatched ::= (DesUnmatch) DesUnmatchFlag DesignatorMatched;
		
		OptionalDesignatorOpp ::= (OptDesignOpp) OptionalDesignatorOpp OrIdentExpr
								  |
								  (NoOptDesignOpp) \/*epsilon*\/
								  ;
		OrIdentExpr ::= (OrIdent) DOT IDENT:iden
						|
						(OrExpr) OrIEFlag LSQUARE Expr RSQUARE
						;	
		OrIEFlag ::= (OrIEFlag) \/*epsilon*\/;
		DesUnmatchFlag ::= (DesUnmatchFlag) IDENT:name COLON COLON ;
						a
						/ \
						a
						\
						b
	 * 
	 */
	public static boolean findNamespaceObj(String name) {
		return true;
	}
	public boolean isNamespace=false;
	public String pozvaniNamespace=null;
	public Stack<Boolean> isNamespaceStack= new Stack<>();
	public Stack<String> pozvaniNamespaceList=new Stack<>();
	
	
	public void visit(DesUnmatchFlag df) {
		//isNamespace=true;
		pozvaniNamespace=df.getName();
		//isNamespaceStack.push(isNamespace);
		//pozvaniNamespaceList.push(pozvaniNamespace);
	}
	//napomena Designator.a , Designator mora da bude naziv klase
	Stack<Obj> designator=new Stack<Obj>();
	boolean jedinstven=true;
	
	public void visit(DesPom dp) {
		/**
		if(!isNamespaceStack.empty()) {
			isNamespaceStack.pop();
			Obj nmsp=Tab.find(pozvaniNamespaceList.pop());
			if(nmsp==Tab.noObj) {
				report_error("Ne postoji pozvani namespace", dp);
				dp.obj=Tab.noObj;
			
			}else {
				List<Obj> lista=new ArrayList<>(nmsp.getType().getMembers());
				for(Obj obj:lista) {
					if(obj.getName().equals(dp.getName())) {
						report_info("Pozvano je nesto iz namespacea", dp);
						dp.obj=obj;
						break;
					}
				}
				if(dp.obj!=Tab.noObj && dp.obj!=null) {
					designator.push(dp.obj);
				}
			}
			
			//isNamespace=false;
			//pozvaniNamespace=null;
			return;
			
		}
		*/
		
		String name=dp.getName();
		if(name=="this") {
			dp.obj=potklasaObjekat;
			designator.push(potklasaObjekat);
			return;
		}
		
		//Tab.dump();
	    if(imeTrenutnogNamespacea!=null ) {
			if(Tab.find(name)==Tab.noObj) {
				name="_"+imeTrenutnogNamespacea+"_"+name;
			}
		}else if(pozvaniNamespace!=null) {
			name="_"+pozvaniNamespace+"_"+name;
		}
		
		pozvaniNamespace=null;
		
			
		Obj obj=Tab.find(name);
		
		if(obj!=Tab.noObj) {
			dp.obj=obj;
		    report_info("Promenljivoj koja je po tipu: "+dp.obj +(dp.obj.getType()!=null?dp.obj.getType().getKind():"null"), dp);
		    
		    designator.push(obj);
		    report_info("????????????????????"+pozvaniNamespace+" "+designator+"  "+obj.getName(), dp);
		    return;
		}else {
			dp.obj=Tab.noObj;
			Tab.dump();
			report_error("GRESKA: Promenljiva sa nazivom "+name+" ne postoji, izgled currentScopea:"+Tab.find("_Addition_sum").getName(), dp);
			return;
		}
	}
	
	
	Obj currDeignatorObj;
	public void visit(DesignatorM d) {
		currDeignatorObj=d.obj=d.getDesignatorMatched().obj;
	}
	public void visit(DesignatorU du) {
		currDeignatorObj=du.obj=du.getDesignatorUnmatched().obj;
		report_info("Saljemo designatoru iz unmatcha:", du);
	}
	public void visit(DesMatch dm) {
		if(jedinstven!=true) {
			dm.obj=dm.getOptionalDesignatorOpp().obj;
		}else {
			dm.obj=dm.getDesPom().obj;
		}
		jedinstven=true;
		if(!designator.empty()) designator.pop();
	}
	/**provera za designator unmatch da li sadrzi to ime u namespaceu
	 * DesPom ::= (DesPom) IDENT:name;
		DesignatorMatched ::= (DesMatch) DesPom OptionalDesignatorOpp;
		DesignatorUnmatched ::= (DesUnmatch) IDENT:name COLON COLON DesignatorMatched;
		
		DesUnmatchFlag ::= (DesUnmatchFlag) IDENT:name COLON COLON ;
	 */
	
	public void visit(DesUnmatch du) {
		/**
		Obj ns=Tab.find(((DesUnmatchFlag)du.getDesUnmatchFlag()).getName());
		if(ns==Tab.noObj) {
			report_error("GRESKA:Ne postoji namespace sa tim imenom:"+((DesUnmatchFlag)du.getDesUnmatchFlag()).getName(), du);
			du.obj=Tab.noObj;
			return;
		}
		if(ns.getKind()!=Namespace) {
			report_error("GRESKA:Ovo nije namespace:"+((DesUnmatchFlag)du.getDesUnmatchFlag()).getName(), du);
			du.obj=Tab.noObj;
			return;
		}
		//provera da li je polje u namespaceu
		boolean flagSadrzi=false;
		List<Obj> lista=new ArrayList<>(ns.getType().getMembers());
		String nazivPozvanog=((DesMatch)du.getDesignatorMatched()).getDesPom().getName();
		for(Obj l:lista) {
			if(l.getName().equals(nazivPozvanog)) {
				flagSadrzi=true;
				break;
			}
		}
		
		if(flagSadrzi==true) {
			du.obj=du.getDesignatorMatched().obj;
			report_info("Prepoznato nesto u namespaceu tipa:"+du.obj.getType().getKind(),du);
		}else {
			report_error("Ne postoji ime:"+nazivPozvanog+" sa tim nazivom u tom namespaceu:"+ns.getName(), du);
		}
		*/
		du.obj=du.getDesignatorMatched().obj;
	}
	public void visit(OptDesignOpp op) {
		/**
		if(op.getOrIdentExpr().getClass()==OrIdent.class) {
			System.out.println("-------PROBA----------: "+((OrIdent)op.getOrIdentExpr()).getIden());
		}else {
			System.out.println("-------PROBA----------: ");
		}
		*/
		
		//pristup elementu klase
		if(op.getOrIdentExpr().getClass()==OrIdent.class) {//kao instanceof
			jedinstven=false;
			if(designator.empty()) {
				op.obj=Tab.noObj;
				return;
			}
			Obj currDesignator=designator.pop();
			if(currDesignator==Tab.noObj) {
				op.obj=Tab.noObj;
				return;
			}
			
			if(currDesignator.getType().getKind()!=Struct.Class) {
				report_error("GRESKA:Pristupa se polju elementa koji nije klasa", op);
				op.obj=Tab.noObj;//suptilan nacin a reci null
				return;
			}
			
			//da bi se sprecila beskonacna rekurzija ako je tip elementa klase sama ista kao i sama klasa
			//znaci ako pristupamo polju dok smo jos u klasi
			//npr. class M{M m; int a; void metod(){m.a=5;}}
			
		  if(currDesignator.getType()==potklasaTip) {
				//pronalazimo polje a,za objekat m kome pristupamo iz slikovitog primera
				Obj obj=Tab.currentScope.getOuter().findSymbol(((OrIdent)op.getOrIdentExpr()).getIden());
				
				if(obj==null) {
					report_error("Klasa:- "+potklasaIme+" nema polje "+((OrIdent)op.getOrIdentExpr()).getIden(),op);
					op.obj=Tab.noObj;
					return;
				}else {
					report_info("Pristupa se polju: "+((OrIdent)op.getOrIdentExpr()).getIden()+" klase: "+potklasaIme,op);
					designator.push(obj);
					op.obj=obj;
					return;
				}
			}
			
			//pristup polju klase iz neke fruge klase ili globalne metode
			// npr. class M{M m; int a;} M m; void pri(){m.a=5;}
			
			//posto je definicija klase ovde gotova, imamo formiran struct cvor koji ukazuje na polja i metode klase
			//zato dohvatamo sve objekte klase
			//i onda u jednoj for petlji prolazimo kroz sve elemente da vidimo da li postoji polje klase kome se pristupa
			
			ArrayList<Obj> listaPotencijalnihPolja=new ArrayList<>(currDesignator.getType().getMembers());
			
			for(Obj obj:listaPotencijalnihPolja) {
				if(((OrIdent)op.getOrIdentExpr()).getIden().equals(obj.getName())) {
					report_info("Pristupa se polju ili metodu klase izvan klase", op);
					designator.push(obj);
					op.obj=obj;
					return;
				}
			}
			
			report_error("GRESKA:Ne postoji polje ili metoda klase kojoj se pristupa"+potklasaTip+" "+potklasaObjekat.getName()+" "+potklasaObjekat.getType(), op);
			return;
			
		}
		//pristup elementu niza klase
		
		if(op.getOrIdentExpr().getClass()==OrExpr.class) {
			Expr expr=((OrExpr)op.getOrIdentExpr()).getExpr();
			jedinstven=false;
			if(expr.struct!=Tab.intType) {
				//ako indeksiramo niz sa elementom koji nije int
				report_error("GRESKA: Indeksiranje niza elementom koji nije int",op);
				op.obj=Tab.noObj;
				return;
			}
			
			if(designator.empty()) {
				op.obj=Tab.noObj;
				return;
			}
			
			Obj currDesignator=designator.pop();
			
			if(currDesignator!=null) {
				if(currDesignator.getType().getKind()!=Struct.Array) {
					report_error("GRESKA: Promenjljiva kojoj se trenutno pristupa nije niz",op);
					op.obj=Tab.noObj;
					return;
				}else {
					//vracamo gore samo element niza kome se pristupa odnosno njegov tip
					op.obj=new Obj(Obj.Elem,currDesignator.getName(),currDesignator.getType().getElemType());
					
					if ( expr.getTerm().getFactor() instanceof FactorNum) {
						//op.obj.setAdr(currDesignator.getAdr()+((FactorNum)expr.getTerm().getFactor()).getVal()*4);
					}else if(expr.getTerm().getFactor() instanceof FactorIdent) {
					}
					
					//op.obj.setAdr(((FactorNum)expr.getTerm().getFactor()).getVal());
					designator.push(op.obj);
					
					return;	
				}
			}
			
		}
		// trazenje max. el. niza
		if(op.getOrIdentExpr().getClass()==MaxArray.class) {
			jedinstven=false;
			Obj currDesignator=designator.pop();
			
			if(currDesignator!=null) {
				if(currDesignator.getType().getKind()!=Struct.Array) {
					report_error("GRESKA: Promenjljiva kojoj se trenutno pristupa nije niz",op);
					op.obj=Tab.noObj;
					return;
				}else {
					//vracamo gore samo element niza kome se pristupa odnosno njegov tip
					op.obj=new Obj(Obj.Elem,currDesignator.getName(),currDesignator.getType().getElemType());
					
					designator.push(op.obj);
					return;	
				}
			}
		}
		//niz.foreach(a=>{print(a);});
		/**
		if(op.getOrIdentExpr().getClass()==FE.class) {
			jedinstven=false;
			Obj currDesignator=designator.pop();
			
			if(currDesignator!=null) {
				if(currDesignator.getType().getKind()!=Struct.Array) {
					report_error("GRESKA: Promenjljiva kojoj se trenutno pristupa nije niz",op);
					op.obj=Tab.noObj;
					return;
				}else {
					//vracamo gore samo element niza kome se pristupa odnosno njegov tip
					op.obj=new Obj(Obj.Elem,currDesignator.getName(),currDesignator.getType().getElemType());
					
					designator.push(op.obj);
					return;	
				}
			}
		}
		*/
	}
	
	
	
	
	
	/***********************ACT PARS-stvarni argumenti pri pozivu funkcije***********/
	/**
	 * PomExprActPars ::= (PomExprActPars) Expr;
	 * ActPars ::= (ActPars) PomExprActPars OptionalComaExpr;

		OptionalComaExpr ::= (OptCommaExpr) OptionalComaExpr COMMA PomExprActPars
							  |
							  (NoOptCommaExpr) \/*epsilon*\/
							  ;
							  
		AdditionalActPars ::= (AddActPar) ActPars
							  |
							  (NoAddActPar) \/*epsilon*\/
							  ;
	 * 
	 * 
	 */
	
	//provera za klase koje su izvedene iz neke klase u samoj implementaciji nije potpuna
	//ne proverava se da li je klasa koja se dodeljuje izvedena klasa iz elementa kome se dodeljuje
	//ako je to slucaj onda je to ok
	//npr. class A{..} class B extends A{...} A a; B b; ->a=b je ok
	/**
	 * 
	 * @param s1-vrednost koja se dodeljuje
	 * @param s2-vrednost kojoj se dodeljuje
	 * @return
	 */
	public boolean AssignedToImproved(Struct s1,Struct s2) {
		//da li moze s1=s2
		if(!s1.assignableTo(s2)) {
			if(s1.getKind()==s2.getKind() && s1.getKind()==Struct.Class) {
				Struct pom=s1;
				while(pom!=null) {
					//idemo po lancu pokazivaca u strukturi
					if(pom.equals(s2)) {
						return true;
					}
					pom=pom.getElemType();
				}
			}else {
				return false;
			}
		}
		return true;
	}
	//ovde se proverava da li je broj i tipovi argumnta pri pozivu metoda/funkcija ok
	//koristimo ovo sto je definisano pre factor da bi bilo vidljivo:
	//stakMetoda ce da sadrzi objekte u stablu koji su metode 
		//Stack<Obj> stekMetoda=new Stack<>();
		//a stek argumenata ce da sadrzi argumente koji se koriste pri pozivu tih metoda
		//moglo je sve i lokalno da se cuva posto nece se pozvati naredna metoda dok se ne zavrsi
		//prethodna, ali je ovako bolje zbog prosirivosti
		//Stack<List<Struct>> stekArgumenata=new Stack<>();
	
	
	//Pojasnjenje zasto je uvedeno PomExprActPars:
	//Da to nije uvedeno onda ne bi mogli da dodajemo Expr u listu argumenata
	//jer expr moze da se koristi i za nesto drugo a ne samo za argumente funkcije
	//tako da kad ni direktnoo u Expr dodali dodavanje u stekArgumenata, onda bi
	//mozda dodali i argumente koji nisu argumenti metode nego na primer broj u neww int[expr]...
	public void visit(PomExprActPars pom) {
		//Pojasnjenje:
		//U stekArgumenata se stavljaju argumenti metoda, posto je PomExprActPars pozivan uvek
		//pre ActPars za sve argumente oni ce se svi dodati u stekArgumenata i onda
		//u ActPars proveravamo da li su broj i tipovi argumenata dobri u ActPars
		report_info("pozivam act pars", pom);
		List<Struct> lista=stekArgumenata.pop();
		lista.add(pom.getExpr().struct);
		stekArgumenata.push(lista);
	}
	
	public void visit(ActPars ap) {
		//funkcija koja je pozvana
		Obj funkcija=stekMetoda.pop();
		//argumneti funkcije
		report_info("----------------Pozivam ponovo act pars",ap);
		List<Struct> argument=stekArgumenata.pop();
		
		if(funkcija.getKind()==Obj.Meth) {
			//broj argumenata je jednak stvarnom broju argumenata za globalne i staticke funkcije
			//ali je broj argumenata metoda jednak stvarniBroj+1 zbog this
			//!!!!!!!!!!!!!!!!!-GORE TREBA DODATI da kad se prepozna globalna funkcija da se dodaje u global listu
			//da bi mogao ovde da proverim da li je globalna i dodam +1
			if(funkcija.getLevel()!=(argument.size()+(globalneMetode.contains(funkcija.getName())==true?0:1))) {
				report_info(globalneMetode.toString(),ap);
				report_error("Broj argumenata u pozivu funkcije(broj argumenata u funkciji:"+funkcija.getName()+" je: "+funkcija.getLevel()+" , ne odgovara stvarnom broju argumenata funkcije(stvarni broj argumenata je:"+argument.size(), ap);
			}else {
				//provera da li se tipovi argumenta poklapaju
				int i=0;
				for(Obj funArg:funkcija.getLocalSymbols()) {
					if(!funArg.getName().equals("this")) {
						//da li argument funkcije u pozivu moze da se dodeli stvarno argumentu
						//report_info("Funkcija je:"+funkcija.getName()+" je:"+funkcija.getLevel()+" a i je:"+i+" a size argumenata je:"+argument.size(),ap);
						if(!AssignedToImproved(argument.get(i), funArg.getType())) {
							report_error("Stvarni i formalni "+i+"-ci parametar se ne poklapaju",ap);
						}
						i++;
					}
					
					if(i==(funkcija.getLevel()-((!globalneMetode.contains(funkcija.getName())==true)?1:0))) {
						break;
					}
				}
			}
		}
	}
	
	//ako funkcija nema argumente uopste
	public void visit(NoAddActPar nap) {
		//trenutno ovako dok ne implementiram sve ---------------------------
		if(stekArgumenata.empty() || stekMetoda.empty()) return;
		//funkcija koja je pozvana
		Obj funkcija=stekMetoda.pop();
		//argumneti funkcije
		List<Struct> argument=stekArgumenata.pop();
		if(funkcija.getKind()==Obj.Meth) {
			//broj argumenata je jednak stvarnom broju argumenata za globalne i staticke funkcije
			//ali je broj argumenata metoda jednak stvarniBroj+1 zbog this
			//!!!!!!!!!!!!!!!!!-GORE TREBA DODATI da kad se repozna globalna funkcija da se dodaje u global listu
			//da bi mogao ovde da proverim da li je globalna i dodam +1
			if(funkcija.getLevel()!=argument.size()+(globalneMetode.contains(funkcija.getName())==true?0:1)) {
				report_error("Broj argumenata u pozivu funkcije, ne odgovara stvarnom broju argumenata funkcije "+"level funkcije je:"+"name:"+funkcija.getName()+" glob:"+globalneMetode+" "+funkcija.getLevel()+" a broj argumenata je:"+argument.size(), nap);
			}
		}
		
	}
	
	
	//dva puta sam pisao AdditionalActPars ?????????? pa se zbog toga duplira i kod
	//ako funkcija nema argumente uopste
	public void visit(NoAddActPar2 nap) {
			//funkcija koja je pozvana
			Obj funkcija=stekMetoda.pop();
			//argumneti funkcije
			List<Struct> argument=stekArgumenata.pop();
			if(funkcija.getKind()==Obj.Meth) {
				//broj argumenata je jednak stvarnom broju argumenata za globalne i staticke funkcije
				//ali je broj argumenata metoda jednak stvarniBroj+1 zbog this
				//!!!!!!!!!!!!!!!!!-GORE TREBA DODATI da kad se repozna globalna funkcija da se dodaje u global listu
				//da bi mogao ovde da proverim da li je globalna i dodam +1
				if(funkcija.getLevel()!=argument.size()+(globalneMetode.contains(funkcija.getName())==true?0:1)) {
					report_error("GRESKA:Broj argumenata u pozivu funkcije, ne odgovara stvarnom broju argumenata funkcije"+" level funkcije je:"+funkcija.getLevel()+" a broj argumenata je:"+argument.size(), nap);
				}
			}
			
	}
	
	/****************** COND FACT  ***************************/
	
	/**
	 * 
	 * CondFact ::= (CondFact) Expr AdditionalRelopExpr;

		AdditionalRelopExpr ::= (AddRelopExpr) Relop Expr
								|
								(NoAddRelopExpr) \/*epsilon*\/
								;
		AdditionalCondFact ::= (AddCondFact) CondFact
								|
								(NoAddCondFact) \/*epsilon*\/
								;
	 * 
	 * @return
	 */
	
	boolean hasSecondExpr=false;
	Expr secondExpr=null;
	
	public void visit(AddRelopExpr are) {
		hasSecondExpr=true; secondExpr=are.getExpr();
	}
	
	public void visit(CondFact cf) {
		//ako ima samo jedan argument mora biti logickog tipa
		//cf.struct=booleanType;
		//return;
		if(cf.getExpr().struct!=booleanType && !hasSecondExpr) {
			report_error("Tip expr-esa mora biti boolean", cf);
			cf.struct=Tab.noType;
			return;
		}
		if(!hasSecondExpr) {
			cf.struct=cf.getExpr().struct;
			return;
		}
		//kompatibilnost uslova
		if(!secondExpr.struct.compatibleWith(cf.getExpr().struct)){
			cf.struct=Tab.noType;
			report_error("Izrazi nisu komatibilni za dodelu prvi je tipa:"+cf.getExpr().struct.getKind()+" a drugi:"+secondExpr.struct.getKind(), cf);
			return;
		}
		//jos je ostalo implementirati proveru za nizove
		//Uz promenljive tipa klase ili niza, od relacionih operatora, mogu se koristiti samo != i ==
		
		Integer exprKind=cf.getExpr().struct.getKind();
		Integer secondExprKind=secondExpr.struct.getKind();
		
		if(exprKind==secondExprKind && exprKind==Struct.Array && !currOperator.equals("!=") && !currOperator.equals("==")) {
			report_error("GRESKA:Nizovi mogu da se porede samo na != i ==",cf);
			cf.struct=Tab.noType;
			return;
		}else if(exprKind==secondExprKind && exprKind==Struct.Array && !currOperator.equals("!=") && !currOperator.equals("==")) {
			report_error("GRESKA:Klase se porede samo na == i !=", cf);
			cf.struct=Tab.noType;
			return;
		}
		
		hasSecondExpr=false;
		cf.struct=booleanType;
		 return;
	}
		
	/*****************COND TERM************************************/
	/**
	 * CondTerm ::= (CondTerm) CondFact OptionalCondFact;

	   OptionalCondFact ::= (OptCondFact) OptionalCondFact AND CondFact
					  |
					 (NoOptCondFact) \/*epsilon*\/
					 ;
	 */
	
	public void visit(CondTerm ct) {
		if(!(ct.getCondFact().struct==booleanType && ct.getOptionalCondFact().struct==booleanType)) {
			report_error("GRESKA: Tipovi nisu kompatibilni u cond term-u", ct);
			ct.struct=Tab.noType;
			return;
		}
		ct.struct=booleanType;
		return;
	}
	
	public void visit(OptCondFact oct) {
		//mada smo vec to proverili na dubljim nivoima
		if(!(oct.getCondFact().struct==booleanType && oct.getOptionalCondFact().struct==booleanType)) {
			report_error("GRESKA: Tipovi nisu kompatibilni u cond term-u", oct);
			oct.struct=Tab.noType;
			return;
		}
		oct.struct=booleanType;
		return;
	}
	
	public void visit(NoOptCondFact nct) {
		nct.struct=booleanType;
	}
	/****************CONDITION*************************************/
	/**
	 * Condition ::= (Condition) CondTerm OptionalCondTerm;

	   OptionalCondTerm ::= (OptCondTerm) OptionalCondTerm OR CondTerm
					  |
					  (NoOptCondTerm) \/*epsilon*\/
					  ;
	 * @return
	 */
	
	public void visit(NoOptCondTerm noct) {
		noct.struct=booleanType;
	}
	
	public void visit(OptCondTerm oct) {
		if(!(oct.getOptionalCondTerm().struct==booleanType && oct.getCondTerm().struct==booleanType)) {
			report_error("GRESKA: Tipovi nisu kompatibilni u cond term-u", oct);
			oct.struct=Tab.noType;
			return;
		}
		oct.struct=booleanType;
		return;
	}
	
	public void visit(Condition c) {
		if(!(c.getOptionalCondTerm().struct==booleanType && c.getCondTerm().struct==booleanType)) {
			report_error("GRESKA: Tipovi nisu kompatibilni u cond term-u", c);
			c.struct=Tab.noType;
			return;
		}
		c.struct=booleanType;
		return;
	}
	
	/****************** DESIGNATOR STATEMENT *********************/
	/**
	 * DesignatorStatement ::= (DesStmtE) Designator OrDesStmt
						|
						(DesStmtH) LSQUARE OptionalDesignComma MUL Designator RSQUARE EQUALS Designator
						;

		OrDesStmt ::= (OrAssignExpr) Assignop Expr
					  |
					  (OrActPars) PomDesAddStek LPAREN AdditionalActPars RPAREN
					  |
					  (OrIncrement) INCREMENT
					  |
					  (OrDecrement) DECREMENT
					  ;
		PomDesAddStek ::= (PomDesAddStek) \/* epsilon*\/;		  
							
		OptionalDesignComma ::= (OptDesCom) OptionalDesignComma AdditionalDesign COMMA
								|
								(NooOptDesCom) \/*epsilon*\/
								;
		AdditionalDesign ::= (AdditionalDes) Designator
							 |
							 (NoAdditionalDes) \/*epsilon*\/
							 ;
	 * @return
	 */
	//ovde visie nista necemo slati na gore posto se u ovom podstablu sve isproveravalo
	//tako da na visim instancama nema potrebe dalje proveravati ono sto je vec provereno
	
	//ova funkcija ce proveravati uslove koji se odnose na DesStmE--napomena: to je skracenica od easy
	public void visit(DesStmtE des) {
		if(des.getOrDesStmt().getClass()==OrAssignExpr.class) {//dodela vrednosti a=1
			Expr expr=((OrAssignExpr)des.getOrDesStmt()).getExpr();
			Integer kind=des.getDesignator().obj.getKind();
			//ako promenljiva kojoj se vrsi dodela, nije ni element niza, ni polje klase, ni globalna promenjljiva, onda je to greska
			if(kind!=Obj.Elem && kind!=Obj.Fld && kind!=Obj.Var) {
				report_error("GRESKA:Dodela se vrsi izrazu koji nije ni globalna promenljiva, ni promenjljiva klase, ni element niza, vec je:"+kind, expr);
				return;
			}
			
			//druga greska je ako se vrsi dodela nekompatibilnim tipovima
			//za tu priliku sam napisao posebnu metodu koja je redefinicija 
			//metode AssignTo, a koja jos proverava da li moze da se vrsi dodela
			//podtipa nadtipu, ako su tipovi klase
			/**if(!AssignedToImproved(expr.struct,des.getDesignator().obj.getType())) {
				report_error("GRESKA: Tip za dodelu je nekompatibilan, prvi je:"+des.getDesignator().obj.getType().getKind()+" a drugi:"+expr.struct.getKind(), expr);
				return;
			}*/
			//ako se nalazimo u static inicijalizatoru mozemo da koistimo samo staticka polja
			if(startStaticArea==true && !listaStatickihPoljaKlase.contains(des.getDesignator().obj.getName())) {
				report_error("Nalazite se u statickom inicijalizatoru i koristite polje koje nije staticko, objekat:"+des.getDesignator().obj.getName()+" a lista statickih polja je:", des);
				for(String o:listaStatickihPoljaKlase) {
					report_info(" "+o, expr);
				}
				return;
			}
			if(!AssignedToImproved(expr.struct,des.getDesignator().obj.getType())) {
				report_error("GRESKA: Tip za dodelu je nekompatibilan, prvi je:"+des.getDesignator().obj.getType().getKind()+" a drugi:"+expr.struct.getKind(), expr);
				return;
			}
		}else if(des.getOrDesStmt().getClass()==OrIncrement.class) {//inkrementiranje i++
			if(des.getDesignator().obj.getType()!=Tab.intType) {
				report_error("GRESKA:Pokusavate inc za promenjljivu koja nije int",des);
				return;
			}
			if(
					des.getDesignator().obj.getType().getKind()!=Obj.Var &&
					des.getDesignator().obj.getType().getKind()!=Obj.Fld &&
					des.getDesignator().obj.getType().getKind()!=Obj.Elem
					) {
				report_error("GRESKA:Pokusavate inc promenjljive koja nije ni lokalna ni globalna, ni klasna", des);
				return;
			}
		}else if(des.getOrDesStmt().getClass()==OrDecrement.class) {//dekrementiranje i--
			if(des.getDesignator().obj.getType()!=Tab.intType) {
				report_error("GRESKA:Pokusavate dec za promenjljivu koja nije int",des);
				return;
			}
			if(
					des.getDesignator().obj.getType().getKind()!=Obj.Var &&
					des.getDesignator().obj.getType().getKind()!=Obj.Fld &&
					des.getDesignator().obj.getType().getKind()!=Obj.Elem
					) {
				report_error("GRESKA:Pokusavate dec promenjljive koja nije ni lokalna ni globalna, ni klasna", des);
				return;
			}
		}else if(des.getOrDesStmt().getClass()==OrActPars.class) {//poziv funkcije f(1,2,3)
			//!!!!!!!!!!!!!!!!!!!!!!!!!1Moguca greska jer nije koriscen FactorDesignator umesto Designatora u cupu-u proveri kasnije| jeste to gres
			if(des.getDesignator().obj.getKind()!=Obj.Meth) {
				report_error("GRESKA: Vrsite poziv necega sto nije ni funkcija ni metoda,vec je:"+des.getDesignator().obj.getKind(), des);
				return;
			}else {
				
			}
		}
	}
	public void visit(PomDesAddStek pom) {
		stekMetoda.push(currDeignatorObj);
		//za sada cemo samo postaviti precagu da bi kasnije umesto nje bili argumenti
		stekArgumenata.push(new ArrayList<Struct>());
	}
	//[a,b,*c]=[1,2,3,4]=>a=1;b=2;c=[3,4]
	List<Obj> designatorsList=new ArrayList<>();
	
	public void visit(AdditionalDes des) {
		if(
				des.getDesignator().obj.getType().getKind()!=Obj.Var &&
				des.getDesignator().obj.getType().getKind()!=Obj.Fld &&
				des.getDesignator().obj.getType().getKind()!=Obj.Elem
				) {
			report_error("GRESKA:Pokusavate dodelu promenjljivoj koja nije ni lokalna ni globalna, ni klasna, vec je:"+des.getDesignator().obj.getType().getKind(), des);
			return;
		}else {
			designatorsList.add(des.getDesignator().obj);
		}
	}
	public void visit(DesStmtH des) {//skaraceno od hard :)
		//ovde treba uraditi nekoliko provera
		
		//provera da li je poslednji element liste niz [a,b,*c], c mora biti niz
		Struct array=des.getDesignator().obj.getType();
		if(array.getKind()!=Struct.Array) {
		
			report_error("GRESKA:Poslednji element u listi nije niz", des);
			return;
		}
		
		//provera da li je poslednji element niza kompatibilan sa vrednosti koja se dodeljuje
		if(!AssignedToImproved(des.getDesignator().obj.getType().getElemType(), des.getDesignator1().obj.getType().getElemType())){
			report_error("GRESKA:Tip elementa niza poslednjeg elementa se ne poklapa sa tipom elementa vrednosti koja se dodeljuje", des);
			return;
		}
		
		//priovera da li ostatak elemenata niza od 0 do n-2 (ako je duzina niza n)-moze im se dodeli vrednost sa desne strane
		
		for(Obj obj:designatorsList) {
			if(!AssignedToImproved(des.getDesignator1().obj.getType().getElemType(), obj.getType())) {
				report_error("GRESKA:Neki element liste nema kompatibilan tip sa elementom sa desne strane znaka jednako", des);
				return;
			}
		}
		designatorsList.clear();
	}
	
	/*****************FORM PARS***********************/
	/**
	 * FormPars ::= (FormPars) Type NewVarDecl OptionalVarDeclList2; //razlikuje se od VarDecl samo po ; na kraju

		OptionalVarDeclList2 ::= (OptionalVarList2) OptionalVarDeclList2 COMMA Type NewVarDecl
								|
								(NoOptionalVarList2) \/*epsilon*\/
								;
								
	  if("epsilon".equals(tip)) {
			report_info("Promenljiva: "+name+" je dodata u tabelu simbola.", varMeth);
			Tab.insert(Obj.Var, name, currType);
		}else if("[]".equals(tip)) {
			report_info("Promenjliva tipa niz: "+name+" je dodata u tableu simbola.",varMeth);
			Tab.insert(Obj.Var, name, new Struct(Struct.Array,currType));
		}else {
			report_error("Desia se greska promenjliva nije ni niz ni obicna.", varMeth);
		}
	 * @return
	 */
	
	
	//BITNO-ISPRAVI ZA METODE KLASE VEROVATNO TREBA DA SE DODA +1 za elemetnre niza kod o.setAdr()
	Stack<Obj> metodParams=new Stack<>();
	Stack<Obj> reverseMetodParams=new Stack<Obj>();
	public void visit(FormPars fp) {
		//dodto obrtanje steka
		while(!metodParams.empty()) {
			Obj stckObj=metodParams.pop();
			reverseMetodParams.push(stckObj);
		}
		
		
		
		Tab.insert(Obj.Var, ((VarDeclIdent)fp.getNewVarDecl()).getName(), fp.getType().struct);
		brojParametara++;
		
		int size=reverseMetodParams.size();
		while(!reverseMetodParams.empty()) {
			Obj obj=reverseMetodParams.pop();
			
			Obj o=Tab.insert(obj.getKind(), obj.getName(), obj.getType());
			o.setAdr(brojParametara);
			brojParametara++;
		}
	}
	
	public void visit(OptionalVarList2 ov2) {
		Obj obj=new Obj(Obj.Var,((VarDeclIdent)ov2.getNewVarDecl()).getName(),ov2.getType().struct);
		metodParams.push(obj);
	}
	/*****************************NAMESPACE****************/
	/**
	 * NamespaceList ::= (NameSpaceL) NamespaceList Namespace
				  |
				  (NoNameSpaceL) \/*epsilon*\/
				  ;
		NamespaceBegin ::= (NamespaceBegin) NAMESPACE IDENT:name;
		
		Namespace ::= (Namespace) NamespaceBegin LBRACE ConstDeclList LBRACE MethodDeclList RBRACE RBRACE;
	 * @return
	 */
	
	List<String> listaNamespaceova=new ArrayList<>();
	Struct currNamespace=null;
	String imeTrenutnogNamespacea=null;
	public void visit(NamespaceBegin nb) {
		String name=nb.getName();
		imeTrenutnogNamespacea=name;
		//listaNamespaceova.add(name);
		//currNamespace=new Struct(Struct.Class);//n
		//Tab.insert(Namespace,name, currNamespace);//n
		//Tab.openScope();//n
		
	}
	
	public void visit(Namespace n) {
		//Tab.chainLocalSymbols(currNamespace);//n
		//Tab.closeScope();//n
		currNamespace=null;//n
		imeTrenutnogNamespacea=null;
		listaNamespaceova=null;
	}
	
	//rad sa statickim objektime
	boolean startStaticArea=false;
	public void visit(StaticStartDef ssd) {
		startStaticArea=true;
	}
	
	public void visit(StaticEndDef sed) {
		startStaticArea=false;
	}
	public boolean passed(){
    	return !errorDetected;
    }
	
	//MODIFIKACIJA 2
	/**for( i: arr){}
	 * ForModBegin ::= (ForModBegin) ForStart LPAREN ForVar ForArrayMod;
	   ForVar ::= (ForVar) IDENT: name COLON;
	   ForArrayMod ::= (ForArrayMod) IDENT:name RPAREN;
	 */
	
	public void visit(ForVar fv) {
		Obj obj=Tab.find(fv.getName());
		uPetlji++;
		if(obj==Tab.noObj) {
			fv.obj=Tab.noObj;
			report_error("Nema te promenjljive", fv);
			return;
		}else {
			if(obj.getKind()!=Obj.Var) {
				fv.obj=Tab.noObj;
				report_error("To nije promenjljiva", fv);
				return;
			}
			if(obj.getType().getKind()!=Struct.Int) {
				report_error("Promenjljiva nije int", fv);
				fv.obj=Tab.noObj;
				return;
			}
			fv.obj=obj;
		}
		return;
	}
	
	public void visit(ForArrayMod fam) {
		Obj obj=Tab.find(fam.getName());
		if(obj==Tab.noObj) {
			fam.obj=Tab.noObj;
			report_error("Nema te promenjljive", fam);
			return;
		}else {
			if(obj.getType().getKind()!=Struct.Array) {
				fam.obj=Tab.noObj;
				report_error("To nije promenjljiva", fam);
				return;
			}
			if(obj.getType().getElemType().getKind()!=Struct.Int) {
				fam.obj=Tab.noObj;
				report_error("Promenjljiva nije niz ili nema elemente tipa niza", fam);
				return;
			}
			fam.obj=obj;
		}
	}
	
	public void visit(ForArr fa) {
		uPetlji--;
	}
	
	public void visit(ForArr2 fa2) {
		uPetlji--;
	}
	
	//MODIFIKACIJA 3
	/**
	 * 
	 * niz.foreach(a=>{print(a);});
	 * Matched ::= (ForeachStmt) DesignatorFE LPAREN FeVar ARROW Statement RPAREN SEMICOLON
	 * DesignatorFE ::= (DesignatorFE) IDENT:name DOT FOREACH;
		FeVar ::= (FeVar) IDENT:name;
	 */
	public void visit(DesignatorFE dfe) {
		Obj obj=Tab.find(dfe.getName());
		if(obj==Tab.noObj) {
			report_error("Nije pronadjen niz", dfe);
		}else {
			if(obj.getType().getKind()!=Struct.Array) {
				report_error("To nije niz", dfe);
				return;
			}else {
				report_info("Nadjen niz", dfe);
				dfe.obj=obj;
			}
		}
	}
	
	public void visit(FeVar fv) {
		Obj obj=Tab.find(fv.getName());
		if(obj==Tab.noObj) {
			report_error("Nije pronadjena promenjljiva", fv);
		}else {
			fv.obj=obj;
		}
	}
	
	//Modifikacija 4
	// do { i--} while(i>0)
	
	/**
	 * Matched ::= (DoWhile1) DoWhileBegin  Matched RBRACE WHILE LPAREN Condition DoWhileEnd
					|
					(DoWhile2) DoWhileBegin  Unmatched RBRACE WHILE LPAREN Condition DoWhileEnd
					;

					DoWhileBegin ::= (DoWhileBegin) DO LBRACE;
					DoWhileEnd ::= (DoWhileEnd) RPAREN;
	 */
	
	public void visit(DoWhileBegin dwb) {
		uPetlji++;
	}
	
	public void visit(DoWhileEnd dwe) {
		
	}
	
	public void visit(DoWhile1 dw) {
		uPetlji--;
	}
	
}
