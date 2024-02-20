// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class DesUnmatch extends DesignatorUnmatched {

    private DesUnmatchFlag DesUnmatchFlag;
    private DesignatorMatched DesignatorMatched;

    public DesUnmatch (DesUnmatchFlag DesUnmatchFlag, DesignatorMatched DesignatorMatched) {
        this.DesUnmatchFlag=DesUnmatchFlag;
        if(DesUnmatchFlag!=null) DesUnmatchFlag.setParent(this);
        this.DesignatorMatched=DesignatorMatched;
        if(DesignatorMatched!=null) DesignatorMatched.setParent(this);
    }

    public DesUnmatchFlag getDesUnmatchFlag() {
        return DesUnmatchFlag;
    }

    public void setDesUnmatchFlag(DesUnmatchFlag DesUnmatchFlag) {
        this.DesUnmatchFlag=DesUnmatchFlag;
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
        if(DesUnmatchFlag!=null) DesUnmatchFlag.accept(visitor);
        if(DesignatorMatched!=null) DesignatorMatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesUnmatchFlag!=null) DesUnmatchFlag.traverseTopDown(visitor);
        if(DesignatorMatched!=null) DesignatorMatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesUnmatchFlag!=null) DesUnmatchFlag.traverseBottomUp(visitor);
        if(DesignatorMatched!=null) DesignatorMatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesUnmatch(\n");

        if(DesUnmatchFlag!=null)
            buffer.append(DesUnmatchFlag.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorMatched!=null)
            buffer.append(DesignatorMatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesUnmatch]");
        return buffer.toString();
    }
}
