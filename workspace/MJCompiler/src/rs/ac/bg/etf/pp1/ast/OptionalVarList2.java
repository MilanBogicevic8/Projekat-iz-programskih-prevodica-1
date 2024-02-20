// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalVarList2 extends OptionalVarDeclList2 {

    private OptionalVarDeclList2 OptionalVarDeclList2;
    private Type Type;
    private NewVarDecl NewVarDecl;

    public OptionalVarList2 (OptionalVarDeclList2 OptionalVarDeclList2, Type Type, NewVarDecl NewVarDecl) {
        this.OptionalVarDeclList2=OptionalVarDeclList2;
        if(OptionalVarDeclList2!=null) OptionalVarDeclList2.setParent(this);
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
    }

    public OptionalVarDeclList2 getOptionalVarDeclList2() {
        return OptionalVarDeclList2;
    }

    public void setOptionalVarDeclList2(OptionalVarDeclList2 OptionalVarDeclList2) {
        this.OptionalVarDeclList2=OptionalVarDeclList2;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(OptionalVarDeclList2!=null) OptionalVarDeclList2.accept(visitor);
        if(Type!=null) Type.accept(visitor);
        if(NewVarDecl!=null) NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDeclList2!=null) OptionalVarDeclList2.traverseTopDown(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDeclList2!=null) OptionalVarDeclList2.traverseBottomUp(visitor);
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalVarList2(\n");

        if(OptionalVarDeclList2!=null)
            buffer.append(OptionalVarDeclList2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewVarDecl!=null)
            buffer.append(NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalVarList2]");
        return buffer.toString();
    }
}
