// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class AdditionalDes extends AdditionalDesign {

    private Designator Designator;
    private DesHardAfter DesHardAfter;

    public AdditionalDes (Designator Designator, DesHardAfter DesHardAfter) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesHardAfter=DesHardAfter;
        if(DesHardAfter!=null) DesHardAfter.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesHardAfter getDesHardAfter() {
        return DesHardAfter;
    }

    public void setDesHardAfter(DesHardAfter DesHardAfter) {
        this.DesHardAfter=DesHardAfter;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesHardAfter!=null) DesHardAfter.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesHardAfter!=null) DesHardAfter.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesHardAfter!=null) DesHardAfter.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AdditionalDes(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesHardAfter!=null)
            buffer.append(DesHardAfter.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AdditionalDes]");
        return buffer.toString();
    }
}
