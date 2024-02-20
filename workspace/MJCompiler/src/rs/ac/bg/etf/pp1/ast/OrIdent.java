// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OrIdent extends OrIdentExpr {

    private String iden;

    public OrIdent (String iden) {
        this.iden=iden;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden=iden;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrIdent(\n");

        buffer.append(" "+tab+iden);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrIdent]");
        return buffer.toString();
    }
}
