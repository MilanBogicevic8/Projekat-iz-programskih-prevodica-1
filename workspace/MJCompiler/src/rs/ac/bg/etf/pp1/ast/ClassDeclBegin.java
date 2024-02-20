// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclBegin implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private String name;
    private AdditionalExtends AdditionalExtends;

    public ClassDeclBegin (String name, AdditionalExtends AdditionalExtends) {
        this.name=name;
        this.AdditionalExtends=AdditionalExtends;
        if(AdditionalExtends!=null) AdditionalExtends.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public AdditionalExtends getAdditionalExtends() {
        return AdditionalExtends;
    }

    public void setAdditionalExtends(AdditionalExtends AdditionalExtends) {
        this.AdditionalExtends=AdditionalExtends;
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
        if(AdditionalExtends!=null) AdditionalExtends.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalExtends!=null) AdditionalExtends.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalExtends!=null) AdditionalExtends.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclBegin(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(AdditionalExtends!=null)
            buffer.append(AdditionalExtends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclBegin]");
        return buffer.toString();
    }
}
