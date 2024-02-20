package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	public int getMainPc() {
		return mainPc;
	}
	
	Scope currentScope = Tab.currentScope;
	HashMap<Obj, Integer> hashOfArrays=new HashMap<>();
	HashMap<Obj, Stack<Integer>> hashOfIndexesOfArrays=new HashMap<>();
	/**
	 * U hes tabeli prosledjenog opsega trazi Obj cvor sa imenom name, pocevsi od
	 * prosledjenog opsega, pa redom kroz opsege na nizim nivoima. Povratna
	 * vrednost: - pronadjeni Obj cvor, ako je pretrazivanje bilo uspesno. -
	 * Tab.noObj objekat, ako je pretrazivanje bilo neuspesno.
	 */
	public static Obj find(String name, Scope scope) {
		Obj resultObj = null;
		for (Scope s = scope; s != null; s = s.getOuter()) {
			if (s.getLocals() != null) {
				resultObj = s.getLocals().searchKey(name);
				if (resultObj != null)
					break;
			}
		}
		return (resultObj != null) ? resultObj : Tab.noObj;
	}
	
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
			
			AdditionalPrintNum ::= (AddPrintN) COMMA NUMCONST
						|
						(NoAddPrintN) \/*epsilon*\/
						;
	 */
	/*********************************PRINT***************************/
	public void visit(StmtPrint print) {//podrazumeva se da je vrednost vec na steku
		if(print.getExpr().struct==Tab.intType || print.getExpr().struct==SemanticAnalyzer.booleanType) {
			Code.put(Code.print);
		}else if(print.getExpr().struct==Tab.charType) {
			Code.put(Code.bprint);
		}else {//boolenantype
			//err
		}
		
	}
	
	public void visit(AddPrintN apn) {
		StmtPrint pstm=(StmtPrint) apn.getParent();
		
		Integer width=apn.getVal();
		Code.loadConst(width);	
	}
	
	public void visit(NoAddPrintN napn) {
		StmtPrint stmt=(StmtPrint)napn.getParent();
		Expr expr=stmt.getExpr();
		if(expr.struct==Tab.intType) Code.loadConst(5);
		else if(expr.struct==SemanticAnalyzer.booleanType) Code.loadConst(5);
		else Code.loadConst(1);
	}
	
	/******************************READ************************************/
	
	public void visit(StmtRead read) {
		Designator des=read.getDesignator();
		if(des.obj.getType()==Tab.charType) Code.put(Code.bread);
		else Code.put(Code.read);
		
		Code.store(des.obj);//storujemo procitanu vrednost u des.obj
	}
	
	/****************************Global functions****************************/
	/**
	 * MethodDecl ::= (MethodDecl) TypeOrVoid LPAREN AdditionalFormPars RPAREN OptionalMethVarDeclL LBRACE StatementList RBRACE;
	 * TypeOrVoid ::= (TypeOpt) Type IDENT:name
				|
				(VoidOpt) VOID IDENT:name
				;
	   AdditionalExpr ::= (AddExpr) Expr
					|
				   (NoAddExpr) \/*epsilon*\/
				    ;
	   
	 */
	private Obj currMethod=null;
	private Boolean returnStmtFound=false;
	public void initVFP() {
		int varCnt=SemanticAnalyzer.nVars;
		for(Obj obj:this.hashOfClasses.keySet()) {
			hashOfClasses.put(obj, varCnt);//adresa pocetka VFT za tu klasu
			for(Obj meth:obj.getType().getMembers()) {
				if(meth.getKind()==Obj.Meth) {
					String name=meth.getName();
					//ubacivanje slovo po slovo metode
					for(int i=0;i<name.length();i++) {
						char c=name.charAt(i);
						Code.loadConst(c);
						Code.put(Code.putstatic);
						Code.put2(varCnt);
						varCnt++;//svako slovo tavljamo na posebnu adresu
					}
					//kraj metode
					Code.loadConst(-1);
					Code.put(Code.putstatic);
					Code.put2(varCnt);
					varCnt++;
					Code.loadConst(meth.getAdr());//adresa u prograu te metode
					Code.put(Code.putstatic);
					Code.put2(varCnt);
					varCnt++;
				}
				
			}
			//kraj tabele simbola
			Code.loadConst(-2);
			Code.put(Code.putstatic);
			Code.put2(varCnt);
			varCnt++;	
		}
		
		SemanticAnalyzer.nVars=varCnt;//azuriranje broja globalnih podataka
	}
	
	public void visit(TypeOpt to) {
		to.obj.setAdr(Code.pc);
		int level=to.obj.getLevel();
		int argNum=to.obj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(level);
		Code.put(argNum);
		this.currMethod=to.obj;
	}
	
	public void visit(VoidOpt vo) {
		vo.obj.setAdr(Code.pc);
		String name=vo.obj.getName();
		if("main".equals(name)) {
			//ovde treba inicijalizovati tableu virtuelnih funkcija
			this.mainPc=Code.pc;//prva instrukcija koja se izvrsava
			initVFP();
			
		}
		int level=vo.obj.getLevel();
		int argNum=vo.obj.getLocalSymbols().size();
		Code.put(Code.enter);
		Code.put(level);
		Code.put(argNum);
		this.currMethod=vo.obj;
		
	}
	//return stmt
	public void visit(AddExpr aexpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
		returnStmtFound=true;
	}
	
    public void visit(MethodDecl md) {
		if(!returnStmtFound) {
			Struct mdStruct=md.obj.getType();
			if(mdStruct==Tab.noType) {
				//ako je povratna vrednost void i nema return-a OK
				Code.put(Code.exit);
				Code.put(Code.return_);
			}else {
				//ako je povratna vrednost!=null, a ne vraca se nista
				//runtime error
				Code.put(Code.trap);
				Code.put(1);
			}
		}
		returnStmtFound=false;currMethod=null; 
	}
    //MethodDeclClass ::= (MethodDeclClass) TypeOrVoid LPAREN AdditionalFormPars RPAREN OptionalMethVarDeclL LBRACE StatementList RBRACE;
    List<Obj> listaMetodaUKlasama=new ArrayList<>();
    public void visit(MethodDeclClass mdc) {
    	if(!returnStmtFound) {
			Struct mdStruct=mdc.obj.getType();
			if(mdStruct==Tab.noType) {
				//ako je povratna vrednost void i nema return-a OK
				Code.put(Code.exit);
				Code.put(Code.return_);
			}else {
				//ako je povratna vrednost!=null, a ne vraca se nista
				//runtime error
				
				Code.put(Code.trap);
				Code.put(1);
			}
		}
    	listaMetodaUKlasama.add(currMethod);
		returnStmtFound=false;currMethod=null; 
    }
    /***********************EXPR*********************************/
    /**
     * 
     * Expr ::= (Expr) AdditionalMinus Term OptionalAddopTerm;

		AdditionalMinus ::= (AdditMinus) MINUS
							|
							(NoAdditMinus) \/*epsilon*\/
							;
		OptionalAddopTerm ::= (OptAddTerm) OptionalAddopTerm Addop Term
							  |
							  (NoOptAddTerm) \/*epsilon*\/
							  ;
		Addop ::= (AddOpPlus) PLUS
		  |
		  (AddOpMinus) MINUS
		  ;
     */
    
    public void visit(AdditMinus am) {//////!!!!!!!!proveri kasnije je l radi
    	//Code.put(Code.neg);
    }
    //ispravljeno AdditMinus treba dodati neg odmah nakon postavljanja broja a ne nekad posle
    public void visit(ExprMinusFlag emf) {
    	if(((Expr)emf.getParent()).getAdditionalMinus() instanceof AdditMinus) {
    		Code.put(Code.neg);
    	}
    }
    public void visit(OptAddTerm oat) {
    	if(oat.getAddop() instanceof AddOpPlus) Code.put(Code.add);
    	else if(oat.getAddop() instanceof AddOpMinus) Code.put(Code.sub);
    }
	
    /************************TERM***************************/
    /**
     * Term ::= (Term) Factor OptionalTermMulop;

		OptionalTermMulop ::= (OptTermMul) OptionalTermMulop Mulop Factor
							  |
							  (NoOptTermMul) \/*epsilon*\/
							  ;
		Mulop ::= (MulopMul) MUL
		   |
		   (MulopDiv) DIV
		   |
		   (MulopPercent) PERCENT
		   ;
		   
     */
    
    public void visit(OptTermMul otm) {
    	if(otm.getMulop() instanceof MulopMul) Code.put(Code.mul);
    	else if(otm.getMulop() instanceof MulopDiv) Code.put(Code.div);
    	else if(otm.getMulop() instanceof MulopPercent) Code.put(Code.rem);
    }
    
    /**********************FACTOR*****************************/
    /**
     * FactorDesignator ::= (FactorDesignator) Designator;
		Factor ::= (FactorDesign) FactorDesignator LPAREN AdditionalActParOp RPAREN
					|
					(FactorIdent) Designator
					|
					(FactorNum) NUMCONST:val
					|
					(FactorChar) CHARCONST:val
					|
					(FactorBool) BOOLCONST:val
					|
					(FactorNew) NEW Type OrExprActPars
					|
					(FactorExpr) LPAREN Expr RPAREN
					;
	   
     */
    
    Integer numOfElemInArray=null;//za [a,b,*c]=[1,2,3], poslednje
    public void visit(FactorNum fn) {
    	numOfElemInArray=fn.getVal();
    	Code.loadConst(fn.getVal());
    }
    
    public void visit(FactorChar fc) {
    	Code.loadConst((int)fc.getVal());
    }
    
    public void visit(FactorBool fb) {
    	Code.loadConst((fb.getVal()==true)?1:0);
    }
    
    public void visit(FactorIdent fi) {
    	if(max==true) {
			max=false;
			return;
		}
    	Code.load(fi.getDesignator().obj);
    }
    
    public void visit(FactorExpr fe) {//prebaci u semanticku
    	fe.struct=fe.getExpr().struct;
    }
    
    private Stack<Obj> stekPozvanihFunkcija=new Stack<>();//
    public void visit(FactorDesignator fd) {
    	SyntaxNode node=fd.getParent();
    	//ovde je greska
    	FactorDesign factor=(FactorDesign)node;
    	if(listaMetodaUKlasama.contains(factor.getFactorDesignator().getDesignator().obj)) {
    		
    	}else {
    		//pozivGlobalneFunkcije(fd.getDesignator().obj);
    	}
    }
    public void visit(FactorNew fn) {
    	
    	//ako se zove new nad klasnim objektom treba alocirati mesta koliko taj objekat ima polja-staticka
    	if(fn.getOrExprActPars() instanceof OrActPar) {
    		Code.put(Code.new_);//new s (alocira objekat) i ostavlja adresu alociranog objekta na steku
    		Integer brojPolja=fn.struct.getNumberOfFields()*4;
    		Code.put2(brojPolja);
    		//ugradjivanje tabele virtuelnih funkcija u objekat
    		
    		String nazivKlase;
    		
    		return;
    	}
    	Code.put(Code.newarray);
    	if(fn.struct.getElemType()==Tab.charType) Code.put(0);
    	else Code.put(1);
    }
    
    public void visit(FactorDesign fd) {
    	if(listaMetodaUKlasama.contains(fd.getFactorDesignator().getDesignator().obj)) {
    		//...
    	}else {
    		Obj obj=fd.getFactorDesignator().getDesignator().obj;
    		if(obj.getKind()==Obj.Meth) {
	    			if(obj.getName().equals("len")) {
	    				Code.put(Code.arraylength);
	    			}else if(!obj.getName().equals("ord") && !obj.getName().equals("chr")) {
		    			Code.put(Code.call);
		    			Code.put2(obj.getAdr()-Code.pc+1);
    			}
    		}
    	}
    }
    /*********************************DESIGNATOR***********************************/
    /**
     * Designator ::= (DesignatorM) DesignatorMatched
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
								(OrExpr) LSQUARE Expr RSQUARE
								;	
				DesUnmatchFlag ::= (DesUnmatchFlag) IDENT:name COLON COLON ;
     */
    
    DesPom pomDes=null;
    public void visit(DesPom ds) {
    	SyntaxNode par=((DesMatch)ds.getParent()).getParent();
    	if(/////VRV OVD IMA NEKA GRESKA !!!!!!!!!!!!!!!!!!!---Treba dodati vrv proveru da li je designatorStatement u drugom delu fora za ovo jer se on izvrsava nakon for petlje
    			(ds.obj.getKind()==Obj.Meth && listaMetodaUKlasama.contains(ds.obj) && par instanceof FactorDesignator)
    			||
    			(ds.obj.getKind()==Obj.Fld && (par instanceof DesignatorM || par instanceof DesignatorU || par instanceof DesStmtE ))
    	  ) {
    		//Code.put(Code.load_n+0);//load_0 malo drugacije napisano
    	}
    	pomDes=ds;
    	if(ds.obj.getType().getKind()==Struct.Array) {
    		//hashOfIndexesOfArrays.put(ds.getName(), new Stack());
    	}
    }
    boolean max=false;
    public void visit(OptDesignOpp odo) {
    	OrIdentExpr orIdentExpr=odo.getOrIdentExpr();
    	if(orIdentExpr.getClass()==OrIdent.class) {//a.b.b.bb.
    		if(max==true) {
    			max=false;
    			return;
    		}
    		Code.load(odo.getOptionalDesignatorOpp().obj);
    	}else if(orIdentExpr.getClass()==OrExpr.class) {//a.b[c]
    		
    		//if(odo.getOptionalDesignatorOpp().obj==null) return;//privremeno
    		SyntaxNode parent=odo.getParent();
    		//DesMatch dm=(DesMatch)parent;//ovo isto sam zakomentarisao jer sam mejlao semanticku analizu
    		
    	    //Code.load(dm.getDesPom().obj);//ovo treba prepraviti nesto mi ugrozava pythonList
    	}else if(orIdentExpr.getClass()==MaxArray.class) {
    		
    		max=true;
    		Obj niz=odo.getOptionalDesignatorOpp().obj;
    		//Code.load(niz);
    		//Code.put(Code.arraylength);
    		//promenjljiva1=0;
    		Code.loadConst(0);
    		Code.store(SemanticAnalyzer.promenjljiva1);
    		
    		//promenjljiva2=niz[0]
    		Code.load(niz);
    		Code.loadConst(0);
    		Code.put(Code.aload);
    		Code.store(SemanticAnalyzer.promenjljiva2);
    		
    		int jmp=Code.pc;
    		//if(promenjljiva1<len(niz))
    		Code.load(SemanticAnalyzer.promenjljiva1);
    		Code.load(niz);
    		Code.put(Code.arraylength);
    		Code.put(Code.jcc+Code.ge);
        	Code.put2(37);
    		
    		int adr=Code.pc+1;
    		
    		//if(promenljiva2<niz[promenjljiva1])
    		Code.load(SemanticAnalyzer.promenjljiva2);
    		
    		Code.load(niz);
    		Code.load(SemanticAnalyzer.promenjljiva1);
    		Code.put(Code.aload);
    		
    		
    		Code.put(Code.jcc+Code.ge);
        	Code.put2(13);
    		
    		int adr2=Code.pc+1;
    		
    		//promenjljiva2=niz[proemenjljiva1]
    		Code.load(niz);
    		Code.load(SemanticAnalyzer.promenjljiva1);
    		Code.put(Code.aload);
    		Code.store(SemanticAnalyzer.promenjljiva2);
    		
    		
    		//Code.fixup(adr2);
    		
    		//promenljliva1=promenjljiva1+1
    		Code.load(SemanticAnalyzer.promenjljiva1);
    		Code.loadConst(1);
    		Code.put(Code.add);
    		Code.store(SemanticAnalyzer.promenjljiva1);
    		
    		
    		//Code.fixup(adr);
    		Code.putJump(jmp);
    		
    		
    		Code.load(SemanticAnalyzer.promenjljiva2);
    		
    		
    	
    	}
    }
    
    public void visit(OrIEFlag oieff) {
    	
    	Code.load(pomDes.obj);//treba posle realizovati nesto slicno pom des kao stek vrv !!!!!!!!!!!! i ovde to ispraviti
    }
    
    //!!!!!!!!!!!!!!!!!!TREBA DOPUNITI
    public void visit(NoOptDesignOpp nodo) {
    	
    	nodo.obj=pomDes.obj;
    }
    
    
    
    /*********************************DESIGNATOR STATEMENT*************************/
    //POZIV FUNKCIJE
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
		OptionalDesignComma ::= (OptDesCom) OptionalDesignComma AdditionalDesign COMMA
						|
						(NooOptDesCom) \/*epsilon*\/
						;
		AdditionalDesign ::= (AdditionalDes) Designator
					 |
					 (NoAdditionalDes) \/*epsilon*\/
					 ;
	     */
    //poziv metode posebno uradi !!!!!!!!!!!!!!!!!kASNIJE
    
    public void pozivGlobalneFunkcije(Obj obj) {
    	String name=obj.getName();
    	
    	if(name.equals("len")) {
    		Code.put(Code.arraylength);
    		return;
    	}
    	
    	if(name.equalsIgnoreCase("ord")) {
    		return;
    	}
    	
    	if(name.equals("chr")) {
    		return;
    	}
    	
    	Code.put(Code.call);
    	//racunanje adrese funkcije
    	int adr=obj.getAdr();
    	Code.put2(adr-Code.pc+1);//offset za koliko da se pomerimo da bi stigli na pocetak funkcije
    }
    
    /**
     * 
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
     */
    public void visit(DesStmtE dse) {
    	if(dse.getOrDesStmt().getClass()==OrActPars.class) {
    		Obj funcDesig=dse.getDesignator().obj;
    		//treba dodati poziv funkcije za metode!!!!!!!!!!!!!!!!!!!!!
    		//kad ispravlkjas ovo stavi u else deo
    		pozivGlobalneFunkcije(funcDesig);
    		//posto se kod designator statementa povratna vrednost funkcije ne dodeljuje povratnu
    		//vrednost samo skidamo sa steka
    		//dodatno proveravamo da nije Tab.noType da ne bi dva puta skidali sa steka
    		
    		
    		if(dse.getDesignator().obj.getType()!=Tab.noType) {
    			Code.put(Code.pop);
    		}
    		//stekPozvanihFunkcija.pop(); kasnije
    	}else if(dse.getOrDesStmt().getClass()==OrAssignExpr.class) {
    		if(dse.getDesignator().obj.getType().getKind()==Struct.Array) {
    			hashOfArrays.put(dse.getDesignator().obj, numOfElemInArray);
    		}
    		if(max==true) {
    			max=false;
    			Code.put(Code.pop);
    			
    		}
    		Code.store(dse.getDesignator().obj);
    	}else if(dse.getOrDesStmt().getClass()==OrIncrement.class) {
    		if(dse.getDesignator().obj.getKind()==Obj.Fld) {
    			Code.put(Code.dup);
    		}else if(dse.getDesignator().obj.getKind()==Obj.Elem) {
    			Code.put(Code.dup2);
    		}
    		Code.load(dse.getDesignator().obj);
    		Code.loadConst(1);
    		Code.put(Code.add);
    		Code.store(dse.getDesignator().obj);
    	}else if(dse.getOrDesStmt().getClass()==OrDecrement.class) {
    		if(dse.getDesignator().obj.getKind()==Obj.Fld) {
    			Code.put(Code.dup);
    		}else if(dse.getDesignator().obj.getKind()==Obj.Elem) {
    			Code.put(Code.dup2);
    		}
    		Code.load(dse.getDesignator().obj);
    		Code.loadConst(1);
    		Code.put(Code.sub);
    		Code.store(dse.getDesignator().obj);
    	}
    }
    
    
    List<Obj> pythonList=new ArrayList<>();
    public void visit(AdditionalDes ad) {
    	pythonList.add(ad.getDesignator().obj);
    }
    
    public void visit(NoAdditionalDes nad) {
    	pythonList.add(null);
    	curr++;
    }
    
    int curr=0;
    public void visit(DesStmtH dsh) {
    	//prvo treba uporediti da li je duzina elemenata prve liste + poslednji niz jednala
    	//duzini niza sa druge strane =
    	//duzina leve strane
    	/**
    	for(Obj o:pythonList) {
    		if(o.getType().getKind()==Struct.Array) {
    			Code.load(o);
    			Code.put(Code.arraylength);
    			break;
    		}
    	}
    	*/
    	
    	
    	
    	Code.load(dsh.getDesignator().obj);
    	Code.put(Code.arraylength);
    	Code.loadConst(pythonList.size());
    	Code.put(Code.add);
    	//duzina desne strane
    	Code.load(dsh.getDesignator1().obj);
    	Code.put(Code.arraylength);
    	//ako je duzina ok skoci,, u suprotnom treap
    	Code.put(Code.jcc+Code.le);
    	Code.put2(5);
    	Code.put(Code.trap);
    	Code.put(2);
    	
    	int elemCnt=0;
    	for(Obj obj:pythonList) {
    		if(obj!=null && obj.getType().getKind()!=Struct.Array) {
    			
    			//curr+=1;
    			elemCnt+=1;
    		}
    		if(obj==null) {
    			//curr++;
    		}
    		if(obj!=null && obj.getKind()==Obj.Elem) {
    			/**
    			Code.load(obj);
    			Code.put(curr-elemCnt);
    			Code.load(dsh.getDesignator1().obj);
    			Code.loadConst(curr);
    			Code.put(Code.aload);
    			curr+=1;
    			Code.put(Code.astore);
    			*/
    			//curr+=1;
    			/**
    			Code.load(obj);
    			Code.loadConst(curr);
    			Code.load(dsh.getDesignator1().obj);
    			Code.loadConst(pythonList.indexOf(obj));
    			Code.put(Code.aload);
    			Code.put(Code.astore);
    			*/
    		}
    		//curr++;
    	}
    	//dsh.getDesignator().obj.get
    	pythonList.clear();
    	//treba jos dodati kopiranje ostatka elemenana iz niza sa desne u niz sa leve str
    	int size=curr;
    	int numOfElInLeftArray=hashOfArrays.get(dsh.getDesignator().obj);//ako ne radi onda stavi dsh.getDesignator().obj
    	for(int i=curr;i<numOfElInLeftArray+size;i++) {//numOfElemInArray+size?//a ovde: i<numOfElInRightArray+1
    		Code.load(dsh.getDesignator().obj);
    		Code.loadConst(i-size);
    		Code.load(dsh.getDesignator1().obj);
    		Code.loadConst(i);
    	    Code.put(Code.aload);
    	    Code.put(Code.astore);
    	}
    	numOfElemInArray=0;
    	curr=0;
    }
    
    Designator desright=null;//niz na desnoj strani
    public void visit(DesHardStart dhs) {
    	DesStmtH dsh=(DesStmtH) dhs.getParent();
    	desright=dsh.getDesignator1();
    	
    	Code.load(dsh.getDesignator().obj);
    	Code.put(Code.arraylength);
    	Code.loadConst(pythonList.size());
    	Code.put(Code.add);
    	//duzina desne strane
    	Code.load(dsh.getDesignator1().obj);
    	Code.put(Code.arraylength);
    	//ako je duzina ok skoci,, u suprotnom treap
    	Code.put(Code.jcc+Code.le);
    	Code.put2(5);
    	Code.put(Code.trap);
    	Code.put(2);
    }
    public void visit(DesHardAfter dha) {
    	Code.load(desright.obj);
		Code.loadConst(curr++);
		Code.put(Code.aload);
		Code.store(((AdditionalDes)dha.getParent()).getDesignator().obj);
    }
    /***********************CLASS DECL**************************/
    
    /**
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
			
			AdditionalMethodClassDecl ::= (AddClassMeth) LBRACE MethodDeclClassList RBRACE
										  |
										  (NoAddClassMeth) \/*epsilon*\/
										  ;
     */
    
       Obj currClass=null;
       HashMap<Obj,Integer> hashOfClasses=new HashMap<>();
       
	   public void visit(ClassDeclBegin cb) {
		   this.currClass=cb.obj;
	   }
	   
	   public void visit(ClassDeclaration cd) {
		   //kad obidjemo celu klasu stavljamo je u hash mapu
		   hashOfClasses.put(currClass, -1); currClass=null;//postavljamo klasu u hash listu klasa, tu cemo cuvati adresu pocetka VFT za tu klasu  
	   }
	   /************************ IF USLOVI *****************************/
	   /**
	    * Za matched se vec nalazi dole.
	    * Za unmatched:
	    * Unmatched ::= (IfUnmatched) IF IfBegin Condition IfEpsilon RPAREN Statement
			  |
			  (IfElseUnmatched) IF IfBegin Condition IfEpsilon RPAREN Matched ElseBegin Unmatched
			  ;
			  
	    */
	   
	   public void visit(IfBegin ib) {
		   //precage
		   andS.push(new ArrayList<>());
		   orS.push(new ArrayList<>());
		   elseS.push(new ArrayList<>());
	   }
	   
	   public void visit(IfUnmatched iu) {
		   List<Integer> andList=andS.pop();
		   //andS.clear();--mozda neka greska ako se ovo ukloni, ali vrv ne
		   orS.pop();
		   elseS.pop();
		   for(int a:andList) {
			   Code.fixup(a);
		   }
		   andList.clear();
	   }
	   
	   public void visit(IfElseUnmatched ieu) {
		   List<Integer> elseList=elseS.pop();
		   orS.pop();
		   andS.pop();
		   for(Integer e:elseList) {
			   Code.fixup(e);
		   }
		   elseList.clear();
		   
	   }
	   
	   public void visit(IfElseMatched iem) {
		   List<Integer> elseList=elseS.pop();
		   
		   orS.pop();
		   andS.pop();
		   for(Integer e:elseList) {
			   Code.fixup(e);
		   }
		   elseList.clear();
	   }
	   
	   public void visit(ElseBegin eb) {
		   List<Integer> elseList=elseS.pop();
		   elseList.add(Code.pc+1);
		   elseS.push(elseList);
		   Code.putJump(0);
		   List<Integer> andList=andS.pop();
		   for(int a:andList) {
			   Code.fixup(a);
		   }
		   andList.clear();
		   andS.push(andList);
	   }
	   /************************FOR_PETLJA*****************************/
	   /**
	    * 
	    * Matched ::= (DesStm) DesignatorStatement SEMICOLON
			|
			(IfElseMatched) IF IfBegin Condition IfEpsilon RPAREN Matched ElseBegin Matched
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
	
			ForBegin ::= (ForBegin)	ForStart LPAREN AdditionalDesignatoStmtList LoopStart SEMICOLON AdditionalCondFact ForFlag SEMICOLON AdditionalDesignatoStmtList ForFlagEnd RPAREN;
			ForEnd ::= (ForEnd) \/*epsilon*\/;
			
			ForStart ::= (ForStart) FOR;
			LoopStart ::= (LoopStart) \/*epsilon*\/;
			AdditionalDesignatoStmtListAfterFor ::= (AdditionalDesignatoStmtListAfterFor) AdditionalDesignatoStmtList;
			ForFlag ::= (ForFlag) \/*epsilon*\/;
			ForFlagEnd ::= (ForFlagEnd) \/*epsilon*\/;
	    */
	   
	   //PATCHING STACKS
	   Stack<Integer> forS=new Stack<>();
	   Stack<List<Integer>> breakS=new Stack<>();
	   Stack<List<Integer>> elseS=new Stack<>();
	   Stack<List<Integer>> andS=new Stack<>();
	   Stack<List<Integer>> orS=new Stack<>();
	   
	   public void visit(LoopStart fs) {
		   //postavljanje trenutne vrednosti pc-ija jer ce se na njega ponovo skakati
		   //dok je ispunjen uslov na pocetku petlje
		   forS.push(Code.pc);
		   //pregrade
		   andS.push(new ArrayList<>());
		   orS.push(new ArrayList<>());
		   elseS.push(new ArrayList<>());
		   breakS.push(new ArrayList<>());
		   //kasnije dodato
		   endLoopEndS.push(Code.pc);
	   }
	   
	   Integer endLoopEnd=0;
	   Stack<Integer> startLoopEndS=new Stack<Integer>();//for(ForStart Design;LoopStart Cond ForFlag; Desig ForFlagEnd){
	   Stack<Integer> endLoopEndS=new Stack<Integer>();  //	Stmt
	   													// }ForEnd
	   public void visit(ForFlagEnd ffe) {
		   //endLoopEnd=Code.pc+1;
		   //kasnije dodato
		   Code.putJump(endLoopEndS.pop());
	   }
	   
	   
	   public void visit(ForBegin fb) {  
		   Code.fixup(endLoopStart);
	   }
	   public boolean EndLoop=false;
	   
	   Integer endLoopStart=null;
	   public void visit(ForFlag ff) {
		   EndLoop=true;
		   endLoopStart=Code.pc+1;
		   Code.putJump(0);
		   //kasnije dodato
		   startLoopEndS.push(Code.pc);
	   }
	   
	   public void visit(StmtFor sf) {
		   List<Integer> orList=orS.pop();
		   List<Integer> andList=andS.pop();
		   List<Integer> breakList=breakS.pop();
		   List<Integer> elseList=elseS.pop();
		   //forS.pop();
		   //Code.putJump(forS.pop());//vracamo se na pocetak for petlje
		   //kasnije izmnjeno 
		   Code.putJump(startLoopEndS.pop());
		   for(Integer a:andList) {
			   Code.fixup(a);//patch
		   }
		   
		   for(Integer b:breakList) {
			   Code.fixup(b);//patch
		   }
	   }
	   
	   public void visit(StmtFor2 sf2) {
		   List<Integer> orList=orS.pop();
		   List<Integer> andList=andS.pop();
		   List<Integer> breakList=breakS.pop();
		   List<Integer> elseList=elseS.pop();
		   //forS.pop();
		   //Code.putJump(forS.pop());//vracamo se na pcetak for petlje
		   //kasnije dodato
		   Code.putJump(startLoopEndS.pop());
		   
		   for(Integer a:andList) {
			   Code.fixup(a);//patch
		   }
		   
		   for(Integer b:breakList) {
			   Code.fixup(b);//patch
		   }
	   }
	   /************************BREAK*********************************/
	   public void visit(StmtBreak sb) {
		   //ovde ne znamo trenutno gde je kraj, pa cemo kasnije patchovati
		   List<Integer> breakList=breakS.pop();
		   breakList.add(Code.pc+1);
		   breakS.push(breakList);
		   
		   
		   Code.putJump(0);//kasnije stavljamo stvarnu adresu skoka
		   
	   }
	   /************************CONTINUE*****************************/
	   public void visit(StmtContinue sc) {
		   Code.putJump(startLoopEndS.peek());
	   }
	   
	   /***********************COND TERM*****************************/
	   /**
	    * 
	    */
	   
	   /************************CONDITION****************************/
	   /**
	    * Condition ::= (Condition) CondTerm OptionalCondTerm;

			OptionalCondTerm ::= (OptCondTerm) OptionalCondTerm CondOrBegin OR CondTerm
								  |
								  (NoOptCondTerm) \/*epsilon*\/
								  ;
	    */
	   
	   public void visit(CondOrBegin cob) {
		   List<Integer> orList=orS.pop();
		   orList.add(Code.pc+1);
		   orS.push(orList);
		   Code.putJump(0);
		   
		   List<Integer> andList=andS.pop();
		   for(int a:andList) {
			   Code.fixup(a);
		   }
		   andList.clear();
		   andS.push(andList);
	   }
	   
	   public void visit(IfBeginEnd ibe) {
		   List<Integer> orList=orS.pop();
		   for(int o:orList) {
			   Code.fixup(o);
		   }
		   orList.clear();
		   orS.push(orList);
	   }
	   /***********************COND FACT****************************/
	   /**
	    * CondFact ::= (CondFact) Expr AdditionalRelopExpr;

			AdditionalRelopExpr ::= (AddRelopExpr) Relop Expr
									|
									(NoAddRelopExpr) \/*epsilon*\/
									;
			Relop ::= (RelEqEq) EQUALS_EQUALS
		  |
		  (RelNotEq) NOT_EQUALS
		  |
		  (RelGrt) GT
		  |
		  (RelGrtEq) GTE
		  |
		  (RelLt) LT
		  |
		  (RelLtEq) LTE
		  ;		
						
	    */
	   public void visit(AddRelopExpr are) {
		   List<Integer> andList=andS.pop();
		   andList.add(Code.pc+1);
		   andS.push(andList);
		   Relop relop=are.getRelop();
		   //postavlamo adrese na 0 pa ih kasnije patchujem
		   if(relop.getClass()==RelEqEq.class) {
			   Code.putFalseJump(Code.eq, 0);
		   }else if(relop.getClass()==RelNotEq.class) {
			   Code.putFalseJump(Code.ne, 0);
		   }else if(relop.getClass()==RelGrt.class) {
			   Code.putFalseJump(Code.gt, 0);
		   }else if(relop.getClass()==RelGrtEq.class) {
			   Code.putFalseJump(Code.ge, 0);
		   }else if(relop.getClass()==RelLt.class) {
			   Code.putFalseJump(Code.lt, 0);
		   }else if(relop.getClass()==RelLtEq.class) {
			   Code.putFalseJump(Code.le, 0);
		   }
		   
	   }
	   
	   public void visit(NoAddRelopExpr nae) {
		   Code.loadConst(1);//for(;true;){}
		   List<Integer> andLista=andS.pop();
		   andLista.add(Code.pc+1);
		   andS.push(andLista);
		   Code.putFalseJump(Code.eq, 0);//fromalno patch iako ne iskacemo
	   }
	   
	   /* MAX ELEM */
	   
	   public void visit(MaxArray ma) {
		   OptDesignOpp op=(OptDesignOpp)ma.getParent();
		   
	   }
	   
	   /** for(a:arr){}*/
	   /**for( i: arr){}
		 * ForModBegin ::= (ForModBegin) ForStart LPAREN ForVar ForArrayMod;
		   ForVar ::= (ForVar) IDENT: name COLON;
		   ForArrayMod ::= (ForArrayMod) IDENT:name RPAREN;
		 */
	   Stack<Integer> forModBegin=new Stack<>();
	   Stack<Integer> forModEnd=new Stack<>();
	   
	   public void visit(ForVar fv) {
		   SyntaxNode node=fv.getParent();
		   ForArrayMod fam=((ForModBegin)node).getForArrayMod();
		   
		   //promenjljiva a
		   Obj objVar=fv.obj;
		   //promenjljiva arr
		   Obj objArr=fam.obj;
		   
		   
		   Code.load(objArr);
		   forModBegin.push(Code.pc+1);
		   breakS.push(new ArrayList<>());
		   
		   Code.loadConst(-1);
		   Code.loadConst(1);
		   Code.put(Code.add);
		   Code.put(Code.dup2);
		  // Code.put(Code.dup2);
		   Code.put(Code.pop);
		   Code.put(Code.arraylength);
		   Code.put(Code.dup2);
		   Code.put(Code.pop);
		   Code.put(Code.dup_x1);
		   Code.put(Code.pop);
		   
		   //Code.putFalseJump(Code.le, Code.pc-1);
		   forModEnd.push(Code.pc+1);
		   Code.put(Code.jcc+Code.ge);
		   Code.put(0);
		   
		   Code.put2(Code.dup2);
		   
		   
		   
		   Code.put(Code.aload);
		   Code.store(objVar);
		   
	   }
	   
	   public void visit(ForArr fmb) {
		   List<Integer> breakList=breakS.pop();
		   Code.putJump(forModBegin.pop());
		   Code.fixup(forModEnd.pop());
		   
		   for(int b:breakList) {
			   Code.fixup(b);
		   }
		   Code.put(Code.pop);
		   Code.put(Code.pop);

	   }
	   
	   public void visit(ForArr2 fmb) {
		   List<Integer> breakList=breakS.pop();
		   Code.putJump(forModBegin.pop());
		   Code.fixup(forModEnd.pop());
		   
		   for(int b:breakList) {
			   Code.fixup(b);
		   }
		   Code.put(Code.pop);
		   Code.put(Code.pop);
		   
	   }
	   
	   /**WHILE*/
	   
	   Stack<Integer> whileList=new Stack<>();
	   public void visit(WhileBegin b) {
		   whileList.push(Code.pc);
		   andS.push(new ArrayList<>());
		   orS.push(new ArrayList<>());
		   elseS.push(new ArrayList<>());
		   breakS.push(new ArrayList<>());
	   }
	   
	   public void visit(While w) {
		   List<Integer> orList=orS.pop();
		   List<Integer> andList=andS.pop();
		   List<Integer> breakList=breakS.pop();
		   List<Integer> elseList=elseS.pop();
		   
		   
		   Code.putJump(whileList.pop());
		   
		   for(Integer a:andList) {
			   Code.fixup(a);//patch
		   }
		   
		   for(Integer b:breakList) {
			   Code.fixup(b);//patch
		   }
	   }
	   
	   //modifikacija 3
	   // niz.foreach(a=>{print(a);});
	   
	   Stack<Integer> foreachStack=new Stack<>();
	   Stack<Integer> foreachBegin=new Stack<>();
	   public void visit(FeVar fv) {
		   Obj promenjljiva=fv.obj;
		   Obj niz= ((ForeachStmt)fv.getParent()).getDesignatorFE().obj;
		   
		   Code.load(niz);
		   Code.loadConst(-1);
		   foreachBegin.push(Code.pc);
		   Code.loadConst(1);
		   Code.put(Code.add);
		   Code.put(Code.dup2);
		   Code.put(Code.pop);
		   Code.put(Code.arraylength);
		   Code.put(Code.dup2);
		   Code.put(Code.pop);
		   Code.put(Code.dup_x1);
		   Code.put(Code.pop);
		   
		   foreachStack.push(Code.pc+1);
		   Code.putFalseJump(Code.lt, 0);
		   //Code.put(0);
		   
		   Code.put(Code.dup2);
		   if(promenjljiva.getType().getKind()==Struct.Int) {
			   Code.put(Code.aload);
		   }else {
			   Code.put(Code.baload);
		   }
		   
		   Code.store(promenjljiva);
		   
	   }
	   
	   public void visit(ForeachStmt fs) {
		   Code.putJump(foreachBegin.pop());
		   Code.fixup(foreachStack.pop());
		   Code.put(Code.pop);
		   Code.put(Code.pop);
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
	   
	   Stack<Integer> dowhileBegin=new Stack<>();
	   
	   public void visit(DoWhileBegin dwb) {
		   dowhileBegin.push(Code.pc);
		   
		   andS.push(new ArrayList<>());
		   orS.push(new ArrayList<>());
		   elseS.push(new ArrayList<>());
		   breakS.push(new ArrayList<>());
	   }
	   
	   public void visit(DoWhile1 dw) {
		   Code.putJump(dowhileBegin.pop());
		   
		   List<Integer> orList=orS.pop();
		   List<Integer> andList=andS.pop();
		   List<Integer> breakList=breakS.pop();
		   List<Integer> elseList=elseS.pop();
		   
		   
		   for(Integer a:andList) {
			   Code.fixup(a);//patch
		   }
		   
		   for(Integer b:breakList) {
			   Code.fixup(b);//patch
		   }
	   }
	   
	   
}
