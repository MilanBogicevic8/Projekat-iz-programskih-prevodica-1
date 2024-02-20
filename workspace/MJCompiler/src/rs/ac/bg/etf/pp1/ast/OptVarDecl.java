// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptVarDecl extends OptionalVarDecl {

    private OptionalVarDecl OptionalVarDecl;
    private VarDeclClass VarDeclClass;

    public OptVarDecl (OptionalVarDecl OptionalVarDecl, VarDeclClass VarDeclClass) {
        this.OptionalVarDecl=OptionalVarDecl;
        if(OptionalVarDecl!=null) OptionalVarDecl.setParent(this);
        this.VarDeclClass=VarDeclClass;
        if(VarDeclClass!=null) VarDeclClass.setParent(this);
    }

    public OptionalVarDecl getOptionalVarDecl() {
        return OptionalVarDecl;
    }

    public void setOptionalVarDecl(OptionalVarDecl OptionalVarDecl) {
        this.OptionalVarDecl=OptionalVarDecl;
    }

    public VarDeclClass getVarDeclClass() {
        return VarDeclClass;
    }

    public void setVarDeclClass(VarDeclClass VarDeclClass) {
        this.VarDeclClass=VarDeclClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalVarDecl!=null) OptionalVarDecl.accept(visitor);
        if(VarDeclClass!=null) VarDeclClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDecl!=null) OptionalVarDecl.traverseTopDown(visitor);
        if(VarDeclClass!=null) VarDeclClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDecl!=null) OptionalVarDecl.traverseBottomUp(visitor);
        if(VarDeclClass!=null) VarDeclClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptVarDecl(\n");

        if(OptionalVarDecl!=null)
            buffer.append(OptionalVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclClass!=null)
            buffer.append(VarDeclClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptVarDecl]");
        return buffer.toString();
    }
}
