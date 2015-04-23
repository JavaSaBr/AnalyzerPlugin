package com.instinctools.analyzer.model.match.qualified;

import com.instinctools.analyzer.model.match.qualified.impl.QualifiedNameImpl;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class QualifiedNameFactory {

    public static QualifiedName create(final List<String> classNames) {
        return new QualifiedNameImpl(classNames);
    }
}
