package com.instonctools.analyzer.registry;

import com.instonctools.analyzer.builder.BuilderFactory;
import com.instonctools.analyzer.builder.RuleBuilder;
import com.instonctools.analyzer.builder.RuleSource;
import com.instonctools.analyzer.model.rule.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class RuleRegistry {

    private static final RuleRegistry INSTANCE = new RuleRegistry();
    private final Map<String, Rule> rules;

    private RuleRegistry() {
        this.rules = new HashMap<String, Rule>();
    }

    public static RuleRegistry getInstance() {
        return INSTANCE;
    }

    public List<Rule> getRules() {
        return new ArrayList<Rule>(rules.values());
    }

    private void addRule(Rule rule) {

        if (rules.containsKey(rule.getId())) {
            throw new IllegalArgumentException("found duplicate rule for id " + rule.getId());
        }

        rules.put(rule.getId(), rule);
    }

    public void register(RuleSource ruleSource) {

        RuleBuilder builder = BuilderFactory.getRuleBuilderFor(ruleSource);

        for (Rule rule : builder.build(ruleSource)) {
            addRule(rule);
        }
    }
}
