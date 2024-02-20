// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class TypeOptDoubleColon extends Type {

    private String Nname;
    private String typeName;

    public TypeOptDoubleColon (String Nname, String typeName) {
        this.Nname=Nname;
        this.typeName=typeName;
    }

    public String getNname() {
        return Nname;
    }

    public void setNname(String Nname) {
        this.Nname=Nname;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName=typeName;
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
        buffer.append("TypeOptDoubleColon(\n");

        buffer.append(" "+tab+Nname);
        buffer.append("\n");

        buffer.append(" "+tab+typeName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TypeOptDoubleColon]");
        return buffer.toString();
    }
}
