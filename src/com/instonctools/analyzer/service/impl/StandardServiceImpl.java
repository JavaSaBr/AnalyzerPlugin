package com.instonctools.analyzer.service.impl;

import com.instonctools.analyzer.builder.BuilderFactory;
import com.instonctools.analyzer.builder.StandardBuilder;
import com.instonctools.analyzer.builder.xml.impl.XmlFileStandardSource;
import com.instonctools.analyzer.model.standard.Standard;
import com.instonctools.analyzer.service.StandardService;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronn on 15.04.15.
 */
public class StandardServiceImpl implements StandardService {

    private static final String[] STANDARD_PATHS = {
            "/standards/about-sql-injection.xml"
    };

    private final Map<String, Standard> fileToStandard;

    public StandardServiceImpl() {
        this.fileToStandard = new HashMap<String, Standard>();
        init();
    }

    private void init() {

        for (String standartPath : STANDARD_PATHS) {

            URL resource = getClass().getResource(standartPath);

            if (resource == null) {
                continue;
            }

            try {

                File file = new File(resource.toURI());
                XmlFileStandardSource fileSource = new XmlFileStandardSource(file);

                StandardBuilder builder = BuilderFactory.getStandardBuilderFor(fileSource);
                Standard standard = builder.build(fileSource);

                fileToStandard.put(file.getName(), standard);

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Standard getStandardForFile(String filePath) {
        return fileToStandard.get(filePath);
    }
}
