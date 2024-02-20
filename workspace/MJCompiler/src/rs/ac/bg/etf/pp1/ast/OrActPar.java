// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OrActPar extends OrExprActPars {

    private AdditionalActPars AdditionalActPars;

    public OrActPar (AdditionalActPars AdditionalActPars) {
        this.AdditionalActPars=AdditionalActPars;
        if(AdditionalActPars!=null) AdditionalActPars.setParent(this);
    }

    public AdditionalActPars getAdditionalActPars() {
        return AdditionalActPars;
    }

    public void setAdditionalActPars(AdditionalActPars AdditionalActPars) {
        this.AdditionalActPars=AdditionalActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AdditionalActPars!=null) AdditionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalActPars!=null) AdditionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalActPars!=null) AdditionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrActPar(\n");

        if(AdditionalActPars!=null)
            buffer.append(AdditionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrActPar]");
        return buffer.toString();
    }
}
