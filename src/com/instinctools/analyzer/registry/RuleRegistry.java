package com.instinctools.analyzer.registry;

import com.instinctools.analyzer.builder.BuilderFactory;
import com.instinctools.analyzer.builder.RuleBuilder;
import com.instinctools.analyzer.builder.RuleSource;
import com.instinctools.analyzer.model.rule.Rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
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

    private void addRule(final Rule rule) {

        if (rules.containsKey(rule.getId())) {
            throw new IllegalArgumentException("found duplicate rule for id " + rule.getId());
        }

        rules.put(rule.getId(), rule);
    }

    public void register(final RuleSource ruleSource) {

        final RuleBuilder builder = BuilderFactory.getRuleBuilderFor(ruleSource);

        for (final Rule rule : builder.build(ruleSource)) {
            addRule(rule);
        }
    }
}
