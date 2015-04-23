package com.instinctools.analyzer.service;

import com.instinctools.analyzer.model.lang.Language;
import com.instinctools.analyzer.model.rule.Rule;

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
