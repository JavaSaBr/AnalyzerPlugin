package com.instonctools.analyzer.model.match.method;

import com.instonctools.analyzer.model.match.method.impl.MethodImpl;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
public class MethodFactory {

    public static Method create(List<String> methodNames) {
        return new MethodImpl(methodNames);
    }
}
