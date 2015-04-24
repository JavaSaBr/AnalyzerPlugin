package com.instinctools.analyzer.model.marker.impl;

import com.instinctools.analyzer.model.marker.MutableSecurityMarker;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.service.RuleService;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Transient;

/**
 * Created by ronn on 13.04.15.
 * Documentation follows here.
 */
public class SecurityMarkerImpl implements MutableSecurityMarker {

    @Property
    public String ruleId;
    @Property
    public String filePath;

    @Property
    public int startOffset;
    @Property
    public int endOffset;

    private Rule rule;
    private VirtualFile file;
    private TextRange textRange;

    public SecurityMarkerImpl() {
        super();
    }

    @Transient
    @Override
    public TextRange getTextRange() {
        return textRange;
    }

    @Override
    public void setTextRange(final TextRange textRange) {
        this.textRange = textRange;
        this.startOffset = textRange.getStartOffset();
        this.endOffset = textRange.getEndOffset();
    }

    @Transient
    @Override
    public Rule getRule() {
        return rule;
    }

    @Override
    public void setRule(final Rule rule) {
        this.rule = rule;
        this.ruleId = rule.getId();
    }

    @Transient
    @Override
    public VirtualFile getFile() {
        return file;
    }

    public void setFile(final VirtualFile file) {
        this.file = file;
        this.filePath = file.getUrl();
    }

    @Override
    public void init() {

        final VirtualFileManager fileManager = VirtualFileManager.getInstance();
        final RuleService ruleService = ServiceManager.getService(RuleService.class);

        this.rule = ruleService.getRule(ruleId);
        this.file = fileManager.findFileByUrl(filePath);
        this.textRange = new TextRange(startOffset, endOffset);
    }

    @Override
    public String toString() {
        return "SecurityMarkerImpl{" + "ruleId='" + ruleId + '\'' + ", filePath='" + filePath + '\'' + ", startOffset=" + startOffset + ", endOffset=" + endOffset + ", rule=" + rule + ", file="
                + file + ", textRange=" + textRange + '}';
    }
}
