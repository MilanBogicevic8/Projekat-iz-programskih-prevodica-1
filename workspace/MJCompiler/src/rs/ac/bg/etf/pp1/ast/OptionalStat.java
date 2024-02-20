// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalStat extends OptionalStatic {

    private OptionalStatic OptionalStatic;
    private VarDeclStatic VarDeclStatic;
    private StaticStartDef StaticStartDef;

    public OptionalStat (OptionalStatic OptionalStatic, VarDeclStatic VarDeclStatic, StaticStartDef StaticStartDef) {
        this.OptionalStatic=OptionalStatic;
        if(OptionalStatic!=null) OptionalStatic.setParent(this);
        this.VarDeclStatic=VarDeclStatic;
        if(VarDeclStatic!=null) VarDeclStatic.setParent(this);
        this.StaticStartDef=StaticStartDef;
        if(StaticStartDef!=null) StaticStartDef.setParent(this);
    }

    public OptionalStatic getOptionalStatic() {
        return OptionalStatic;
    }

    public void setOptionalStatic(OptionalStatic OptionalStatic) {
        this.OptionalStatic=OptionalStatic;
    }

    public VarDeclStatic getVarDeclStatic() {
        return VarDeclStatic;
    }

    public void setVarDeclStatic(VarDeclStatic VarDeclStatic) {
        this.VarDeclStatic=VarDeclStatic;
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
        if(OptionalStatic!=null) OptionalStatic.accept(visitor);
        if(VarDeclStatic!=null) VarDeclStatic.accept(visitor);
        if(StaticStartDef!=null) StaticStartDef.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalStatic!=null) OptionalStatic.traverseTopDown(visitor);
        if(VarDeclStatic!=null) VarDeclStatic.traverseTopDown(visitor);
        if(StaticStartDef!=null) StaticStartDef.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalStatic!=null) OptionalStatic.traverseBottomUp(visitor);
        if(VarDeclStatic!=null) VarDeclStatic.traverseBottomUp(visitor);
        if(StaticStartDef!=null) StaticStartDef.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalStat(\n");

        if(OptionalStatic!=null)
            buffer.append(OptionalStatic.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclStatic!=null)
            buffer.append(VarDeclStatic.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticStartDef!=null)
            buffer.append(StaticStartDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalStat]");
        return buffer.toString();
    }
}
