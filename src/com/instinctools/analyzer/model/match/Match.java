package com.instinctools.analyzer.model.match;

import com.instinctools.analyzer.model.match.method.Method;
import com.instinctools.analyzer.model.match.qualified.QualifiedName;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface Match {

    QualifiedName getQualifiedName();

    Method getMethod();
}
