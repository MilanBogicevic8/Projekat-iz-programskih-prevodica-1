// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptDesignOpp extends OptionalDesignatorOpp {

    private OptionalDesignatorOpp OptionalDesignatorOpp;
    private OrIdentExpr OrIdentExpr;

    public OptDesignOpp (OptionalDesignatorOpp OptionalDesignatorOpp, OrIdentExpr OrIdentExpr) {
        this.OptionalDesignatorOpp=OptionalDesignatorOpp;
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.setParent(this);
        this.OrIdentExpr=OrIdentExpr;
        if(OrIdentExpr!=null) OrIdentExpr.setParent(this);
    }

    public OptionalDesignatorOpp getOptionalDesignatorOpp() {
        return OptionalDesignatorOpp;
    }

    public void setOptionalDesignatorOpp(OptionalDesignatorOpp OptionalDesignatorOpp) {
        this.OptionalDesignatorOpp=OptionalDesignatorOpp;
    }

    public OrIdentExpr getOrIdentExpr() {
        return OrIdentExpr;
    }

    public void setOrIdentExpr(OrIdentExpr OrIdentExpr) {
        this.OrIdentExpr=OrIdentExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.accept(visitor);
        if(OrIdentExpr!=null) OrIdentExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.traverseTopDown(visitor);
        if(OrIdentExpr!=null) OrIdentExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.traverseBottomUp(visitor);
        if(OrIdentExpr!=null) OrIdentExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptDesignOpp(\n");

        if(OptionalDesignatorOpp!=null)
            buffer.append(OptionalDesignatorOpp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrIdentExpr!=null)
            buffer.append(OrIdentExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptDesignOpp]");
        return buffer.toString();
    }
}
