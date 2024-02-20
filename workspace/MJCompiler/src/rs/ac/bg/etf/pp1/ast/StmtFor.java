// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class StmtFor extends Matched {

    private ForBegin ForBegin;
    private Matched Matched;
    private ForEnd ForEnd;

    public StmtFor (ForBegin ForBegin, Matched Matched, ForEnd ForEnd) {
        this.ForBegin=ForBegin;
        if(ForBegin!=null) ForBegin.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.ForEnd=ForEnd;
        if(ForEnd!=null) ForEnd.setParent(this);
    }

    public ForBegin getForBegin() {
        return ForBegin;
    }

    public void setForBegin(ForBegin ForBegin) {
        this.ForBegin=ForBegin;
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
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
        if(Matched!=null) Matched.accept(visitor);
        if(ForEnd!=null) ForEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForBegin!=null) ForBegin.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ForEnd!=null) ForEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForBegin!=null) ForBegin.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ForEnd!=null) ForEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StmtFor(\n");

        if(ForBegin!=null)
            buffer.append(ForBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForEnd!=null)
            buffer.append(ForEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StmtFor]");
        return buffer.toString();
    }
}
