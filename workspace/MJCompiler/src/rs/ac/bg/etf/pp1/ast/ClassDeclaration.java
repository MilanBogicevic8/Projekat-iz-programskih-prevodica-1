// generated with ast extension for cup
// version 0.8
// 7/1/2024 7:57:9


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ClassDecl {

    private ClassDeclBegin ClassDeclBegin;
    private ClassBody ClassBody;

    public ClassDeclaration (ClassDeclBegin ClassDeclBegin, ClassBody ClassBody) {
        this.ClassDeclBegin=ClassDeclBegin;
        if(ClassDeclBegin!=null) ClassDeclBegin.setParent(this);
        this.ClassBody=ClassBody;
        if(ClassBody!=null) ClassBody.setParent(this);
    }

    public ClassDeclBegin getClassDeclBegin() {
        return ClassDeclBegin;
    }

    public void setClassDeclBegin(ClassDeclBegin ClassDeclBegin) {
        this.ClassDeclBegin=ClassDeclBegin;
    }

    public ClassBody getClassBody() {
        return ClassBody;
    }

    public void setClassBody(ClassBody ClassBody) {
        this.ClassBody=ClassBody;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassDeclBegin!=null) ClassDeclBegin.accept(visitor);
        if(ClassBody!=null) ClassBody.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassDeclBegin!=null) ClassDeclBegin.traverseTopDown(visitor);
        if(ClassBody!=null) ClassBody.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassDeclBegin!=null) ClassDeclBegin.traverseBottomUp(visitor);
        if(ClassBody!=null) ClassBody.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        if(ClassDeclBegin!=null)
            buffer.append(ClassDeclBegin.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassBody!=null)
            buffer.append(ClassBody.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
