// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclNamespaceL extends MethodDeclNamespaceList {

    private MethodDeclNamespaceList MethodDeclNamespaceList;
    private MethodDeclNamespace MethodDeclNamespace;

    public MethodDeclNamespaceL (MethodDeclNamespaceList MethodDeclNamespaceList, MethodDeclNamespace MethodDeclNamespace) {
        this.MethodDeclNamespaceList=MethodDeclNamespaceList;
        if(MethodDeclNamespaceList!=null) MethodDeclNamespaceList.setParent(this);
        this.MethodDeclNamespace=MethodDeclNamespace;
        if(MethodDeclNamespace!=null) MethodDeclNamespace.setParent(this);
    }

    public MethodDeclNamespaceList getMethodDeclNamespaceList() {
        return MethodDeclNamespaceList;
    }

    public void setMethodDeclNamespaceList(MethodDeclNamespaceList MethodDeclNamespaceList) {
        this.MethodDeclNamespaceList=MethodDeclNamespaceList;
    }

    public MethodDeclNamespace getMethodDeclNamespace() {
        return MethodDeclNamespace;
    }

    public void setMethodDeclNamespace(MethodDeclNamespace MethodDeclNamespace) {
        this.MethodDeclNamespace=MethodDeclNamespace;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclNamespaceList!=null) MethodDeclNamespaceList.accept(visitor);
        if(MethodDeclNamespace!=null) MethodDeclNamespace.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclNamespaceList!=null) MethodDeclNamespaceList.traverseTopDown(visitor);
        if(MethodDeclNamespace!=null) MethodDeclNamespace.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclNamespaceList!=null) MethodDeclNamespaceList.traverseBottomUp(visitor);
        if(MethodDeclNamespace!=null) MethodDeclNamespace.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclNamespaceL(\n");

        if(MethodDeclNamespaceList!=null)
            buffer.append(MethodDeclNamespaceList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclNamespace!=null)
            buffer.append(MethodDeclNamespace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclNamespaceL]");
        return buffer.toString();
    }
}
