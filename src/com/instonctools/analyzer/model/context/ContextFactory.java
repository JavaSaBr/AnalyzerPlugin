package com.instonctools.analyzer.model.context;

import com.instonctools.analyzer.model.context.impl.ContextImpl;

/**
 * Created by ronn on 09.04.15.
 */
public class ContextFactory {

    public static Context create(String name) {
        return new ContextImpl(name);
    }
}
