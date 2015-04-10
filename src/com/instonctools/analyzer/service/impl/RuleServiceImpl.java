package com.instonctools.analyzer.service.impl;

import com.instonctools.analyzer.builder.RuleBuilder;
import com.instonctools.analyzer.builder.RuleBuilderFactory;
import com.instonctools.analyzer.builder.xml.impl.XmlFileRuleSource;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.rule.Rule;
import com.instonctools.analyzer.service.RuleService;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by ronn on 10.04.15.
 */
public class RuleServiceImpl implements RuleService {

    private static final String[] RULE_PATHS = {
            "/rules/sql-injection.xml"
    };

    private final Map<Language, List<Rule>> languageRules;

    private final List<Rule> allRules;

    public RuleServiceImpl() {
        this.allRules = new ArrayList<Rule>();
        this.languageRules = new HashMap<Language, List<Rule>>();
        init();
    }

    private void init() {

        for (String rulePath : RULE_PATHS) {

            URL resource = getClass().getResource(rulePath);

            if (resource == null) {
                continue;
            }

            try {

                XmlFileRuleSource fileRuleSource = new XmlFileRuleSource(Paths.get(resource.toURI()));

                RuleBuilder builder = RuleBuilderFactory.getBuilderFor(fileRuleSource);
                List<Rule> rules = builder.build(fileRuleSource);

                allRules.addAll(rules);

                for (Rule rule : rules) {

                    List<Rule> container = languageRules.get(rule.getLanguage());

                    if (container == null) {
                        container = new ArrayList<Rule>();
                        languageRules.put(rule.getLanguage(), container);
                    }

                    container.add(rule);
                }

            } catch (URISyntaxException e) {
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
}

