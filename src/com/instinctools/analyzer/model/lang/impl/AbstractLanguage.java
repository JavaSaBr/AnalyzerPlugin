package com.instinctools.analyzer.model.lang.impl;

import com.instinctools.analyzer.model.lang.Language;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public abstract class AbstractLanguage implements Language {

    private final String name;

    public AbstractLanguage(final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "name='" + name + '\'' + '}';
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final AbstractLanguage that = (AbstractLanguage) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
