package com.instonctools.analyzer.service;

import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.rule.Rule;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 * //TODO need add documentation
 */
public interface RuleService {

    List<Rule> getAllRules();

    List<Rule> getRulesFor(Language language);

    Rule getRule(String id);
}
