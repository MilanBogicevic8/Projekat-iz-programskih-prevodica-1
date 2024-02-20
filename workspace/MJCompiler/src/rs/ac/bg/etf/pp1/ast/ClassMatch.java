// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ClassMatch extends ClassBody {

    private ClassMat ClassMat;

    public ClassMatch (ClassMat ClassMat) {
        this.ClassMat=ClassMat;
        if(ClassMat!=null) ClassMat.setParent(this);
    }

    public ClassMat getClassMat() {
        return ClassMat;
    }

    public void setClassMat(ClassMat ClassMat) {
        this.ClassMat=ClassMat;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassMat!=null) ClassMat.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassMat!=null) ClassMat.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassMat!=null) ClassMat.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMatch(\n");

        if(ClassMat!=null)
            buffer.append(ClassMat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMatch]");
        return buffer.toString();
    }
}
