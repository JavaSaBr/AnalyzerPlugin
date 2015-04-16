package com.instonctools.analyzer.model.lang;

import com.instonctools.analyzer.model.lang.impl.JavaLanguage;
import com.intellij.openapi.util.text.StringUtil;

/**
 * Created by ronn on 09.04.15.
 * //TODO need add documentation
 */
public class LanguageFactory {

    public static Language create(String name) {

        if (StringUtil.equalsIgnoreCase("java", name)) {
            return new JavaLanguage();
        }

        throw new IllegalArgumentException("unknown language name is " + name);
    }
}

