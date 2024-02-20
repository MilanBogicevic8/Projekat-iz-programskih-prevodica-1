// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class StmtFor2 extends Matched {

    private ForBegin ForBegin;
    private Unmatched Unmatched;
    private ForEnd ForEnd;

    public StmtFor2 (ForBegin ForBegin, Unmatched Unmatched, ForEnd ForEnd) {
        this.ForBegin=ForBegin;
        if(ForBegin!=null) ForBegin.setParent(this);
        this.Unmatched=Unmatched;
        if(Unmatched!=null) Unmatched.setParent(this);
        this.ForEnd=ForEnd;
        if(ForEnd!=null) ForEnd.setParent(this);
    }

    public ForBegin getForBegin() {
        return ForBegin;
    }

    public void setForBegin(ForBegin ForBegin) {
        this.ForBegin=ForBegin;
    }

    public Unmatched getUnmatched() {
        return Unmatched;
    }

    public void setUnmatched(Unmatched Unmatched) {
        this.Unmatched=Unmatched;
    }

    public ForEnd getForEnd() {
        return ForEnd;
    }

    public void setForEnd(ForEnd ForEnd) {
        this.ForEnd=ForEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForBegin!=null) ForBegin.accept(visitor);
        if(Unmatched!=null) Unmatched.accept(visitor);
        if(ForEnd!=null) ForEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForBegin!=null) ForBegin.traverseTopDown(visitor);
        if(Unmatched!=null) Unmatched.traverseTopDown(visitor);
        if(ForEnd!=null) ForEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForBegin!=null) ForBegin.traverseBottomUp(visitor);
        if(Unmatched!=null) Unmatched.traverseBottomUp(visitor);
        if(ForEnd!=null) ForEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtFor2(\n");

        if(ForBegin!=null)
            buffer.append(ForBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Unmatched!=null)
            buffer.append(Unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEnd!=null)
            buffer.append(ForEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtFor2]");
        return buffer.toString();
    }
}
