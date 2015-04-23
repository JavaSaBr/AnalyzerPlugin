package com.instinctools.analyzer.model.rule;

import com.instinctools.analyzer.model.category.Category;
import com.instinctools.analyzer.model.lang.Language;
import com.instinctools.analyzer.model.match.Match;
import com.instinctools.analyzer.model.standard.Standard;

import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
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
