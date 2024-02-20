// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class AddDesStmtL extends AdditionalDesignatoStmtList {

    private DesignatorStatement DesignatorStatement;
    private OptionalDesignatorStmtL OptionalDesignatorStmtL;

    public AddDesStmtL (DesignatorStatement DesignatorStatement, OptionalDesignatorStmtL OptionalDesignatorStmtL) {
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
        this.OptionalDesignatorStmtL=OptionalDesignatorStmtL;
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.setParent(this);
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public OptionalDesignatorStmtL getOptionalDesignatorStmtL() {
        return OptionalDesignatorStmtL;
    }

    public void setOptionalDesignatorStmtL(OptionalDesignatorStmtL OptionalDesignatorStmtL) {
        this.OptionalDesignatorStmtL=OptionalDesignatorStmtL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddDesStmtL(\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalDesignatorStmtL!=null)
            buffer.append(OptionalDesignatorStmtL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddDesStmtL]");
        return buffer.toString();
    }
}
