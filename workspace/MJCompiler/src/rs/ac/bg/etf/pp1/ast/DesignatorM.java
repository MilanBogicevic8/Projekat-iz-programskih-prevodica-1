// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class DesignatorM extends Designator {

    private DesignatorMatched DesignatorMatched;

    public DesignatorM (DesignatorMatched DesignatorMatched) {
        this.DesignatorMatched=DesignatorMatched;
        if(DesignatorMatched!=null) DesignatorMatched.setParent(this);
    }

    public DesignatorMatched getDesignatorMatched() {
        return DesignatorMatched;
    }

    public void setDesignatorMatched(DesignatorMatched DesignatorMatched) {
        this.DesignatorMatched=DesignatorMatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorMatched!=null) DesignatorMatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorMatched!=null) DesignatorMatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorMatched!=null) DesignatorMatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorM(\n");

        if(DesignatorMatched!=null)
            buffer.append(DesignatorMatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorM]");
        return buffer.toString();
    }
}
