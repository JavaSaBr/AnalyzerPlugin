package com.instinctools.analyzer.finder.java.impl;

import com.instinctools.analyzer.model.match.Match;
import com.instinctools.analyzer.model.match.method.Method;
import com.instinctools.analyzer.model.match.qualified.QualifiedName;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.service.MarkerService;
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
 * Documentation follows here.
 */
public class JavaPsiMethodVisitor extends JavaElementVisitor {

    private final List<Rule> rules;

    private final PsiJavaFile javaFile;

    private final Module module;

    public JavaPsiMethodVisitor(final List<Rule> rules, final PsiJavaFile javaFile) {
        this.javaFile = javaFile;
        this.rules = rules;

        final Project project = javaFile.getProject();
        final VirtualFile virtualFile = javaFile.getVirtualFile();

        final ProjectRootManager projectRootManager = ProjectRootManager.getInstance(project);
        final ProjectFileIndex fileIndex = projectRootManager.getFileIndex();
        this.module = fileIndex.getModuleForFile(virtualFile);
    }

    public Module getModule() {
        return module;
    }

    @Override
    public void visitElement(final PsiElement element) {
        super.visitElement(element);

        for (final PsiElement child : element.getChildren()) {
            child.accept(this);
        }
    }

    @Override
    public void visitMethodCallExpression(final PsiMethodCallExpression expression) {
        super.visitMethodCallExpression(expression);

        final PsiReferenceExpression methodExpression = expression.getMethodExpression();

        if (methodExpression == null) {
            return;
        }

        final PsiReference reference = methodExpression.getReference();

        if (reference == null) {
            return;
        }

        final PsiElement result = reference.resolve();

        if (result == null) {
            return;
        }

        final PsiElement methodClass = result.getContext();
        String methodClassName = null;

        if (methodClass instanceof PsiClass) {
            final PsiClass invokeClass = (PsiClass) methodClass;
            methodClassName = invokeClass.getQualifiedName();
        }

        if (methodClassName == null) {
            return;
        }

        final PsiIdentifier methodIndentifier = (PsiIdentifier) methodExpression.getLastChild();
        final String methodName = methodIndentifier.getText();

        final List<Rule> rules = getRules();

        for (final Rule rule : rules) {

            final Match match = rule.getMatch();
            final QualifiedName qualifiedName = match.getQualifiedName();
            final List<String> classNames = qualifiedName.getClassNames();

            if (!classNames.contains(methodClassName)) {
                continue;
            }

            final Method method = match.getMethod();
            final List<String> methodNames = method.getMethodNames();

            if (!methodNames.contains(methodName)) {
                continue;
            }

            final MarkerService markerService = ServiceManager.getService(MarkerService.class);
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
