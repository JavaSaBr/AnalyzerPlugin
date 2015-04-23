package com.instinctools.analyzer.model.match.impl;

import com.instinctools.analyzer.model.match.Match;
import com.instinctools.analyzer.model.match.method.Method;
import com.instinctools.analyzer.model.match.qualified.QualifiedName;

/**
 * Created by ronn on 09.04.15. //TODO need add documentation
 */
public class MatchImpl implements Match {

    private final QualifiedName qualifiedName;

    private final Method method;

    public MatchImpl(final QualifiedName qualifiedName, final Method method) {
        this.qualifiedName = qualifiedName;
        this.method = method;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public QualifiedName getQualifiedName() {
        return qualifiedName;
    }

    @Override
    public String toString() {
        return "MatchImpl{" + "qualifiedName=" + qualifiedName + ", method=" + method + '}';
    }
}
