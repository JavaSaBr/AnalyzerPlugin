package com.instinctools.analyzer.model.match;

import com.instinctools.analyzer.model.match.impl.MatchImpl;
import com.instinctools.analyzer.model.match.method.Method;
import com.instinctools.analyzer.model.match.qualified.QualifiedName;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class MatchFactory {

    public static Match create(QualifiedName qualifiedName, Method method) {
        return new MatchImpl(qualifiedName, method);
    }
}
