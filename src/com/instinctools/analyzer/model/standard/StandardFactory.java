package com.instinctools.analyzer.model.standard;

import com.instinctools.analyzer.model.standard.impl.StandardImpl;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class StandardFactory {

    public static MutableStandard create() {
        return new StandardImpl();
    }
}
