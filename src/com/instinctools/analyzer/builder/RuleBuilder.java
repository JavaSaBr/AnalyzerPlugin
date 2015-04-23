package com.instinctools.analyzer.builder;

import com.instinctools.analyzer.model.rule.Rule;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface RuleBuilder {

    List<Rule> build(RuleSource source);
}
