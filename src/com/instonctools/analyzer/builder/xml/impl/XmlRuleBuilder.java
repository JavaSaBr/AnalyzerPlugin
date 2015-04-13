package com.instonctools.analyzer.builder.xml.impl;

import com.instonctools.analyzer.builder.RuleBuilder;
import com.instonctools.analyzer.builder.RuleSource;
import com.instonctools.analyzer.builder.xml.XmlRuleSource;
import com.instonctools.analyzer.model.category.Category;
import com.instonctools.analyzer.model.category.CategoryFactory;
import com.instonctools.analyzer.model.context.Context;
import com.instonctools.analyzer.model.context.ContextFactory;
import com.instonctools.analyzer.model.lang.Language;
import com.instonctools.analyzer.model.lang.LanguageFactory;
import com.instonctools.analyzer.model.match.Match;
import com.instonctools.analyzer.model.match.MatchFactory;
import com.instonctools.analyzer.model.match.method.Method;
import com.instonctools.analyzer.model.match.method.MethodFactory;
import com.instonctools.analyzer.model.match.qualified.QualifiedName;
import com.instonctools.analyzer.model.match.qualified.QualifiedNameFactory;
import com.instonctools.analyzer.model.rule.MutableRule;
import com.instonctools.analyzer.model.rule.Rule;
import com.instonctools.analyzer.model.rule.RuleFactory;
import com.instonctools.analyzer.model.standart.Standart;
import com.instonctools.analyzer.model.standart.StandartFactory;
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
    public List<Rule> build(RuleSource source) {

        if (!(source instanceof XmlRuleSource)) {
            throw new IllegalArgumentException("this is builder only for " + XmlRuleSource.class.getName());
        }

        Document document = null;

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(((XmlRuleSource) source).getStream());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Rule> result = new ArrayList<Rule>();

        for (Node node = document.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_RULES.equals(node.getNodeName())) {
                continue;
            }

            parseRules((Element) node, result);
        }

        return result;
    }

    private void parseRules(Element parent, List<Rule> container) {

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_RULE.equals(node.getNodeName())) {
                continue;
            }

            Rule rule = parseRule((Element) node);

            if (rule != null) {
                container.add(rule);
            }
        }
    }

    private Rule parseRule(Element parent) {

        String id = parent.getAttribute(ATTR_ID);
        String langName = parent.getAttribute(ATTR_LANG);

        Language language = LanguageFactory.create(langName);

        MutableRule rule = RuleFactory.create();
        rule.setLanguage(language);
        rule.setId(id);

        parseRule(rule, parent);

        return rule;
    }

    private void parseRule(MutableRule rule, Element parent) {

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element element = (Element) node;

            String nodeName = node.getNodeName();

            if (NODE_CATEGORY.equals(nodeName)) {
                rule.setCategory(parseCategory(element));
            } else if (NODE_TITLE.equals(nodeName)) {
                rule.setTitle(parseTitle(element));
            } else if (NODE_MATCH.equals(nodeName)) {
                rule.setMatch(parseMatch(element));
            } else if (NODE_DESCRIPTION.equals(nodeName)) {
                rule.setDescription(parseDescription(element));
            } else if (NODE_STANDARDS.equals(nodeName)) {

                List<Standart> standarts = parseStandarts(element);

                if (!standarts.isEmpty()) {
                    for (Standart standart : standarts) {
                        rule.addStandart(standart);
                    }
                }
            }
        }
    }

    private Category parseCategory(Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        return CategoryFactory.create(textContent);
    }

    private String parseTitle(Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        return textContent;
    }

    private String parseDescription(Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        return textContent;
    }

    private Match parseMatch(Element parent) {

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

    private Method parseMethod(Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        List<String> methodNames = new ArrayList<String>();

        if (textContent.contains("(") && textContent.contains(")")) {

            int beginIndex = textContent.indexOf('(');
            int endIndex = textContent.indexOf(')');

            textContent = textContent.substring(beginIndex + 1, endIndex);
        }

        for (String name : textContent.split("[|]")) {
            methodNames.add(name);
        }

        return MethodFactory.create(methodNames);
    }

    private QualifiedName parseQualifiedName(Element element) {

        String textContent = element.getTextContent();
        textContent = textContent.trim();

        List<String> classNames = Arrays.asList(textContent.split("[|]"));

        return QualifiedNameFactory.create(classNames);
    }

    private List<Standart> parseStandarts(Element parent) {

        List<Standart> result = new ArrayList<Standart>();

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_STANDARD.equals(node.getNodeName())) {
                continue;
            }

            Element element = (Element) node;

            String file = element.getAttribute(ATTR_FILE);
            Context context = parseContext(element);

            result.add(StandartFactory.create(file, context));
        }

        return result;
    }

    private Context parseContext(Element parent) {

        for (Node node = parent.getFirstChild(); node != null; node = node.getNextSibling()) {

            if (node.getNodeType() != Node.ELEMENT_NODE || !NODE_CONTEXT.equals(node.getNodeName())) {
                continue;
            }

            Element element = (Element) node;

            String textContent = element.getTextContent();
            textContent = textContent.trim();

            return ContextFactory.create(textContent);
        }

        throw new IllegalArgumentException("not found context element for " + parent);
    }
}
