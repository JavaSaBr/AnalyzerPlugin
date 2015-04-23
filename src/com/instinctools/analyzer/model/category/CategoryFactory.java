package com.instinctools.analyzer.model.category;

import com.instinctools.analyzer.model.category.impl.CategoryImpl;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class CategoryFactory {

    public static Category create(final String name) {
        return new CategoryImpl(name);
    }
}
