package com.instinctools.analyzer.model.category.impl;

import com.instinctools.analyzer.model.category.Category;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class CategoryImpl implements Category {

    private final String name;

    public CategoryImpl(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CategoryImpl{" + "name='" + name + '\'' + '}';
    }
}
