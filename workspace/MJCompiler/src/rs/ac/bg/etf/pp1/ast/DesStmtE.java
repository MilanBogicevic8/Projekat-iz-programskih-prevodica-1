// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class DesStmtE extends DesignatorStatement {

    private Designator Designator;
    private OrDesStmt OrDesStmt;

    public DesStmtE (Designator Designator, OrDesStmt OrDesStmt) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.OrDesStmt=OrDesStmt;
        if(OrDesStmt!=null) OrDesStmt.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public OrDesStmt getOrDesStmt() {
        return OrDesStmt;
    }

    public void setOrDesStmt(OrDesStmt OrDesStmt) {
        this.OrDesStmt=OrDesStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(OrDesStmt!=null) OrDesStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(OrDesStmt!=null) OrDesStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(OrDesStmt!=null) OrDesStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesStmtE(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrDesStmt!=null)
            buffer.append(OrDesStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesStmtE]");
        return buffer.toString();
    }
}
