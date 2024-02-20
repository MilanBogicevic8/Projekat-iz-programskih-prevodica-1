// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalVarListMeth extends OptionalVarDeclListMeth {

    private OptionalVarDeclListMeth OptionalVarDeclListMeth;
    private NewVarDecl NewVarDecl;

    public OptionalVarListMeth (OptionalVarDeclListMeth OptionalVarDeclListMeth, NewVarDecl NewVarDecl) {
        this.OptionalVarDeclListMeth=OptionalVarDeclListMeth;
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
    }

    public OptionalVarDeclListMeth getOptionalVarDeclListMeth() {
        return OptionalVarDeclListMeth;
    }

    public void setOptionalVarDeclListMeth(OptionalVarDeclListMeth OptionalVarDeclListMeth) {
        this.OptionalVarDeclListMeth=OptionalVarDeclListMeth;
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
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.accept(visitor);
        if(NewVarDecl!=null) NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDeclListMeth!=null) OptionalVarDeclListMeth.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalVarListMeth(\n");

        if(OptionalVarDeclListMeth!=null)
            buffer.append(OptionalVarDeclListMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewVarDecl!=null)
            buffer.append(NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalVarListMeth]");
        return buffer.toString();
    }
}
