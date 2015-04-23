package com.instinctools.analyzer.finder.java.impl;

import com.instinctools.analyzer.finder.ProblemFinder;
import com.instinctools.analyzer.model.lang.LanguageFactory;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.service.MarkerService;
import com.instinctools.analyzer.service.RuleService;
import com.intellij.lang.Language;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiMethod;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * Documentation follows here.
 */
public class JavaFileProblemFinder implements ProblemFinder {

    @Override
    public void find(final PsiFile psiFile) {

        final PsiJavaFile javaFile = (PsiJavaFile) psiFile;

        final MarkerService markerService = ServiceManager.getService(MarkerService.class);
        markerService.clearMarkersFor(psiFile.getProject(), javaFile.getVirtualFile());

        final Language language = javaFile.getLanguage();

        final RuleService ruleService = ServiceManager.getService(RuleService.class);
        final List<Rule> rules = ruleService.getRulesFor(LanguageFactory.create(language.getDisplayName()));

        if (rules.isEmpty()) {
            return;
        }

        final JavaPsiMethodVisitor methodVisiter = new JavaPsiMethodVisitor(rules, javaFile);

        final PsiClass[] classes = javaFile.getClasses();

        for (final PsiClass javaClass : classes) {

            final PsiMethod[] methods = javaClass.getMethods();

            for (final PsiMethod psiMethod : methods) {
                psiMethod.accept(methodVisiter);
            }
        }
    }
}
