package com.instonctools.analyzer.service;

import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.rule.Rule;

import java.util.List;

/**
 * Created by ronn on 10.04.15.
 */
public interface RuleService {

    public List<Rule> getAllRules();

    public List<Rule> getRulesFor(Language language);
}
