package com.instonctools.analyzer.model.match;

import com.instonctools.analyzer.model.match.impl.MatchImpl;
import com.instonctools.analyzer.model.match.method.Method;
import com.instonctools.analyzer.model.match.qualified.QualifiedName;

/**
 * Created by ronn on 09.04.15.
 */
public class MatchFactory {

    public static Match create(QualifiedName qualifiedName, Method method) {
        return new MatchImpl(qualifiedName, method);
    }
}
