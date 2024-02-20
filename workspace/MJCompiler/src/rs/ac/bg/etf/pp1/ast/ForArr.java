// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ForArr extends Matched {

    private ForModBegin ForModBegin;
    private Matched Matched;

    public ForArr (ForModBegin ForModBegin, Matched Matched) {
        this.ForModBegin=ForModBegin;
        if(ForModBegin!=null) ForModBegin.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
    }

    public ForModBegin getForModBegin() {
        return ForModBegin;
    }

    public void setForModBegin(ForModBegin ForModBegin) {
        this.ForModBegin=ForModBegin;
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForModBegin!=null) ForModBegin.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForModBegin!=null) ForModBegin.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForModBegin!=null) ForModBegin.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForArr(\n");

        if(ForModBegin!=null)
            buffer.append(ForModBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForArr]");
        return buffer.toString();
    }
}
