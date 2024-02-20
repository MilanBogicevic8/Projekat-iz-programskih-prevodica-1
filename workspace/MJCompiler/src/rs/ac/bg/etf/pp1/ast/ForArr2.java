// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ForArr2 extends Matched {

    private ForModBegin ForModBegin;
    private Unmatched Unmatched;

    public ForArr2 (ForModBegin ForModBegin, Unmatched Unmatched) {
        this.ForModBegin=ForModBegin;
        if(ForModBegin!=null) ForModBegin.setParent(this);
        this.Unmatched=Unmatched;
        if(Unmatched!=null) Unmatched.setParent(this);
    }

    public ForModBegin getForModBegin() {
        return ForModBegin;
    }

    public void setForModBegin(ForModBegin ForModBegin) {
        this.ForModBegin=ForModBegin;
    }

    public Unmatched getUnmatched() {
        return Unmatched;
    }

    public void setUnmatched(Unmatched Unmatched) {
        this.Unmatched=Unmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForModBegin!=null) ForModBegin.accept(visitor);
        if(Unmatched!=null) Unmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForModBegin!=null) ForModBegin.traverseTopDown(visitor);
        if(Unmatched!=null) Unmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForModBegin!=null) ForModBegin.traverseBottomUp(visitor);
        if(Unmatched!=null) Unmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForArr2(\n");

        if(ForModBegin!=null)
            buffer.append(ForModBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Unmatched!=null)
            buffer.append(Unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForArr2]");
        return buffer.toString();
    }
}
