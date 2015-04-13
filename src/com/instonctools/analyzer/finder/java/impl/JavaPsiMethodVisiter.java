package com.instonctools.analyzer.finder.java.impl;

import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.match.method.Method;
import com.instonctools.analyzer.model.match.qualified.QualifiedName;
import com.instonctools.analyzer.model.rule.Rule;
import com.intellij.psi.*;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 */
public class JavaPsiMethodVisiter extends JavaElementVisitor {

    private List<Rule> rules;

    private PsiJavaFile javaFile;

    public JavaPsiMethodVisiter(List<Rule> rules, PsiJavaFile javaFile) {
        this.javaFile = javaFile;
        this.rules = rules;
    }

    @Override
    public void visitElement(PsiElement element) {
        super.visitElement(element);

        for (PsiElement child : element.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression expression) {
        super.visitMethodCallExpression(expression);

        PsiReferenceExpression methodExpression = expression.getMethodExpression();
        PsiReference reference = methodExpression.getReference();
        PsiElement result = reference.resolve();

        if (result == null) {
            return;
        }

        PsiElement methodClass = result.getContext();
        String methodClassName = null;

        if (methodClass instanceof PsiClass) {
            PsiClass invokeClass = (PsiClass) methodClass;
            methodClassName = invokeClass.getQualifiedName();
        }

        if (methodClassName == null) {
            return;
        }

        PsiIdentifier methodIndentifier = (PsiIdentifier) methodExpression.getLastChild();
        String methodName = methodIndentifier.getText();

        List<Rule> rules = getRules();

        for (Rule rule : rules) {

            Match match = rule.getMatch();
            QualifiedName qualifiedName = match.getQualifiedName();
            List<String> classNames = qualifiedName.getClassNames();

            if (!classNames.contains(methodClassName)) {
                continue;
            }

            Method method = match.getMethod();
            List<String> methodNames = method.getMethodNames();

            if (!methodNames.contains(methodName)) {
                continue;
            }

            System.out.println("SECURITY WARNING! " + methodClassName + "." + methodName + " in " + javaFile.getVirtualFile().getCanonicalPath());
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    public PsiJavaFile getJavaFile() {
        return javaFile;
    }
}
