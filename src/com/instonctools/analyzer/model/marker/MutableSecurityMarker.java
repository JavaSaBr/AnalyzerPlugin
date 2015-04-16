package com.instonctools.analyzer.model.marker;

import com.instonctools.analyzer.model.rule.Rule;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by ronn on 13.04.15.
 * //TODO need add documentation
 */
public interface MutableSecurityMarker extends SecurityMarker {

    void setRule(Rule rule);

    void setFile(VirtualFile file);

    void setTextRange(TextRange textRange);
}
