package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import org.apache.log4j.*;
public class RuleVisitor extends VisitorAdaptor {

	Logger log=Logger.getLogger(getClass());
	
	int printCallCount=0;
	int constCount=0;
	//ovo je samo primer da kada se desi redukcija print statementa inkrementiramo broj printova u programu
	/**@Override
	public void visit(PrintStmt PrintStmt) {
		printCallCount++;
		log.info("Prepoznata print naredba!");
	}
	
	@Override
	public void visit(VarDecl varDecl){
		constCount++;
	}*/
}
