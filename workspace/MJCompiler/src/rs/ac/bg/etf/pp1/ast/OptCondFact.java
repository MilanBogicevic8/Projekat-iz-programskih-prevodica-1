// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptCondFact extends OptionalCondFact {

    private OptionalCondFact OptionalCondFact;
    private CondFact CondFact;

    public OptCondFact (OptionalCondFact OptionalCondFact, CondFact CondFact) {
        this.OptionalCondFact=OptionalCondFact;
        if(OptionalCondFact!=null) OptionalCondFact.setParent(this);
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
    }

    public OptionalCondFact getOptionalCondFact() {
        return OptionalCondFact;
    }

    public void setOptionalCondFact(OptionalCondFact OptionalCondFact) {
        this.OptionalCondFact=OptionalCondFact;
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalCondFact!=null) OptionalCondFact.accept(visitor);
        if(CondFact!=null) CondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalCondFact!=null) OptionalCondFact.traverseTopDown(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalCondFact!=null) OptionalCondFact.traverseBottomUp(visitor);
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptCondFact(\n");

        if(OptionalCondFact!=null)
            buffer.append(OptionalCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptCondFact]");
        return buffer.toString();
    }
}
