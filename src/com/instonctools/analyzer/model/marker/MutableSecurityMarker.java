package com.instonctools.analyzer.model.marker;

import com.instonctools.analyzer.model.rule.Rule;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by ronn on 13.04.15.
 */
public interface MutableSecurityMarker extends SecurityMarker {

    public void setRule(Rule rule);

    public void setFile(VirtualFile file);

    public void setTextRange(TextRange textRange);
}
