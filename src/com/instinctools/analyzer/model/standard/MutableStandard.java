package com.instinctools.analyzer.model.standard;

/**
 * Created by ronn on 15.04.15.
 * Documentation follows here.
 */
public interface MutableStandard extends Standard {

    void setTitle(String title);

    void setShortDescription(String shortDescription);

    void setLongDescription(String longDescription);

    void setContent(String content);
}
