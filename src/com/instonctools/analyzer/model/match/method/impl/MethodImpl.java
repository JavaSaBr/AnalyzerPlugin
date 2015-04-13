package com.instonctools.analyzer.model.match.method.impl;

import com.instonctools.analyzer.model.match.method.Method;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 */
public class MethodImpl implements Method {

    private final List<String> methodNames;

    public MethodImpl(List<String> methodNames) {
        this.methodNames = methodNames;
    }

    @Override
    public List<String> getMethodNames() {
        return methodNames;
    }

    @Override
    public String toString() {
        return "MethodImpl{" +
                "methodNames=" + methodNames +
                '}';
    }
}
