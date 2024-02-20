// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptDesCom extends OptionalDesignComma {

    private OptionalDesignComma OptionalDesignComma;
    private AdditionalDesign AdditionalDesign;

    public OptDesCom (OptionalDesignComma OptionalDesignComma, AdditionalDesign AdditionalDesign) {
        this.OptionalDesignComma=OptionalDesignComma;
        if(OptionalDesignComma!=null) OptionalDesignComma.setParent(this);
        this.AdditionalDesign=AdditionalDesign;
        if(AdditionalDesign!=null) AdditionalDesign.setParent(this);
    }

    public OptionalDesignComma getOptionalDesignComma() {
        return OptionalDesignComma;
    }

    public void setOptionalDesignComma(OptionalDesignComma OptionalDesignComma) {
        this.OptionalDesignComma=OptionalDesignComma;
    }

    public AdditionalDesign getAdditionalDesign() {
        return AdditionalDesign;
    }

    public void setAdditionalDesign(AdditionalDesign AdditionalDesign) {
        this.AdditionalDesign=AdditionalDesign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalDesignComma!=null) OptionalDesignComma.accept(visitor);
        if(AdditionalDesign!=null) AdditionalDesign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalDesignComma!=null) OptionalDesignComma.traverseTopDown(visitor);
        if(AdditionalDesign!=null) AdditionalDesign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalDesignComma!=null) OptionalDesignComma.traverseBottomUp(visitor);
        if(AdditionalDesign!=null) AdditionalDesign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptDesCom(\n");

        if(OptionalDesignComma!=null)
            buffer.append(OptionalDesignComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalDesign!=null)
            buffer.append(AdditionalDesign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptDesCom]");
        return buffer.toString();
    }
}
