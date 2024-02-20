// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class MethDeclClassL extends MethodDeclClassList {

    private MethodDeclClassList MethodDeclClassList;
    private MethodDeclClass MethodDeclClass;

    public MethDeclClassL (MethodDeclClassList MethodDeclClassList, MethodDeclClass MethodDeclClass) {
        this.MethodDeclClassList=MethodDeclClassList;
        if(MethodDeclClassList!=null) MethodDeclClassList.setParent(this);
        this.MethodDeclClass=MethodDeclClass;
        if(MethodDeclClass!=null) MethodDeclClass.setParent(this);
    }

    public MethodDeclClassList getMethodDeclClassList() {
        return MethodDeclClassList;
    }

    public void setMethodDeclClassList(MethodDeclClassList MethodDeclClassList) {
        this.MethodDeclClassList=MethodDeclClassList;
    }

    public MethodDeclClass getMethodDeclClass() {
        return MethodDeclClass;
    }

    public void setMethodDeclClass(MethodDeclClass MethodDeclClass) {
        this.MethodDeclClass=MethodDeclClass;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclClassList!=null) MethodDeclClassList.accept(visitor);
        if(MethodDeclClass!=null) MethodDeclClass.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclClassList!=null) MethodDeclClassList.traverseTopDown(visitor);
        if(MethodDeclClass!=null) MethodDeclClass.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclClassList!=null) MethodDeclClassList.traverseBottomUp(visitor);
        if(MethodDeclClass!=null) MethodDeclClass.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethDeclClassL(\n");

        if(MethodDeclClassList!=null)
            buffer.append(MethodDeclClassList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclClass!=null)
            buffer.append(MethodDeclClass.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethDeclClassL]");
        return buffer.toString();
    }
}
