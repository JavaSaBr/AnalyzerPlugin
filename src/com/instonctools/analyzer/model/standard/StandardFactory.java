package com.instonctools.analyzer.model.standard;

import com.instonctools.analyzer.model.standard.impl.StandardImpl;

/**
 * Created by ronn on 09.04.15.
 */
public class StandardFactory {

    public static MutableStandard create() {
        return new StandardImpl();
    }
}
