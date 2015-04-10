package com.instonctools.analyzer.model.category.impl;

import com.instonctools.analyzer.model.category.Category;

/**
 * Created by ronn on 09.04.15.
 */
public class CategoryImpl implements Category {

    private final String name;

    public CategoryImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
