// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ClassUnmatch extends ClassBody {

    private ClassUnmat ClassUnmat;

    public ClassUnmatch (ClassUnmat ClassUnmat) {
        this.ClassUnmat=ClassUnmat;
        if(ClassUnmat!=null) ClassUnmat.setParent(this);
    }

    public ClassUnmat getClassUnmat() {
        return ClassUnmat;
    }

    public void setClassUnmat(ClassUnmat ClassUnmat) {
        this.ClassUnmat=ClassUnmat;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassUnmat!=null) ClassUnmat.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassUnmat!=null) ClassUnmat.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassUnmat!=null) ClassUnmat.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassUnmatch(\n");

        if(ClassUnmat!=null)
            buffer.append(ClassUnmat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassUnmatch]");
        return buffer.toString();
    }
}
