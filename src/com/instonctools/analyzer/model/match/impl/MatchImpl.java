package com.instonctools.analyzer.model.match.impl;

import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.match.method.Method;
import com.instonctools.analyzer.model.match.qualified.QualifiedName;

/**
 * Created by ronn on 09.04.15.
 */
public class MatchImpl implements Match {

    private final QualifiedName qualifiedName;

    private final Method method;

    public MatchImpl(QualifiedName qualifiedName, Method method) {
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
        return "MatchImpl{" +
                "qualifiedName=" + qualifiedName +
                ", method=" + method +
                '}';
    }
}
