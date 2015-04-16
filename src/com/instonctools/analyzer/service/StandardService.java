package com.instonctools.analyzer.service;

import com.instonctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 15.04.15.
 * //TODO need add documentation
 */
public interface StandardService {

    Standard getStandardForFile(String filePath);
}
