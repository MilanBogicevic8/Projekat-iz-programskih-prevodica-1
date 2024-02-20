// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class StmtPrint extends Matched {

    private Expr Expr;
    private AdditionalPrintNum AdditionalPrintNum;

    public StmtPrint (Expr Expr, AdditionalPrintNum AdditionalPrintNum) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.AdditionalPrintNum=AdditionalPrintNum;
        if(AdditionalPrintNum!=null) AdditionalPrintNum.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public AdditionalPrintNum getAdditionalPrintNum() {
        return AdditionalPrintNum;
    }

    public void setAdditionalPrintNum(AdditionalPrintNum AdditionalPrintNum) {
        this.AdditionalPrintNum=AdditionalPrintNum;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(AdditionalPrintNum!=null) AdditionalPrintNum.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(AdditionalPrintNum!=null) AdditionalPrintNum.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(AdditionalPrintNum!=null) AdditionalPrintNum.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalPrintNum!=null)
            buffer.append(AdditionalPrintNum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtPrint]");
        return buffer.toString();
    }
}
