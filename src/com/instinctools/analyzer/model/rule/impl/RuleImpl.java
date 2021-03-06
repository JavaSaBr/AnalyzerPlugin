package com.instinctools.analyzer.model.rule.impl;

import com.instinctools.analyzer.model.category.Category;
import com.instinctools.analyzer.model.lang.Language;
import com.instinctools.analyzer.model.match.Match;
import com.instinctools.analyzer.model.rule.MutableRule;
import com.instinctools.analyzer.model.standard.Standard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class RuleImpl implements MutableRule {

    private final List<Standard> standards;

    private String id;

    private String title;

    private String description;

    private Language language;

    private Category category;

    private Match match;

    public RuleImpl() {
        this.standards = new ArrayList<Standard>();
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(final Category category) {
        this.category = category;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(final Language language) {
        this.language = language;
    }

    @Override
    public List<Standard> getStandards() {
        return standards;
    }

    @Override
    public Match getMatch() {
        return match;
    }

    @Override
    public void setMatch(final Match match) {
        this.match = match;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public void addStandart(final Standard standard) {
        this.standards.add(standard);
    }

    @Override
    public void removeStandart(final Standard standard) {
        this.standards.remove(standard);
    }

    @Override
    public String toString() {
        return "RuleImpl{" + "standards=" + standards + ", id='" + id + '\'' + ", title='" + title + '\'' + ", description='" + description + '\'' + ", language=" + language + ", category="
                + category + ", match=" + match + '}';
    }
}
