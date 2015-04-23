package com.instinctools.analyzer.model.match.qualified.impl;

import com.instinctools.analyzer.model.match.qualified.QualifiedName;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class QualifiedNameImpl implements QualifiedName {

    private final List<String> classNames;

    public QualifiedNameImpl(final List<String> classNames) {
        this.classNames = classNames;
    }

    @Override
    public List<String> getClassNames() {
        return classNames;
    }

    @Override
    public String toString() {
        return "QualifiedNameImpl{" + "classNames=" + classNames + '}';
    }
}
