package com.instinctools.analyzer.model.category;

import com.instinctools.analyzer.model.category.impl.CategoryImpl;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class CategoryFactory {

    public static Category create(String name) {
        return new CategoryImpl(name);
    }
}
