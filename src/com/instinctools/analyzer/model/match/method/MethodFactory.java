package com.instinctools.analyzer.model.match.method;

import com.instinctools.analyzer.model.match.method.impl.MethodImpl;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * Documentation follows here.
 */
public class MethodFactory {

    public static Method create(final List<String> methodNames) {
        return new MethodImpl(methodNames);
    }
}
