package com.instonctools.analyzer.model.standard;

/**
 * Created by ronn on 15.04.15.
 * //TODO need add documentation
 */
public interface MutableStandard extends Standard {

    void setTitle(String title);

    void setShortDescription(String shortDescription);

    void setLongDescription(String longDescription);

    void setContent(String content);
}
