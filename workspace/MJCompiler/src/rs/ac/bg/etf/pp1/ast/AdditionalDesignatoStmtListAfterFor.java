// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class AdditionalDesignatoStmtListAfterFor implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private AdditionalDesignatoStmtList AdditionalDesignatoStmtList;

    public AdditionalDesignatoStmtListAfterFor (AdditionalDesignatoStmtList AdditionalDesignatoStmtList) {
        this.AdditionalDesignatoStmtList=AdditionalDesignatoStmtList;
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.setParent(this);
    }

    public AdditionalDesignatoStmtList getAdditionalDesignatoStmtList() {
        return AdditionalDesignatoStmtList;
    }

    public void setAdditionalDesignatoStmtList(AdditionalDesignatoStmtList AdditionalDesignatoStmtList) {
        this.AdditionalDesignatoStmtList=AdditionalDesignatoStmtList;
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
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AdditionalDesignatoStmtListAfterFor(\n");

        if(AdditionalDesignatoStmtList!=null)
            buffer.append(AdditionalDesignatoStmtList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AdditionalDesignatoStmtListAfterFor]");
        return buffer.toString();
    }
}
