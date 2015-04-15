package com.instonctools.analyzer.builder;

import com.instonctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 09.04.15.
 */
public interface StandardBuilder {

    public Standard build(StandardSource source);
}
