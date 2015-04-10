package com.instonctools.analyzer.model.category;

import com.instonctools.analyzer.model.category.impl.CategoryImpl;

/**
 * Created by ronn on 09.04.15.
 */
public class CategoryFactory {

    public static Category create(String name) {
        return new CategoryImpl(name);
    }
}
