// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class Expr implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private AdditionalMinus AdditionalMinus;
    private Term Term;
    private ExprMinusFlag ExprMinusFlag;
    private OptionalAddopTerm OptionalAddopTerm;

    public Expr (AdditionalMinus AdditionalMinus, Term Term, ExprMinusFlag ExprMinusFlag, OptionalAddopTerm OptionalAddopTerm) {
        this.AdditionalMinus=AdditionalMinus;
        if(AdditionalMinus!=null) AdditionalMinus.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.ExprMinusFlag=ExprMinusFlag;
        if(ExprMinusFlag!=null) ExprMinusFlag.setParent(this);
        this.OptionalAddopTerm=OptionalAddopTerm;
        if(OptionalAddopTerm!=null) OptionalAddopTerm.setParent(this);
    }

    public AdditionalMinus getAdditionalMinus() {
        return AdditionalMinus;
    }

    public void setAdditionalMinus(AdditionalMinus AdditionalMinus) {
        this.AdditionalMinus=AdditionalMinus;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public ExprMinusFlag getExprMinusFlag() {
        return ExprMinusFlag;
    }

    public void setExprMinusFlag(ExprMinusFlag ExprMinusFlag) {
        this.ExprMinusFlag=ExprMinusFlag;
    }

    public OptionalAddopTerm getOptionalAddopTerm() {
        return OptionalAddopTerm;
    }

    public void setOptionalAddopTerm(OptionalAddopTerm OptionalAddopTerm) {
        this.OptionalAddopTerm=OptionalAddopTerm;
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
        if(AdditionalMinus!=null) AdditionalMinus.accept(visitor);
        if(Term!=null) Term.accept(visitor);
        if(ExprMinusFlag!=null) ExprMinusFlag.accept(visitor);
        if(OptionalAddopTerm!=null) OptionalAddopTerm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AdditionalMinus!=null) AdditionalMinus.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(ExprMinusFlag!=null) ExprMinusFlag.traverseTopDown(visitor);
        if(OptionalAddopTerm!=null) OptionalAddopTerm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AdditionalMinus!=null) AdditionalMinus.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(ExprMinusFlag!=null) ExprMinusFlag.traverseBottomUp(visitor);
        if(OptionalAddopTerm!=null) OptionalAddopTerm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr(\n");

        if(AdditionalMinus!=null)
            buffer.append(AdditionalMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExprMinusFlag!=null)
            buffer.append(ExprMinusFlag.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptionalAddopTerm!=null)
            buffer.append(OptionalAddopTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr]");
        return buffer.toString();
    }
}
