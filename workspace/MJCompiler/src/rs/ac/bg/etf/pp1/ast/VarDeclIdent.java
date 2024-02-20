// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class VarDeclIdent extends NewVarDecl {

    private String name;
    private AdditionalSquare AdditionalSquare;

    public VarDeclIdent (String name, AdditionalSquare AdditionalSquare) {
        this.name=name;
        this.AdditionalSquare=AdditionalSquare;
        if(AdditionalSquare!=null) AdditionalSquare.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public AdditionalSquare getAdditionalSquare() {
        return AdditionalSquare;
    }

    public void setAdditionalSquare(AdditionalSquare AdditionalSquare) {
        this.AdditionalSquare=AdditionalSquare;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AdditionalSquare!=null) AdditionalSquare.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalSquare!=null) AdditionalSquare.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalSquare!=null) AdditionalSquare.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclIdent(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(AdditionalSquare!=null)
            buffer.append(AdditionalSquare.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclIdent]");
        return buffer.toString();
    }
}
