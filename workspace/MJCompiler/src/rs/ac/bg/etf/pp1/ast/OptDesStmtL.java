// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptDesStmtL extends OptionalDesignatorStmtL {

    private OptionalDesignatorStmtL OptionalDesignatorStmtL;
    private DesignatorStatement DesignatorStatement;

    public OptDesStmtL (OptionalDesignatorStmtL OptionalDesignatorStmtL, DesignatorStatement DesignatorStatement) {
        this.OptionalDesignatorStmtL=OptionalDesignatorStmtL;
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.setParent(this);
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
    }

    public OptionalDesignatorStmtL getOptionalDesignatorStmtL() {
        return OptionalDesignatorStmtL;
    }

    public void setOptionalDesignatorStmtL(OptionalDesignatorStmtL OptionalDesignatorStmtL) {
        this.OptionalDesignatorStmtL=OptionalDesignatorStmtL;
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.traverseTopDown(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignatorStmtL!=null) OptionalDesignatorStmtL.traverseBottomUp(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptDesStmtL(\n");

        if(OptionalDesignatorStmtL!=null)
            buffer.append(OptionalDesignatorStmtL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptDesStmtL]");
        return buffer.toString();
    }
}
