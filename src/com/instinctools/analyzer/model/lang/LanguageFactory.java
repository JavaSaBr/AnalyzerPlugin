package com.instinctools.analyzer.model.lang;

import com.instinctools.analyzer.model.lang.impl.JavaLanguage;
import com.intellij.openapi.util.text.StringUtil;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class LanguageFactory {

    public static Language create(final String name) {

        if (StringUtil.equalsIgnoreCase("java", name)) {
            return new JavaLanguage();
        }

        throw new IllegalArgumentException("unknown language name is " + name);
    }
}
