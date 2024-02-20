// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ForModBegin implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ForStart ForStart;
    private ForVar ForVar;
    private ForArrayMod ForArrayMod;

    public ForModBegin (ForStart ForStart, ForVar ForVar, ForArrayMod ForArrayMod) {
        this.ForStart=ForStart;
        if(ForStart!=null) ForStart.setParent(this);
        this.ForVar=ForVar;
        if(ForVar!=null) ForVar.setParent(this);
        this.ForArrayMod=ForArrayMod;
        if(ForArrayMod!=null) ForArrayMod.setParent(this);
    }

    public ForStart getForStart() {
        return ForStart;
    }

    public void setForStart(ForStart ForStart) {
        this.ForStart=ForStart;
    }

    public ForVar getForVar() {
        return ForVar;
    }

    public void setForVar(ForVar ForVar) {
        this.ForVar=ForVar;
    }

    public ForArrayMod getForArrayMod() {
        return ForArrayMod;
    }

    public void setForArrayMod(ForArrayMod ForArrayMod) {
        this.ForArrayMod=ForArrayMod;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForStart!=null) ForStart.accept(visitor);
        if(ForVar!=null) ForVar.accept(visitor);
        if(ForArrayMod!=null) ForArrayMod.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForStart!=null) ForStart.traverseTopDown(visitor);
        if(ForVar!=null) ForVar.traverseTopDown(visitor);
        if(ForArrayMod!=null) ForArrayMod.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForStart!=null) ForStart.traverseBottomUp(visitor);
        if(ForVar!=null) ForVar.traverseBottomUp(visitor);
        if(ForArrayMod!=null) ForArrayMod.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForModBegin(\n");

        if(ForStart!=null)
            buffer.append(ForStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForVar!=null)
            buffer.append(ForVar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForArrayMod!=null)
            buffer.append(ForArrayMod.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForModBegin]");
        return buffer.toString();
    }
}
