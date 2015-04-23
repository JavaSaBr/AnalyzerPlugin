package com.instinctools.analyzer.service.impl;

import com.instinctools.analyzer.builder.BuilderFactory;
import com.instinctools.analyzer.builder.RuleBuilder;
import com.instinctools.analyzer.builder.xml.impl.XmlURLRuleSource;
import com.instinctools.analyzer.model.lang.Language;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.service.RuleService;

import java.net.URL;
import java.util.*;

/**
 * Created by ronn on 10.04.15.
 */
public class RuleServiceImpl implements RuleService {

    private static final String[] RULE_PATHS = {
            "/rules/sql-injection.xml"
    };

    private final Map<Language, List<Rule>> languageRules;

    private final Map<String, Rule> rules;

    private final List<Rule> allRules;

    public RuleServiceImpl() {
        this.allRules = new ArrayList<Rule>();
        this.languageRules = new HashMap<Language, List<Rule>>();
        this.rules = new HashMap<String, Rule>();
        init();
    }

    private void init() {

        for (String rulePath : RULE_PATHS) {

            URL resource = getClass().getResource(rulePath);

            if (resource == null) {
                continue;
            }

            try {

                XmlURLRuleSource ruleSource = new XmlURLRuleSource(resource);

                RuleBuilder builder = BuilderFactory.getRuleBuilderFor(ruleSource);
                List<Rule> rules = builder.build(ruleSource);

                allRules.addAll(rules);

                for (Rule rule : rules) {

                    List<Rule> container = languageRules.get(rule.getLanguage());

                    if (container == null) {
                        container = new ArrayList<Rule>();
                        languageRules.put(rule.getLanguage(), container);
                    }

                    this.rules.put(rule.getId(), rule);
                    container.add(rule);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Rule> getAllRules() {
        return allRules;
    }

    @Override
    public List<Rule> getRulesFor(Language language) {
        List<Rule> rules = languageRules.get(language);
        return rules == null ? Collections.<Rule>emptyList() : rules;
    }

    @Override
    public Rule getRule(String id) {
        return rules.get(id);
    }
}

