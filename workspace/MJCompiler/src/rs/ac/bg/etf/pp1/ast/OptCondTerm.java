// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptCondTerm extends OptionalCondTerm {

    private OptionalCondTerm OptionalCondTerm;
    private CondOrBegin CondOrBegin;
    private CondTerm CondTerm;

    public OptCondTerm (OptionalCondTerm OptionalCondTerm, CondOrBegin CondOrBegin, CondTerm CondTerm) {
        this.OptionalCondTerm=OptionalCondTerm;
        if(OptionalCondTerm!=null) OptionalCondTerm.setParent(this);
        this.CondOrBegin=CondOrBegin;
        if(CondOrBegin!=null) CondOrBegin.setParent(this);
        this.CondTerm=CondTerm;
        if(CondTerm!=null) CondTerm.setParent(this);
    }

    public OptionalCondTerm getOptionalCondTerm() {
        return OptionalCondTerm;
    }

    public void setOptionalCondTerm(OptionalCondTerm OptionalCondTerm) {
        this.OptionalCondTerm=OptionalCondTerm;
    }

    public CondOrBegin getCondOrBegin() {
        return CondOrBegin;
    }

    public void setCondOrBegin(CondOrBegin CondOrBegin) {
        this.CondOrBegin=CondOrBegin;
    }

    public CondTerm getCondTerm() {
        return CondTerm;
    }

    public void setCondTerm(CondTerm CondTerm) {
        this.CondTerm=CondTerm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalCondTerm!=null) OptionalCondTerm.accept(visitor);
        if(CondOrBegin!=null) CondOrBegin.accept(visitor);
        if(CondTerm!=null) CondTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalCondTerm!=null) OptionalCondTerm.traverseTopDown(visitor);
        if(CondOrBegin!=null) CondOrBegin.traverseTopDown(visitor);
        if(CondTerm!=null) CondTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalCondTerm!=null) OptionalCondTerm.traverseBottomUp(visitor);
        if(CondOrBegin!=null) CondOrBegin.traverseBottomUp(visitor);
        if(CondTerm!=null) CondTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptCondTerm(\n");

        if(OptionalCondTerm!=null)
            buffer.append(OptionalCondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondOrBegin!=null)
            buffer.append(CondOrBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondTerm!=null)
            buffer.append(CondTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptCondTerm]");
        return buffer.toString();
    }
}
