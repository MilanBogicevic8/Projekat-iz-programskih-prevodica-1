// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ActPars implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private PomExprActPars PomExprActPars;
    private OptionalComaExpr OptionalComaExpr;

    public ActPars (PomExprActPars PomExprActPars, OptionalComaExpr OptionalComaExpr) {
        this.PomExprActPars=PomExprActPars;
        if(PomExprActPars!=null) PomExprActPars.setParent(this);
        this.OptionalComaExpr=OptionalComaExpr;
        if(OptionalComaExpr!=null) OptionalComaExpr.setParent(this);
    }

    public PomExprActPars getPomExprActPars() {
        return PomExprActPars;
    }

    public void setPomExprActPars(PomExprActPars PomExprActPars) {
        this.PomExprActPars=PomExprActPars;
    }

    public OptionalComaExpr getOptionalComaExpr() {
        return OptionalComaExpr;
    }

    public void setOptionalComaExpr(OptionalComaExpr OptionalComaExpr) {
        this.OptionalComaExpr=OptionalComaExpr;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(PomExprActPars!=null) PomExprActPars.accept(visitor);
        if(OptionalComaExpr!=null) OptionalComaExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PomExprActPars!=null) PomExprActPars.traverseTopDown(visitor);
        if(OptionalComaExpr!=null) OptionalComaExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PomExprActPars!=null) PomExprActPars.traverseBottomUp(visitor);
        if(OptionalComaExpr!=null) OptionalComaExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActPars(\n");

        if(PomExprActPars!=null)
            buffer.append(PomExprActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalComaExpr!=null)
            buffer.append(OptionalComaExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActPars]");
        return buffer.toString();
    }
}
