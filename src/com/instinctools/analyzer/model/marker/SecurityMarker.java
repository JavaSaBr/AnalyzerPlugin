package com.instinctools.analyzer.model.marker;

import com.instinctools.analyzer.model.rule.Rule;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public interface SecurityMarker {

    Rule getRule();

    TextRange getTextRange();

    VirtualFile getFile();

    void init();
}
