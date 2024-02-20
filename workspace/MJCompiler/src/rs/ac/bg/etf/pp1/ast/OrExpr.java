// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OrExpr extends OrIdentExpr {

    private OrIEFlag OrIEFlag;
    private Expr Expr;

    public OrExpr (OrIEFlag OrIEFlag, Expr Expr) {
        this.OrIEFlag=OrIEFlag;
        if(OrIEFlag!=null) OrIEFlag.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public OrIEFlag getOrIEFlag() {
        return OrIEFlag;
    }

    public void setOrIEFlag(OrIEFlag OrIEFlag) {
        this.OrIEFlag=OrIEFlag;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OrIEFlag!=null) OrIEFlag.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OrIEFlag!=null) OrIEFlag.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OrIEFlag!=null) OrIEFlag.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrExpr(\n");

        if(OrIEFlag!=null)
            buffer.append(OrIEFlag.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrExpr]");
        return buffer.toString();
    }
}
