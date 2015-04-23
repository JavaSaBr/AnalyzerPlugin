package com.instinctools.analyzer.model.standard.impl;

import com.instinctools.analyzer.model.standard.MutableStandard;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class StandardImpl implements MutableStandard {

    private String title;
    private String shortDescription;
    private String longDescription;
    private String content;

    public StandardImpl() {
        super();
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
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
    public String getLongDescription() {
        return longDescription;
    }

    @Override
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Override
    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
