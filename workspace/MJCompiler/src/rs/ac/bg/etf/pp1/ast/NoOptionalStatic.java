// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class NoOptionalStatic extends OptionalStatic {

    private StaticStartDef StaticStartDef;

    public NoOptionalStatic (StaticStartDef StaticStartDef) {
        this.StaticStartDef=StaticStartDef;
        if(StaticStartDef!=null) StaticStartDef.setParent(this);
    }

    public StaticStartDef getStaticStartDef() {
        return StaticStartDef;
    }

    public void setStaticStartDef(StaticStartDef StaticStartDef) {
        this.StaticStartDef=StaticStartDef;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticStartDef!=null) StaticStartDef.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticStartDef!=null) StaticStartDef.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticStartDef!=null) StaticStartDef.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoOptionalStatic(\n");

        if(StaticStartDef!=null)
            buffer.append(StaticStartDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoOptionalStatic]");
        return buffer.toString();
    }
}
