// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptStatInitialize extends OptionalStaticInitializer {

    private OptionalStaticInitializer OptionalStaticInitializer;
    private StaticInitializer StaticInitializer;

    public OptStatInitialize (OptionalStaticInitializer OptionalStaticInitializer, StaticInitializer StaticInitializer) {
        this.OptionalStaticInitializer=OptionalStaticInitializer;
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.setParent(this);
        this.StaticInitializer=StaticInitializer;
        if(StaticInitializer!=null) StaticInitializer.setParent(this);
    }

    public OptionalStaticInitializer getOptionalStaticInitializer() {
        return OptionalStaticInitializer;
    }

    public void setOptionalStaticInitializer(OptionalStaticInitializer OptionalStaticInitializer) {
        this.OptionalStaticInitializer=OptionalStaticInitializer;
    }

    public StaticInitializer getStaticInitializer() {
        return StaticInitializer;
    }

    public void setStaticInitializer(StaticInitializer StaticInitializer) {
        this.StaticInitializer=StaticInitializer;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.accept(visitor);
        if(StaticInitializer!=null) StaticInitializer.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.traverseTopDown(visitor);
        if(StaticInitializer!=null) StaticInitializer.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.traverseBottomUp(visitor);
        if(StaticInitializer!=null) StaticInitializer.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptStatInitialize(\n");

        if(OptionalStaticInitializer!=null)
            buffer.append(OptionalStaticInitializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticInitializer!=null)
            buffer.append(StaticInitializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptStatInitialize]");
        return buffer.toString();
    }
}
