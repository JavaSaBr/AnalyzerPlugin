package com.instinctools.analyzer.test;

import com.instonctools.analyzer.builder.RuleBuilder;
import com.instonctools.analyzer.builder.RuleBuilderFactory;
import com.instonctools.analyzer.builder.xml.impl.XmlFileRuleSource;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * Created by ronn on 09.04.15.
 */
public class TestParseRules {

    @Test
    public void testParseRules() {

        URL resource = Thread.class.getResource("/testRule.xml");

        try {

            XmlFileRuleSource fileRuleSource = new XmlFileRuleSource(Paths.get(resource.toURI()));

            RuleBuilder builder = RuleBuilderFactory.getBuilderFor(fileRuleSource);
            builder.build(fileRuleSource);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
