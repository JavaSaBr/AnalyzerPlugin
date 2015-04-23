package com.instinctools.analyzer.model.match.qualified;

import com.instinctools.analyzer.model.match.qualified.impl.QualifiedNameImpl;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class QualifiedNameFactory {

    public static QualifiedName create(List<String> classNames) {
        return new QualifiedNameImpl(classNames);
    }
}
