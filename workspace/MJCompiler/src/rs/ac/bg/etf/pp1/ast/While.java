// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class While extends Matched {

    private WhileBegin WhileBegin;
    private Condition Condition;
    private Matched Matched;

    public While (WhileBegin WhileBegin, Condition Condition, Matched Matched) {
        this.WhileBegin=WhileBegin;
        if(WhileBegin!=null) WhileBegin.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
    }

    public WhileBegin getWhileBegin() {
        return WhileBegin;
    }

    public void setWhileBegin(WhileBegin WhileBegin) {
        this.WhileBegin=WhileBegin;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
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
        if(WhileBegin!=null) WhileBegin.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileBegin!=null) WhileBegin.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileBegin!=null) WhileBegin.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("While(\n");

        if(WhileBegin!=null)
            buffer.append(WhileBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [While]");
        return buffer.toString();
    }
}
