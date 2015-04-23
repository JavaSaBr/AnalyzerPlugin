package com.instinctools.analyzer.model.marker;

import com.instinctools.analyzer.model.rule.Rule;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by ronn on 13.04.15.
 * Documentation follows here.
 */
public interface MutableSecurityMarker extends SecurityMarker {

    void setRule(Rule rule);

    void setFile(VirtualFile file);

    void setTextRange(TextRange textRange);
}
