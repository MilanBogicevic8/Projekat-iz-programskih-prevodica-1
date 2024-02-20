// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class DesMatch extends DesignatorMatched {

    private DesPom DesPom;
    private OptionalDesignatorOpp OptionalDesignatorOpp;

    public DesMatch (DesPom DesPom, OptionalDesignatorOpp OptionalDesignatorOpp) {
        this.DesPom=DesPom;
        if(DesPom!=null) DesPom.setParent(this);
        this.OptionalDesignatorOpp=OptionalDesignatorOpp;
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.setParent(this);
    }

    public DesPom getDesPom() {
        return DesPom;
    }

    public void setDesPom(DesPom DesPom) {
        this.DesPom=DesPom;
    }

    public OptionalDesignatorOpp getOptionalDesignatorOpp() {
        return OptionalDesignatorOpp;
    }

    public void setOptionalDesignatorOpp(OptionalDesignatorOpp OptionalDesignatorOpp) {
        this.OptionalDesignatorOpp=OptionalDesignatorOpp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesPom!=null) DesPom.accept(visitor);
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesPom!=null) DesPom.traverseTopDown(visitor);
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesPom!=null) DesPom.traverseBottomUp(visitor);
        if(OptionalDesignatorOpp!=null) OptionalDesignatorOpp.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesMatch(\n");

        if(DesPom!=null)
            buffer.append(DesPom.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalDesignatorOpp!=null)
            buffer.append(OptionalDesignatorOpp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesMatch]");
        return buffer.toString();
    }
}
