// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class FactorNew extends Factor {

    private Type Type;
    private CuvarObjektaKlase CuvarObjektaKlase;
    private OrExprActPars OrExprActPars;

    public FactorNew (Type Type, CuvarObjektaKlase CuvarObjektaKlase, OrExprActPars OrExprActPars) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.CuvarObjektaKlase=CuvarObjektaKlase;
        if(CuvarObjektaKlase!=null) CuvarObjektaKlase.setParent(this);
        this.OrExprActPars=OrExprActPars;
        if(OrExprActPars!=null) OrExprActPars.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public CuvarObjektaKlase getCuvarObjektaKlase() {
        return CuvarObjektaKlase;
    }

    public void setCuvarObjektaKlase(CuvarObjektaKlase CuvarObjektaKlase) {
        this.CuvarObjektaKlase=CuvarObjektaKlase;
    }

    public OrExprActPars getOrExprActPars() {
        return OrExprActPars;
    }

    public void setOrExprActPars(OrExprActPars OrExprActPars) {
        this.OrExprActPars=OrExprActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(CuvarObjektaKlase!=null) CuvarObjektaKlase.accept(visitor);
        if(OrExprActPars!=null) OrExprActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(CuvarObjektaKlase!=null) CuvarObjektaKlase.traverseTopDown(visitor);
        if(OrExprActPars!=null) OrExprActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(CuvarObjektaKlase!=null) CuvarObjektaKlase.traverseBottomUp(visitor);
        if(OrExprActPars!=null) OrExprActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorNew(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CuvarObjektaKlase!=null)
            buffer.append(CuvarObjektaKlase.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OrExprActPars!=null)
            buffer.append(OrExprActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorNew]");
        return buffer.toString();
    }
}
