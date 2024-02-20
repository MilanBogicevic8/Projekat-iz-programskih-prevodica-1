// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class DesignatorU extends Designator {

    private DesignatorUnmatched DesignatorUnmatched;

    public DesignatorU (DesignatorUnmatched DesignatorUnmatched) {
        this.DesignatorUnmatched=DesignatorUnmatched;
        if(DesignatorUnmatched!=null) DesignatorUnmatched.setParent(this);
    }

    public DesignatorUnmatched getDesignatorUnmatched() {
        return DesignatorUnmatched;
    }

    public void setDesignatorUnmatched(DesignatorUnmatched DesignatorUnmatched) {
        this.DesignatorUnmatched=DesignatorUnmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorUnmatched!=null) DesignatorUnmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorUnmatched!=null) DesignatorUnmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorUnmatched!=null) DesignatorUnmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorU(\n");

        if(DesignatorUnmatched!=null)
            buffer.append(DesignatorUnmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorU]");
        return buffer.toString();
    }
}
