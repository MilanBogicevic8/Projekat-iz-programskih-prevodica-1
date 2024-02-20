// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ClassU extends ClassUnmat {

    private OptionalStatic OptionalStatic;
    private OptionalStaticInitializer OptionalStaticInitializer;
    private OptionalVarDecl OptionalVarDecl;
    private AdditionalMethodClassDecl AdditionalMethodClassDecl;

    public ClassU (OptionalStatic OptionalStatic, OptionalStaticInitializer OptionalStaticInitializer, OptionalVarDecl OptionalVarDecl, AdditionalMethodClassDecl AdditionalMethodClassDecl) {
        this.OptionalStatic=OptionalStatic;
        if(OptionalStatic!=null) OptionalStatic.setParent(this);
        this.OptionalStaticInitializer=OptionalStaticInitializer;
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.setParent(this);
        this.OptionalVarDecl=OptionalVarDecl;
        if(OptionalVarDecl!=null) OptionalVarDecl.setParent(this);
        this.AdditionalMethodClassDecl=AdditionalMethodClassDecl;
        if(AdditionalMethodClassDecl!=null) AdditionalMethodClassDecl.setParent(this);
    }

    public OptionalStatic getOptionalStatic() {
        return OptionalStatic;
    }

    public void setOptionalStatic(OptionalStatic OptionalStatic) {
        this.OptionalStatic=OptionalStatic;
    }

    public OptionalStaticInitializer getOptionalStaticInitializer() {
        return OptionalStaticInitializer;
    }

    public void setOptionalStaticInitializer(OptionalStaticInitializer OptionalStaticInitializer) {
        this.OptionalStaticInitializer=OptionalStaticInitializer;
    }

    public OptionalVarDecl getOptionalVarDecl() {
        return OptionalVarDecl;
    }

    public void setOptionalVarDecl(OptionalVarDecl OptionalVarDecl) {
        this.OptionalVarDecl=OptionalVarDecl;
    }

    public AdditionalMethodClassDecl getAdditionalMethodClassDecl() {
        return AdditionalMethodClassDecl;
    }

    public void setAdditionalMethodClassDecl(AdditionalMethodClassDecl AdditionalMethodClassDecl) {
        this.AdditionalMethodClassDecl=AdditionalMethodClassDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalStatic!=null) OptionalStatic.accept(visitor);
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.accept(visitor);
        if(OptionalVarDecl!=null) OptionalVarDecl.accept(visitor);
        if(AdditionalMethodClassDecl!=null) AdditionalMethodClassDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalStatic!=null) OptionalStatic.traverseTopDown(visitor);
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.traverseTopDown(visitor);
        if(OptionalVarDecl!=null) OptionalVarDecl.traverseTopDown(visitor);
        if(AdditionalMethodClassDecl!=null) AdditionalMethodClassDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalStatic!=null) OptionalStatic.traverseBottomUp(visitor);
        if(OptionalStaticInitializer!=null) OptionalStaticInitializer.traverseBottomUp(visitor);
        if(OptionalVarDecl!=null) OptionalVarDecl.traverseBottomUp(visitor);
        if(AdditionalMethodClassDecl!=null) AdditionalMethodClassDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassU(\n");

        if(OptionalStatic!=null)
            buffer.append(OptionalStatic.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalStaticInitializer!=null)
            buffer.append(OptionalStaticInitializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalVarDecl!=null)
            buffer.append(OptionalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalMethodClassDecl!=null)
            buffer.append(AdditionalMethodClassDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassU]");
        return buffer.toString();
    }
}
