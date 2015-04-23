package com.instinctools.analyzer.builder.xml.impl;

import com.instinctools.analyzer.builder.RuleBuilder;
import com.instinctools.analyzer.builder.RuleSource;
import com.instinctools.analyzer.builder.xml.XmlRuleSource;
import com.instinctools.analyzer.model.category.Category;
import com.instinctools.analyzer.model.category.CategoryFactory;
import com.instinctools.analyzer.model.context.Context;
import com.instinctools.analyzer.model.context.ContextFactory;
import com.instinctools.analyzer.model.lang.Language;
import com.instinctools.analyzer.model.lang.LanguageFactory;
import com.instinctools.analyzer.model.match.Match;
import com.instinctools.analyzer.model.match.MatchFactory;
import com.instinctools.analyzer.model.match.method.Method;
import com.instinctools.analyzer.model.match.method.MethodFactory;
import com.instinctools.analyzer.model.match.qualified.QualifiedName;
import com.instinctools.analyzer.model.match.qualified.QualifiedNameFactory;
import com.instinctools.analyzer.model.rule.MutableRule;
import com.instinctools.analyzer.model.rule.Rule;
import com.instinctools.analyzer.model.rule.RuleFactory;
import com.instinctools.analyzer.model.standard.Standard;
import com.instinctools.analyzer.service.StandardService;
import com.intellij.openapi.components.ServiceManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ronn on 09.04.15.
 * Documentation follows here.
 */
public class XmlRuleBuilder implements RuleBuilder {

    public static final String NODE_RULES = "Rules";
    public static final String NODE_RULE = "Rule";
    public static final String NODE_CATEGORY = "Category";
    public static final String NODE_TITLE = "Title";
    public static final String NODE_MATCH = "Match";
    public static final String NODE_STANDARDS = "Standards";
    public static final String NODE_METHOD = "Method";
    public static final String NODE_QUALIFIED_NAME = "QualifiedName";
    public static final String NODE_STANDARD = "Standard";
    public static final String NODE_CONTEXT = "Context";
    public static final String NODE_DESCRIPTION = "Description";

    public static final String ATTR_ID = "id";
    public static final String ATTR_LANG = "lang";
    public static final String ATTR_FILE = "file";

    @Override
    public List<Rule> build(final RuleSource source) {

        if (!(source instanceof XmlRuleSource)) {
            throw new IllegalArgumentException("this is builder only for " + XmlRuleSource.class.getName());
        }

        Document document = null;

        try {

            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(((XmlRuleSource) source).getStream());

        } catch (final Exception e) {
            throw new RuntimeException(e);
        }

        final List<Rule> result = new ArrayList<Rule>();

        for (Node node = document.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_RULES.equals(node.getNodeName())) {
                continue;
            }

            parseRules((Element) node, result);
        }

        return result;
    }

    private void parseRules(final Element parent, final List<Rule> container) {

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_RULE.equals(node.getNodeName())) {
                continue;
            }

            final Rule rule = parseRule((Element) node);

            if (rule != null) {
                container.add(rule);
            }
        }
    }

    private Rule parseRule(final Element parent) {

        final String id = parent.getAttribute(ATTR_ID);
        final String langName = parent.getAttribute(ATTR_LANG);

        final Language language = LanguageFactory.create(langName);

        final MutableRule rule = RuleFactory.create();
        rule.setLanguage(language);
        rule.setId(id);

        parseRule(rule, parent);

        return rule;
    }

    private void parseRule(final MutableRule rule, final Element parent) {

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            final Element element = (Element) node;

            final String nodeName = node.getNodeName();

            if (NODE_CATEGORY.equals(nodeName)) {
                rule.setCategory(parseCategory(element));
            } else if (NODE_TITLE.equals(nodeName)) {
                rule.setTitle(parseTitle(element));
            } else if (NODE_MATCH.equals(nodeName)) {
                rule.setMatch(parseMatch(element));
            } else if (NODE_DESCRIPTION.equals(nodeName)) {
                rule.setDescription(parseDescription(element));
            } else if (NODE_STANDARDS.equals(nodeName)) {

                final List<Standard> standards = parseStandarts(element);

                if (!standards.isEmpty()) {
                    for (final Standard standard : standards) {
                        rule.addStandart(standard);
                    }
                }
            }
        }
    }

    private Category parseCategory(final Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        return CategoryFactory.create(textContent);
    }

    private String parseTitle(final Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        return textContent;
    }

    private String parseDescription(final Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        return textContent;
    }

    private Match parseMatch(final Element parent) {

        QualifiedName qualifiedName = null;
        Method method = null;

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (NODE_METHOD.equals(node.getNodeName())) {
                method = parseMethod((Element) node);
            } else if (NODE_QUALIFIED_NAME.equals(node.getNodeName())) {
                qualifiedName = parseQualifiedName((Element) node);
            }
        }

        if (qualifiedName == null || method == null) {
            throw new IllegalArgumentException("can't create match");
        }

        return MatchFactory.create(qualifiedName, method);
    }

    private Method parseMethod(final Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        final List<String> methodNames = new ArrayList<String>();

        if (textContent.contains("(") && textContent.contains(")")) {

            final int beginIndex = textContent.indexOf('(');
            final int endIndex = textContent.indexOf(')');

            textContent = textContent.substring(beginIndex + 1, endIndex);
        }

        for (final String name : textContent.split("[|]")) {
            methodNames.add(name);
        }

        return MethodFactory.create(methodNames);
    }

    private QualifiedName parseQualifiedName(final Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        final List<String> classNames = Arrays.asList(textContent.split("[|]"));

        return QualifiedNameFactory.create(classNames);
    }

    private List<Standard> parseStandarts(final Element parent) {

        final List<Standard> result = new ArrayList<Standard>();

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_STANDARD.equals(node.getNodeName())) {
                continue;
            }

            final Element element = (Element) node;

            final String file = element.getAttribute(ATTR_FILE);
            final Context context = parseContext(element);

            final StandardService standartService = ServiceManager.getService(StandardService.class);
            final Standard standard = standartService.getStandardForFile(file);

            if (standard == null) {
                throw new RuntimeException("not found standard for file " + file);
            }

            result.add(standard);
        }

        return result;
    }

    private Context parseContext(final Element parent) {

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_CONTEXT.equals(node.getNodeName())) {
                continue;
            }

            final Element element = (Element) node;

            String textContent = element.getTextContent();
            textContent = textContent.trim();

            return ContextFactory.create(textContent);
        }

        throw new IllegalArgumentException("not found context element for " + parent);
    }
}
