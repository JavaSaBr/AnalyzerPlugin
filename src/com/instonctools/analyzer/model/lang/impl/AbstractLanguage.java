package com.instonctools.analyzer.model.lang.impl;

import com.instonctools.analyzer.model.lang.Language;

/**
 * Created by ronn on 09.04.15.
 */
public abstract class AbstractLanguage implements Language {

    private final String name;

    public AbstractLanguage(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
