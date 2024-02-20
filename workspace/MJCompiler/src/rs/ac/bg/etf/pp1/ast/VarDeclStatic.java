// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class VarDeclStatic implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private NewVarDecl NewVarDecl;
    private OptionalVarDeclListStatic OptionalVarDeclListStatic;

    public VarDeclStatic (Type Type, NewVarDecl NewVarDecl, OptionalVarDeclListStatic OptionalVarDeclListStatic) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
        this.OptionalVarDeclListStatic=OptionalVarDeclListStatic;
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.setParent(this);
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

    public OptionalVarDeclListStatic getOptionalVarDeclListStatic() {
        return OptionalVarDeclListStatic;
    }

    public void setOptionalVarDeclListStatic(OptionalVarDeclListStatic OptionalVarDeclListStatic) {
        this.OptionalVarDeclListStatic=OptionalVarDeclListStatic;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(NewVarDecl!=null) NewVarDecl.accept(visitor);
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        if(OptionalVarDeclListStatic!=null) OptionalVarDeclListStatic.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclStatic(\n");

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

        if(OptionalVarDeclListStatic!=null)
            buffer.append(OptionalVarDeclListStatic.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclStatic]");
        return buffer.toString();
    }
}
