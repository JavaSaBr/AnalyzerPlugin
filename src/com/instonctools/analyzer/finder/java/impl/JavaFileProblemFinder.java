package com.instonctools.analyzer.finder.java.impl;

import com.instonctools.analyzer.finder.ProblemFinder;
import com.instonctools.analyzer.model.lang.LanguageFactory;
import com.instonctools.analyzer.model.rule.Rule;
import com.instonctools.analyzer.service.MarkerService;
import com.instonctools.analyzer.service.RuleService;
import com.intellij.lang.Language;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.*;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
public class JavaFileProblemFinder implements ProblemFinder {

    @Override
    public void find(PsiFile psiFile) {

        PsiJavaFile javaFile = (PsiJavaFile) psiFile;

        MarkerService markerService = ServiceManager.getService(MarkerService.class);
        markerService.clearMarkersFor(psiFile.getProject(), javaFile.getVirtualFile());

        Language language = javaFile.getLanguage();
        PsiElement context = javaFile.getContext();

        RuleService ruleService = ServiceManager.getService(RuleService.class);
        List<Rule> rules = ruleService.getRulesFor(LanguageFactory.create(language.getDisplayName()));

        if (rules.isEmpty()) {
            return;
        }

        JavaPsiMethodVisiter methodVisiter = new JavaPsiMethodVisiter(rules, javaFile);

        PsiClass[] classes = javaFile.getClasses();

        for (PsiClass javaClass : classes) {

            PsiMethod[] methods = javaClass.getMethods();

            for (PsiMethod psiMethod : methods) {
                psiMethod.accept(methodVisiter);
            }
        }
    }
}
