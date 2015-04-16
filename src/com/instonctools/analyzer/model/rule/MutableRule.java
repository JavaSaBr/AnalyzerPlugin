package com.instonctools.analyzer.model.rule;

import com.instonctools.analyzer.model.category.Category;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public interface MutableRule extends Rule {

    void setId(String id);

    void setTitle(String title);

    void setDescription(String description);

    void setLanguage(Language language);

    void setCategory(Category category);

    void setMatch(Match match);

    void addStandart(Standard standard);

    void removeStandart(Standard standard);
}
