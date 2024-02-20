// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptionalVarList extends OptionalVarDeclList {

    private OptionalVarDeclList OptionalVarDeclList;
    private NewVarDecl NewVarDecl;

    public OptionalVarList (OptionalVarDeclList OptionalVarDeclList, NewVarDecl NewVarDecl) {
        this.OptionalVarDeclList=OptionalVarDeclList;
        if(OptionalVarDeclList!=null) OptionalVarDeclList.setParent(this);
        this.NewVarDecl=NewVarDecl;
        if(NewVarDecl!=null) NewVarDecl.setParent(this);
    }

    public OptionalVarDeclList getOptionalVarDeclList() {
        return OptionalVarDeclList;
    }

    public void setOptionalVarDeclList(OptionalVarDeclList OptionalVarDeclList) {
        this.OptionalVarDeclList=OptionalVarDeclList;
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
        if(OptionalVarDeclList!=null) OptionalVarDeclList.accept(visitor);
        if(NewVarDecl!=null) NewVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalVarDeclList!=null) OptionalVarDeclList.traverseTopDown(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalVarDeclList!=null) OptionalVarDeclList.traverseBottomUp(visitor);
        if(NewVarDecl!=null) NewVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalVarList(\n");

        if(OptionalVarDeclList!=null)
            buffer.append(OptionalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NewVarDecl!=null)
            buffer.append(NewVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalVarList]");
        return buffer.toString();
    }
}