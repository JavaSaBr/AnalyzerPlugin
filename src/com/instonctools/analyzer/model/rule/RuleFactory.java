package com.instonctools.analyzer.model.rule;

import com.instonctools.analyzer.model.rule.impl.RuleImpl;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class RuleFactory {

    public static MutableRule create() {
        return new RuleImpl();
    }
}
