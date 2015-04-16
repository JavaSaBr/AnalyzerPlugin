package com.instonctools.analyzer.model.category.impl;

import com.instonctools.analyzer.model.category.Category;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class CategoryImpl implements Category {

    private final String name;

    public CategoryImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CategoryImpl{" +
                "name='" + name + '\'' +
                '}';
    }
}
