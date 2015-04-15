package com.instinctools.analyzer.test;

import com.instonctools.analyzer.builder.BuilderFactory;
import com.instonctools.analyzer.builder.RuleBuilder;
import com.instonctools.analyzer.builder.xml.impl.XmlFileRuleSource;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by ronn on 09.04.15.
 */
public class TestParseRules {

    @Test
    public void testParseRules() {

        URL resource = Thread.class.getResource("/testRule.xml");

        try {

            XmlFileRuleSource fileRuleSource = new XmlFileRuleSource(new File((resource.toURI())));

            RuleBuilder builder = BuilderFactory.getRuleBuilderFor(fileRuleSource);
            builder.build(fileRuleSource);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
