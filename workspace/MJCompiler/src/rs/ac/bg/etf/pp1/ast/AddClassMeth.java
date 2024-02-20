// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class AddClassMeth extends AdditionalMethodClassDecl {

    private StaticEndDef StaticEndDef;
    private MethodDeclClassList MethodDeclClassList;

    public AddClassMeth (StaticEndDef StaticEndDef, MethodDeclClassList MethodDeclClassList) {
        this.StaticEndDef=StaticEndDef;
        if(StaticEndDef!=null) StaticEndDef.setParent(this);
        this.MethodDeclClassList=MethodDeclClassList;
        if(MethodDeclClassList!=null) MethodDeclClassList.setParent(this);
    }

    public StaticEndDef getStaticEndDef() {
        return StaticEndDef;
    }

    public void setStaticEndDef(StaticEndDef StaticEndDef) {
        this.StaticEndDef=StaticEndDef;
    }

    public MethodDeclClassList getMethodDeclClassList() {
        return MethodDeclClassList;
    }

    public void setMethodDeclClassList(MethodDeclClassList MethodDeclClassList) {
        this.MethodDeclClassList=MethodDeclClassList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticEndDef!=null) StaticEndDef.accept(visitor);
        if(MethodDeclClassList!=null) MethodDeclClassList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticEndDef!=null) StaticEndDef.traverseTopDown(visitor);
        if(MethodDeclClassList!=null) MethodDeclClassList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticEndDef!=null) StaticEndDef.traverseBottomUp(visitor);
        if(MethodDeclClassList!=null) MethodDeclClassList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AddClassMeth(\n");

        if(StaticEndDef!=null)
            buffer.append(StaticEndDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclClassList!=null)
            buffer.append(MethodDeclClassList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AddClassMeth]");
        return buffer.toString();
    }
}
