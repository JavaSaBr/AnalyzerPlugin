package com.instonctools.analyzer.model.lang.impl;

import com.instonctools.analyzer.model.lang.Language;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public abstract class AbstractLanguage implements Language {

    private final String name;

    public AbstractLanguage(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractLanguage that = (AbstractLanguage) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
