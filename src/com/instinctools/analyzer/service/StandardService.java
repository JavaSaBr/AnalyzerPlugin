package com.instinctools.analyzer.service;

import com.instinctools.analyzer.model.standard.Standard;

/**
 * Created by ronn on 15.04.15.
 * //TODO need add documentation
 */
public interface StandardService {

    Standard getStandardForFile(String filePath);
}
