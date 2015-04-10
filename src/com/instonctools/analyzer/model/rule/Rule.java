package com.instonctools.analyzer.model.rule;

import com.instonctools.analyzer.model.category.Category;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.standart.Standart;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 */
public interface Rule {

    public String getId();

    public String getTitle();

    public String getDescription();

    public Language getLanguage();

    public Category getCategory();

    public Match getMatch();

    public List<Standart> getStandarts();
}
