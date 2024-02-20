// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclClass implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private TypeOrVoid TypeOrVoid;
    private AdditionalFormPars AdditionalFormPars;
    private OptionalMethVarDeclL OptionalMethVarDeclL;
    private StatementList StatementList;

    public MethodDeclClass (TypeOrVoid TypeOrVoid, AdditionalFormPars AdditionalFormPars, OptionalMethVarDeclL OptionalMethVarDeclL, StatementList StatementList) {
        this.TypeOrVoid=TypeOrVoid;
        if(TypeOrVoid!=null) TypeOrVoid.setParent(this);
        this.AdditionalFormPars=AdditionalFormPars;
        if(AdditionalFormPars!=null) AdditionalFormPars.setParent(this);
        this.OptionalMethVarDeclL=OptionalMethVarDeclL;
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public TypeOrVoid getTypeOrVoid() {
        return TypeOrVoid;
    }

    public void setTypeOrVoid(TypeOrVoid TypeOrVoid) {
        this.TypeOrVoid=TypeOrVoid;
    }

    public AdditionalFormPars getAdditionalFormPars() {
        return AdditionalFormPars;
    }

    public void setAdditionalFormPars(AdditionalFormPars AdditionalFormPars) {
        this.AdditionalFormPars=AdditionalFormPars;
    }

    public OptionalMethVarDeclL getOptionalMethVarDeclL() {
        return OptionalMethVarDeclL;
    }

    public void setOptionalMethVarDeclL(OptionalMethVarDeclL OptionalMethVarDeclL) {
        this.OptionalMethVarDeclL=OptionalMethVarDeclL;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
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
        if(TypeOrVoid!=null) TypeOrVoid.accept(visitor);
        if(AdditionalFormPars!=null) AdditionalFormPars.accept(visitor);
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeOrVoid!=null) TypeOrVoid.traverseTopDown(visitor);
        if(AdditionalFormPars!=null) AdditionalFormPars.traverseTopDown(visitor);
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeOrVoid!=null) TypeOrVoid.traverseBottomUp(visitor);
        if(AdditionalFormPars!=null) AdditionalFormPars.traverseBottomUp(visitor);
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclClass(\n");

        if(TypeOrVoid!=null)
            buffer.append(TypeOrVoid.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AdditionalFormPars!=null)
            buffer.append(AdditionalFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalMethVarDeclL!=null)
            buffer.append(OptionalMethVarDeclL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclClass]");
        return buffer.toString();
    }
}
