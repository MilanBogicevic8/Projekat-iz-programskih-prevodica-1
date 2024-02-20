// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class IfElseMatched extends Matched {

    private IfBegin IfBegin;
    private Condition Condition;
    private IfBeginEnd IfBeginEnd;
    private Matched Matched;
    private ElseBegin ElseBegin;
    private Matched Matched1;

    public IfElseMatched (IfBegin IfBegin, Condition Condition, IfBeginEnd IfBeginEnd, Matched Matched, ElseBegin ElseBegin, Matched Matched1) {
        this.IfBegin=IfBegin;
        if(IfBegin!=null) IfBegin.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.IfBeginEnd=IfBeginEnd;
        if(IfBeginEnd!=null) IfBeginEnd.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.ElseBegin=ElseBegin;
        if(ElseBegin!=null) ElseBegin.setParent(this);
        this.Matched1=Matched1;
        if(Matched1!=null) Matched1.setParent(this);
    }

    public IfBegin getIfBegin() {
        return IfBegin;
    }

    public void setIfBegin(IfBegin IfBegin) {
        this.IfBegin=IfBegin;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public IfBeginEnd getIfBeginEnd() {
        return IfBeginEnd;
    }

    public void setIfBeginEnd(IfBeginEnd IfBeginEnd) {
        this.IfBeginEnd=IfBeginEnd;
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public ElseBegin getElseBegin() {
        return ElseBegin;
    }

    public void setElseBegin(ElseBegin ElseBegin) {
        this.ElseBegin=ElseBegin;
    }

    public Matched getMatched1() {
        return Matched1;
    }

    public void setMatched1(Matched Matched1) {
        this.Matched1=Matched1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfBegin!=null) IfBegin.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(IfBeginEnd!=null) IfBeginEnd.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(ElseBegin!=null) ElseBegin.accept(visitor);
        if(Matched1!=null) Matched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfBegin!=null) IfBegin.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(IfBeginEnd!=null) IfBeginEnd.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ElseBegin!=null) ElseBegin.traverseTopDown(visitor);
        if(Matched1!=null) Matched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfBegin!=null) IfBegin.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(IfBeginEnd!=null) IfBeginEnd.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ElseBegin!=null) ElseBegin.traverseBottomUp(visitor);
        if(Matched1!=null) Matched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseMatched(\n");

        if(IfBegin!=null)
            buffer.append(IfBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfBeginEnd!=null)
            buffer.append(IfBeginEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseBegin!=null)
            buffer.append(ElseBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched1!=null)
            buffer.append(Matched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseMatched]");
        return buffer.toString();
    }
}
