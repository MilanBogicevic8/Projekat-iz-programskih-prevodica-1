// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class VarDeclMeth implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private NewVarDecl NewVarDecl;
    private OptionalVarDeclListMeth OptionalVarDeclListMeth;

    public VarDeclMeth (Type Type, NewVarDecl NewVarDecl, OptionalVarDeclListMeth OptionalVarDeclListMeth) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
        this.OptionalVarDeclListMeth=OptionalVarDeclListMeth;
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.setParent(this);
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

    public OptionalVarDeclListMeth getOptionalVarDeclListMeth() {
        return OptionalVarDeclListMeth;
    }

    public void setOptionalVarDeclListMeth(OptionalVarDeclListMeth OptionalVarDeclListMeth) {
        this.OptionalVarDeclListMeth=OptionalVarDeclListMeth;
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
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclMeth(\n");

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

        if(OptionalVarDeclListMeth!=null)
            buffer.append(OptionalVarDeclListMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclMeth]");
        return buffer.toString();
    }
}
