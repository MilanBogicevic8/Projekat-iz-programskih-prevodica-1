// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class FactorDesign extends Factor {

    private FactorDesignator FactorDesignator;
    private AdditionalActParOp AdditionalActParOp;

    public FactorDesign (FactorDesignator FactorDesignator, AdditionalActParOp AdditionalActParOp) {
        this.FactorDesignator=FactorDesignator;
        if(FactorDesignator!=null) FactorDesignator.setParent(this);
        this.AdditionalActParOp=AdditionalActParOp;
        if(AdditionalActParOp!=null) AdditionalActParOp.setParent(this);
    }

    public FactorDesignator getFactorDesignator() {
        return FactorDesignator;
    }

    public void setFactorDesignator(FactorDesignator FactorDesignator) {
        this.FactorDesignator=FactorDesignator;
    }

    public AdditionalActParOp getAdditionalActParOp() {
        return AdditionalActParOp;
    }

    public void setAdditionalActParOp(AdditionalActParOp AdditionalActParOp) {
        this.AdditionalActParOp=AdditionalActParOp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorDesignator!=null) FactorDesignator.accept(visitor);
        if(AdditionalActParOp!=null) AdditionalActParOp.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorDesignator!=null) FactorDesignator.traverseTopDown(visitor);
        if(AdditionalActParOp!=null) AdditionalActParOp.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorDesignator!=null) FactorDesignator.traverseBottomUp(visitor);
        if(AdditionalActParOp!=null) AdditionalActParOp.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorDesign(\n");

        if(FactorDesignator!=null)
            buffer.append(FactorDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalActParOp!=null)
            buffer.append(AdditionalActParOp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorDesign]");
        return buffer.toString();
    }
}
