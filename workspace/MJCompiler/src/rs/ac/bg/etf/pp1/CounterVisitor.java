package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {

	protected int count=0;
	
	public int getCount() {
		return count;
	}
	
	//broj argumenata u funkciji/metodi
	public static class FormParamCounter extends CounterVisitor{
		
	}
	//broj promenjljivih
	public static class VarCounter extends CounterVisitor{
		
	}
}
