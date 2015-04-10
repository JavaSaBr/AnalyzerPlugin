package com.instonctools.analyzer.model.rule.impl;

import com.instonctools.analyzer.model.category.Category;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.rule.MutableRule;
import com.instonctools.analyzer.model.standart.Standart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronn on 09.04.15.
 */
public class RuleImpl implements MutableRule {

    private final List<Standart> standarts;

    private String id;

    private String title;

    private String description;

    private Language language;

    private Category category;

    private Match match;

    public RuleImpl() {
        this.standarts = new ArrayList<>();
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public Language getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public List<Standart> getStandarts() {
        return standarts;
    }

    @Override
    public Match getMatch() {
        return match;
    }

    @Override
    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void addStandart(Standart standart) {
        this.standarts.add(standart);
    }

    @Override
    public void removeStandart(Standart standart) {
        this.standarts.remove(standart);
    }
}
