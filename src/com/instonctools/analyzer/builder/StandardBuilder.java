package com.instonctools.analyzer.builder;

import com.instonctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface StandardBuilder {

    Standard build(StandardSource source);
}
