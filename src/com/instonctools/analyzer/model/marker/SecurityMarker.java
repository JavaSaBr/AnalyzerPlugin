package com.instonctools.analyzer.model.marker;

import com.instonctools.analyzer.model.rule.Rule;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by ronn on 13.04.15.
 */
public interface SecurityMarker {

    public Rule getRule();

    public TextRange getTextRange();

    public VirtualFile getFile();

    public void init();
}
