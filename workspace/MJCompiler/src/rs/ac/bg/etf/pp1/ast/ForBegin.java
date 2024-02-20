// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ForBegin implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ForStart ForStart;
    private AdditionalDesignatoStmtList AdditionalDesignatoStmtList;
    private LoopStart LoopStart;
    private AdditionalCondFact AdditionalCondFact;
    private ForFlag ForFlag;
    private AdditionalDesignatoStmtList AdditionalDesignatoStmtList1;
    private ForFlagEnd ForFlagEnd;

    public ForBegin (ForStart ForStart, AdditionalDesignatoStmtList AdditionalDesignatoStmtList, LoopStart LoopStart, AdditionalCondFact AdditionalCondFact, ForFlag ForFlag, AdditionalDesignatoStmtList AdditionalDesignatoStmtList1, ForFlagEnd ForFlagEnd) {
        this.ForStart=ForStart;
        if(ForStart!=null) ForStart.setParent(this);
        this.AdditionalDesignatoStmtList=AdditionalDesignatoStmtList;
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.setParent(this);
        this.LoopStart=LoopStart;
        if(LoopStart!=null) LoopStart.setParent(this);
        this.AdditionalCondFact=AdditionalCondFact;
        if(AdditionalCondFact!=null) AdditionalCondFact.setParent(this);
        this.ForFlag=ForFlag;
        if(ForFlag!=null) ForFlag.setParent(this);
        this.AdditionalDesignatoStmtList1=AdditionalDesignatoStmtList1;
        if(AdditionalDesignatoStmtList1!=null) AdditionalDesignatoStmtList1.setParent(this);
        this.ForFlagEnd=ForFlagEnd;
        if(ForFlagEnd!=null) ForFlagEnd.setParent(this);
    }

    public ForStart getForStart() {
        return ForStart;
    }

    public void setForStart(ForStart ForStart) {
        this.ForStart=ForStart;
    }

    public AdditionalDesignatoStmtList getAdditionalDesignatoStmtList() {
        return AdditionalDesignatoStmtList;
    }

    public void setAdditionalDesignatoStmtList(AdditionalDesignatoStmtList AdditionalDesignatoStmtList) {
        this.AdditionalDesignatoStmtList=AdditionalDesignatoStmtList;
    }

    public LoopStart getLoopStart() {
        return LoopStart;
    }

    public void setLoopStart(LoopStart LoopStart) {
        this.LoopStart=LoopStart;
    }

    public AdditionalCondFact getAdditionalCondFact() {
        return AdditionalCondFact;
    }

    public void setAdditionalCondFact(AdditionalCondFact AdditionalCondFact) {
        this.AdditionalCondFact=AdditionalCondFact;
    }

    public ForFlag getForFlag() {
        return ForFlag;
    }

    public void setForFlag(ForFlag ForFlag) {
        this.ForFlag=ForFlag;
    }

    public AdditionalDesignatoStmtList getAdditionalDesignatoStmtList1() {
        return AdditionalDesignatoStmtList1;
    }

    public void setAdditionalDesignatoStmtList1(AdditionalDesignatoStmtList AdditionalDesignatoStmtList1) {
        this.AdditionalDesignatoStmtList1=AdditionalDesignatoStmtList1;
    }

    public ForFlagEnd getForFlagEnd() {
        return ForFlagEnd;
    }

    public void setForFlagEnd(ForFlagEnd ForFlagEnd) {
        this.ForFlagEnd=ForFlagEnd;
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
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.accept(visitor);
        if(LoopStart!=null) LoopStart.accept(visitor);
        if(AdditionalCondFact!=null) AdditionalCondFact.accept(visitor);
        if(ForFlag!=null) ForFlag.accept(visitor);
        if(AdditionalDesignatoStmtList1!=null) AdditionalDesignatoStmtList1.accept(visitor);
        if(ForFlagEnd!=null) ForFlagEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForStart!=null) ForStart.traverseTopDown(visitor);
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.traverseTopDown(visitor);
        if(LoopStart!=null) LoopStart.traverseTopDown(visitor);
        if(AdditionalCondFact!=null) AdditionalCondFact.traverseTopDown(visitor);
        if(ForFlag!=null) ForFlag.traverseTopDown(visitor);
        if(AdditionalDesignatoStmtList1!=null) AdditionalDesignatoStmtList1.traverseTopDown(visitor);
        if(ForFlagEnd!=null) ForFlagEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForStart!=null) ForStart.traverseBottomUp(visitor);
        if(AdditionalDesignatoStmtList!=null) AdditionalDesignatoStmtList.traverseBottomUp(visitor);
        if(LoopStart!=null) LoopStart.traverseBottomUp(visitor);
        if(AdditionalCondFact!=null) AdditionalCondFact.traverseBottomUp(visitor);
        if(ForFlag!=null) ForFlag.traverseBottomUp(visitor);
        if(AdditionalDesignatoStmtList1!=null) AdditionalDesignatoStmtList1.traverseBottomUp(visitor);
        if(ForFlagEnd!=null) ForFlagEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForBegin(\n");

        if(ForStart!=null)
            buffer.append(ForStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalDesignatoStmtList!=null)
            buffer.append(AdditionalDesignatoStmtList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(LoopStart!=null)
            buffer.append(LoopStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalCondFact!=null)
            buffer.append(AdditionalCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForFlag!=null)
            buffer.append(ForFlag.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalDesignatoStmtList1!=null)
            buffer.append(AdditionalDesignatoStmtList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForFlagEnd!=null)
            buffer.append(ForFlagEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForBegin]");
        return buffer.toString();
    }
}
