package com.instonctools.analyzer.model.context.impl;

import com.instonctools.analyzer.model.context.Context;

/**
 * Created by ronn on 09.04.15.
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
}
