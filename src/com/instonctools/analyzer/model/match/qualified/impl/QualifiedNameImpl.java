package com.instonctools.analyzer.model.match.qualified.impl;

import com.instonctools.analyzer.model.match.qualified.QualifiedName;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 */
public class QualifiedNameImpl implements QualifiedName {

    private final List<String> classNames;

    public QualifiedNameImpl(List<String> classNames) {
        this.classNames = classNames;
    }

    @Override
    public List<String> getClassNames() {
        return classNames;
    }
}
