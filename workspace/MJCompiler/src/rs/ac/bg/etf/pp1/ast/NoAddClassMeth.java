// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class NoAddClassMeth extends AdditionalMethodClassDecl {

    private StaticEndDef StaticEndDef;

    public NoAddClassMeth (StaticEndDef StaticEndDef) {
        this.StaticEndDef=StaticEndDef;
        if(StaticEndDef!=null) StaticEndDef.setParent(this);
    }

    public StaticEndDef getStaticEndDef() {
        return StaticEndDef;
    }

    public void setStaticEndDef(StaticEndDef StaticEndDef) {
        this.StaticEndDef=StaticEndDef;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticEndDef!=null) StaticEndDef.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticEndDef!=null) StaticEndDef.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticEndDef!=null) StaticEndDef.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoAddClassMeth(\n");

        if(StaticEndDef!=null)
            buffer.append(StaticEndDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NoAddClassMeth]");
        return buffer.toString();
    }
}
