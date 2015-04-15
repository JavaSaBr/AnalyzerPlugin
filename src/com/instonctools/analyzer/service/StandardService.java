package com.instonctools.analyzer.service;

import com.instonctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 15.04.15.
 */
public interface StandardService {

    public Standard getStandardForFile(String filePath);
}
