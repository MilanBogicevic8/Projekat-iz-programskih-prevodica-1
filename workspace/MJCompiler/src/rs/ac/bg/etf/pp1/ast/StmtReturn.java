// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class StmtReturn extends Matched {

    private AdditionalExpr AdditionalExpr;

    public StmtReturn (AdditionalExpr AdditionalExpr) {
        this.AdditionalExpr=AdditionalExpr;
        if(AdditionalExpr!=null) AdditionalExpr.setParent(this);
    }

    public AdditionalExpr getAdditionalExpr() {
        return AdditionalExpr;
    }

    public void setAdditionalExpr(AdditionalExpr AdditionalExpr) {
        this.AdditionalExpr=AdditionalExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AdditionalExpr!=null) AdditionalExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalExpr!=null) AdditionalExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalExpr!=null) AdditionalExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtReturn(\n");

        if(AdditionalExpr!=null)
            buffer.append(AdditionalExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtReturn]");
        return buffer.toString();
    }
}
