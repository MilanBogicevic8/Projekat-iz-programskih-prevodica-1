// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptCommaExpr extends OptionalComaExpr {

    private OptionalComaExpr OptionalComaExpr;
    private PomExprActPars PomExprActPars;

    public OptCommaExpr (OptionalComaExpr OptionalComaExpr, PomExprActPars PomExprActPars) {
        this.OptionalComaExpr=OptionalComaExpr;
        if(OptionalComaExpr!=null) OptionalComaExpr.setParent(this);
        this.PomExprActPars=PomExprActPars;
        if(PomExprActPars!=null) PomExprActPars.setParent(this);
    }

    public OptionalComaExpr getOptionalComaExpr() {
        return OptionalComaExpr;
    }

    public void setOptionalComaExpr(OptionalComaExpr OptionalComaExpr) {
        this.OptionalComaExpr=OptionalComaExpr;
    }

    public PomExprActPars getPomExprActPars() {
        return PomExprActPars;
    }

    public void setPomExprActPars(PomExprActPars PomExprActPars) {
        this.PomExprActPars=PomExprActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalComaExpr!=null) OptionalComaExpr.accept(visitor);
        if(PomExprActPars!=null) PomExprActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalComaExpr!=null) OptionalComaExpr.traverseTopDown(visitor);
        if(PomExprActPars!=null) PomExprActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalComaExpr!=null) OptionalComaExpr.traverseBottomUp(visitor);
        if(PomExprActPars!=null) PomExprActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptCommaExpr(\n");

        if(OptionalComaExpr!=null)
            buffer.append(OptionalComaExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PomExprActPars!=null)
            buffer.append(PomExprActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptCommaExpr]");
        return buffer.toString();
    }
}
