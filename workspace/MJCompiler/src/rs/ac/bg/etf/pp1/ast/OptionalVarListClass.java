// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalVarListClass extends OptionalVarDeclListClass {

    private OptionalVarDeclListClass OptionalVarDeclListClass;
    private NewVarDecl NewVarDecl;

    public OptionalVarListClass (OptionalVarDeclListClass OptionalVarDeclListClass, NewVarDecl NewVarDecl) {
        this.OptionalVarDeclListClass=OptionalVarDeclListClass;
        if(OptionalVarDeclListClass!=null) OptionalVarDeclListClass.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
    }

    public OptionalVarDeclListClass getOptionalVarDeclListClass() {
        return OptionalVarDeclListClass;
    }

    public void setOptionalVarDeclListClass(OptionalVarDeclListClass OptionalVarDeclListClass) {
        this.OptionalVarDeclListClass=OptionalVarDeclListClass;
    }

    public NewVarDecl getNewVarDecl() {
        return NewVarDecl;
    }

    public void setNewVarDecl(NewVarDecl NewVarDecl) {
        this.NewVarDecl=NewVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalVarDeclListClass!=null) OptionalVarDeclListClass.accept(visitor);
        if(NewVarDecl!=null) NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDeclListClass!=null) OptionalVarDeclListClass.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDeclListClass!=null) OptionalVarDeclListClass.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalVarListClass(\n");

        if(OptionalVarDeclListClass!=null)
            buffer.append(OptionalVarDeclListClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewVarDecl!=null)
            buffer.append(NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalVarListClass]");
        return buffer.toString();
    }
}
