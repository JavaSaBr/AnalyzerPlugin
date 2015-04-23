package com.instinctools.analyzer.service.impl;

import com.instinctools.analyzer.builder.BuilderFactory;
import com.instinctools.analyzer.builder.StandardBuilder;
import com.instinctools.analyzer.builder.xml.impl.XmlURLStandardSource;
import com.instinctools.analyzer.model.standard.Standard;
import com.instinctools.analyzer.service.StandardService;

import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronn on 15.04.15.
 * Documentation follows here.
 */
public class StandardServiceImpl implements StandardService {

    private static final String[] STANDARD_PATHS = {"/standards/about-sql-injection.xml"
    };

    private final Map<String, Standard> fileToStandard;

    public StandardServiceImpl() {
        this.fileToStandard = new HashMap<String, Standard>();
        init();
    }

    private void init() {

        for (final String standartPath : STANDARD_PATHS) {

            final URL resource = getClass().getResource(standartPath);

            if (resource == null) {
                continue;
            }

            try {

                final XmlURLStandardSource source = new XmlURLStandardSource(resource);

                final StandardBuilder builder = BuilderFactory.getStandardBuilderFor(source);
                final Standard standard = builder.build(source);

                final String path = URLDecoder.decode(resource.getPath(), "UTF-8");

                System.out.println("path " + path);

                if (path.contains("/")) {

                    System.out.println("name " + path.substring(path.lastIndexOf('/'), path.length()));

                    fileToStandard.put(path.substring(path.lastIndexOf('/') + 1, path.length()), standard);
                }

            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Standard getStandardForFile(final String filePath) {
        return fileToStandard.get(filePath);
    }
}
