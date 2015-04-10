package com.instonctools.analyzer.builder;

import com.instonctools.analyzer.model.rule.Rule;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 */
public interface RuleBuilder {

    public List<Rule> build(RuleSource source);
}
