package com.instonctools.analyzer.model.standard;

/**
 * Created by ronn on 15.04.15.
 */
public interface MutableStandard extends Standard {

    public void setTitle(String title);

    public void setShortDescription(String shortDescription);

    public void setLongDescription(String longDescription);

    public void setContent(String content);
}
