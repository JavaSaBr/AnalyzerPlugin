package com.instinctools.analyzer.builder;

import com.instinctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface StandardBuilder {

    Standard build(StandardSource source);
}
