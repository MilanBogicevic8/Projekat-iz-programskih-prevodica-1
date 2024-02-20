// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class IfUnmatched extends Unmatched {

    private IfBegin IfBegin;
    private Condition Condition;
    private IfBeginEnd IfBeginEnd;
    private Statement Statement;

    public IfUnmatched (IfBegin IfBegin, Condition Condition, IfBeginEnd IfBeginEnd, Statement Statement) {
        this.IfBegin=IfBegin;
        if(IfBegin!=null) IfBegin.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.IfBeginEnd=IfBeginEnd;
        if(IfBeginEnd!=null) IfBeginEnd.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
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

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfBegin!=null) IfBegin.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(IfBeginEnd!=null) IfBeginEnd.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfBegin!=null) IfBegin.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(IfBeginEnd!=null) IfBeginEnd.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfBegin!=null) IfBegin.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(IfBeginEnd!=null) IfBeginEnd.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfUnmatched(\n");

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

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfUnmatched]");
        return buffer.toString();
    }
}
