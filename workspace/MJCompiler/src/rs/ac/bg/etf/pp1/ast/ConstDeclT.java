// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclT extends ConstDeclTail {

    private ConstDeclTail ConstDeclTail;
    private ConstDeclHead ConstDeclHead;

    public ConstDeclT (ConstDeclTail ConstDeclTail, ConstDeclHead ConstDeclHead) {
        this.ConstDeclTail=ConstDeclTail;
        if(ConstDeclTail!=null) ConstDeclTail.setParent(this);
        this.ConstDeclHead=ConstDeclHead;
        if(ConstDeclHead!=null) ConstDeclHead.setParent(this);
    }

    public ConstDeclTail getConstDeclTail() {
        return ConstDeclTail;
    }

    public void setConstDeclTail(ConstDeclTail ConstDeclTail) {
        this.ConstDeclTail=ConstDeclTail;
    }

    public ConstDeclHead getConstDeclHead() {
        return ConstDeclHead;
    }

    public void setConstDeclHead(ConstDeclHead ConstDeclHead) {
        this.ConstDeclHead=ConstDeclHead;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclTail!=null) ConstDeclTail.accept(visitor);
        if(ConstDeclHead!=null) ConstDeclHead.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclTail!=null) ConstDeclTail.traverseTopDown(visitor);
        if(ConstDeclHead!=null) ConstDeclHead.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclTail!=null) ConstDeclTail.traverseBottomUp(visitor);
        if(ConstDeclHead!=null) ConstDeclHead.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclT(\n");

        if(ConstDeclTail!=null)
            buffer.append(ConstDeclTail.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclHead!=null)
            buffer.append(ConstDeclHead.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclT]");
        return buffer.toString();
    }
}
