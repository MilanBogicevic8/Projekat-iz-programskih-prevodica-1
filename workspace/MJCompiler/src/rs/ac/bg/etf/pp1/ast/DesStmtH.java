// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class DesStmtH extends DesignatorStatement {

    private DesHardStart DesHardStart;
    private OptionalDesignComma OptionalDesignComma;
    private Designator Designator;
    private Designator Designator1;

    public DesStmtH (DesHardStart DesHardStart, OptionalDesignComma OptionalDesignComma, Designator Designator, Designator Designator1) {
        this.DesHardStart=DesHardStart;
        if(DesHardStart!=null) DesHardStart.setParent(this);
        this.OptionalDesignComma=OptionalDesignComma;
        if(OptionalDesignComma!=null) OptionalDesignComma.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Designator1=Designator1;
        if(Designator1!=null) Designator1.setParent(this);
    }

    public DesHardStart getDesHardStart() {
        return DesHardStart;
    }

    public void setDesHardStart(DesHardStart DesHardStart) {
        this.DesHardStart=DesHardStart;
    }

    public OptionalDesignComma getOptionalDesignComma() {
        return OptionalDesignComma;
    }

    public void setOptionalDesignComma(OptionalDesignComma OptionalDesignComma) {
        this.OptionalDesignComma=OptionalDesignComma;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Designator getDesignator1() {
        return Designator1;
    }

    public void setDesignator1(Designator Designator1) {
        this.Designator1=Designator1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesHardStart!=null) DesHardStart.accept(visitor);
        if(OptionalDesignComma!=null) OptionalDesignComma.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
        if(Designator1!=null) Designator1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesHardStart!=null) DesHardStart.traverseTopDown(visitor);
        if(OptionalDesignComma!=null) OptionalDesignComma.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Designator1!=null) Designator1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesHardStart!=null) DesHardStart.traverseBottomUp(visitor);
        if(OptionalDesignComma!=null) OptionalDesignComma.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Designator1!=null) Designator1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesStmtH(\n");

        if(DesHardStart!=null)
            buffer.append(DesHardStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalDesignComma!=null)
            buffer.append(OptionalDesignComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator1!=null)
            buffer.append(Designator1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesStmtH]");
        return buffer.toString();
    }
}
