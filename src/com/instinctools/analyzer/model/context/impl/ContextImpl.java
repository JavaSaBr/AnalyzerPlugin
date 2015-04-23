package com.instinctools.analyzer.model.context.impl;

import com.instinctools.analyzer.model.context.Context;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class ContextImpl implements Context {

    private String name;

    public ContextImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "ContextImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
