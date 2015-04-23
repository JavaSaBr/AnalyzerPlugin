package com.instinctools.analyzer.finder.java.impl;

import com.instinctools.analyzer.model.match.Match;
import com.instinctools.analyzer.model.match.method.Method;
import com.instinctools.analyzer.model.match.qualified.QualifiedName;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.service.MarkerService;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
public class JavaPsiMethodVisitor extends JavaElementVisitor {

    private List<Rule> rules;

    private PsiJavaFile javaFile;

    private Module module;

    public JavaPsiMethodVisitor(List<Rule> rules, PsiJavaFile javaFile) {
        this.javaFile = javaFile;
        this.rules = rules;

        Project project = javaFile.getProject();
        VirtualFile virtualFile = javaFile.getVirtualFile();

        ProjectRootManager projectRootManager = ProjectRootManager.getInstance(project);
        ProjectFileIndex fileIndex = projectRootManager.getFileIndex();
        this.module = fileIndex.getModuleForFile(virtualFile);
    }

    public Module getModule() {
        return module;
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

        ASTNode node = methodExpression.getNode();

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

            MarkerService markerService = ServiceManager.getService(MarkerService.class);
            markerService.buildMarker(rule, expression);
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    public PsiJavaFile getJavaFile() {
        return javaFile;
    }
}
