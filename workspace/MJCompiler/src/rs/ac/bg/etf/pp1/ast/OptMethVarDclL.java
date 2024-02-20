// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class OptMethVarDclL extends OptionalMethVarDeclL {

    private OptionalMethVarDeclL OptionalMethVarDeclL;
    private VarDeclMeth VarDeclMeth;

    public OptMethVarDclL (OptionalMethVarDeclL OptionalMethVarDeclL, VarDeclMeth VarDeclMeth) {
        this.OptionalMethVarDeclL=OptionalMethVarDeclL;
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.setParent(this);
        this.VarDeclMeth=VarDeclMeth;
        if(VarDeclMeth!=null) VarDeclMeth.setParent(this);
    }

    public OptionalMethVarDeclL getOptionalMethVarDeclL() {
        return OptionalMethVarDeclL;
    }

    public void setOptionalMethVarDeclL(OptionalMethVarDeclL OptionalMethVarDeclL) {
        this.OptionalMethVarDeclL=OptionalMethVarDeclL;
    }

    public VarDeclMeth getVarDeclMeth() {
        return VarDeclMeth;
    }

    public void setVarDeclMeth(VarDeclMeth VarDeclMeth) {
        this.VarDeclMeth=VarDeclMeth;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.accept(visitor);
        if(VarDeclMeth!=null) VarDeclMeth.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.traverseTopDown(visitor);
        if(VarDeclMeth!=null) VarDeclMeth.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptionalMethVarDeclL!=null) OptionalMethVarDeclL.traverseBottomUp(visitor);
        if(VarDeclMeth!=null) VarDeclMeth.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptMethVarDclL(\n");

        if(OptionalMethVarDeclL!=null)
            buffer.append(OptionalMethVarDeclL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclMeth!=null)
            buffer.append(VarDeclMeth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptMethVarDclL]");
        return buffer.toString();
    }
}
