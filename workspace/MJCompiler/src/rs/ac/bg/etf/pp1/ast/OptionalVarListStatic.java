// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalVarListStatic extends OptionalVarDeclListStatic {

    private OptionalVarDeclListStatic OptionalVarDeclListStatic;
    private NewVarDecl NewVarDecl;

    public OptionalVarListStatic (OptionalVarDeclListStatic OptionalVarDeclListStatic, NewVarDecl NewVarDecl) {
        this.OptionalVarDeclListStatic=OptionalVarDeclListStatic;
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
    }

    public OptionalVarDeclListStatic getOptionalVarDeclListStatic() {
        return OptionalVarDeclListStatic;
    }

    public void setOptionalVarDeclListStatic(OptionalVarDeclListStatic OptionalVarDeclListStatic) {
        this.OptionalVarDeclListStatic=OptionalVarDeclListStatic;
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
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.accept(visitor);
        if(NewVarDecl!=null) NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalVarListStatic(\n");

        if(OptionalVarDeclListStatic!=null)
            buffer.append(OptionalVarDeclListStatic.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewVarDecl!=null)
            buffer.append(NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalVarListStatic]");
        return buffer.toString();
    }
}
