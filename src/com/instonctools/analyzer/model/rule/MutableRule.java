package com.instonctools.analyzer.model.rule;

import com.instonctools.analyzer.model.category.Category;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 09.04.15.
 */
public interface MutableRule extends Rule {

    public void setId(String id);

    public void setTitle(String title);

    public void setDescription(String description);

    public void setLanguage(Language language);

    public void setCategory(Category category);

    public void setMatch(Match match);

    public void addStandart(Standard standard);

    public void removeStandart(Standard standard);
}
