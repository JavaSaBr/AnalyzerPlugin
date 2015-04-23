package com.instinctools.analyzer.model.context;

import com.instinctools.analyzer.model.context.impl.ContextImpl;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class ContextFactory {

    public static Context create(String name) {
        return new ContextImpl(name);
    }
}
