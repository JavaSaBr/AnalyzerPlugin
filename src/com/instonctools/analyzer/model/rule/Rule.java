package com.instonctools.analyzer.model.rule;

import com.instonctools.analyzer.model.category.Category;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.standard.Standard;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface Rule {

    String getId();

    String getTitle();

    String getDescription();

    Language getLanguage();

    Category getCategory();

    Match getMatch();

    List<Standard> getStandards();
}
