// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OrActPars extends OrDesStmt {

    private PomDesAddStek PomDesAddStek;
    private AdditionalActPars AdditionalActPars;

    public OrActPars (PomDesAddStek PomDesAddStek, AdditionalActPars AdditionalActPars) {
        this.PomDesAddStek=PomDesAddStek;
        if(PomDesAddStek!=null) PomDesAddStek.setParent(this);
        this.AdditionalActPars=AdditionalActPars;
        if(AdditionalActPars!=null) AdditionalActPars.setParent(this);
    }

    public PomDesAddStek getPomDesAddStek() {
        return PomDesAddStek;
    }

    public void setPomDesAddStek(PomDesAddStek PomDesAddStek) {
        this.PomDesAddStek=PomDesAddStek;
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
        if(PomDesAddStek!=null) PomDesAddStek.accept(visitor);
        if(AdditionalActPars!=null) AdditionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(PomDesAddStek!=null) PomDesAddStek.traverseTopDown(visitor);
        if(AdditionalActPars!=null) AdditionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(PomDesAddStek!=null) PomDesAddStek.traverseBottomUp(visitor);
        if(AdditionalActPars!=null) AdditionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OrActPars(\n");

        if(PomDesAddStek!=null)
            buffer.append(PomDesAddStek.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalActPars!=null)
            buffer.append(AdditionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OrActPars]");
        return buffer.toString();
    }
}
