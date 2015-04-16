package com.instonctools.analyzer.service.impl;

import com.instonctools.analyzer.builder.BuilderFactory;
import com.instonctools.analyzer.builder.StandardBuilder;
import com.instonctools.analyzer.builder.xml.impl.XmlURLStandardSource;
import com.instonctools.analyzer.model.standard.Standard;
import com.instonctools.analyzer.service.StandardService;

import java.net.URL;
import java.net.URLDecoder;
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

                XmlURLStandardSource source = new XmlURLStandardSource(resource);

                StandardBuilder builder = BuilderFactory.getStandardBuilderFor(source);
                Standard standard = builder.build(source);


                String path = URLDecoder.decode(resource.getPath(), "UTF-8");

                System.out.println("path " + path);

                if (path.contains("/")) {

                    System.out.println("name " + path.substring(path.lastIndexOf('/'), path.length()));

                    fileToStandard.put(path.substring(path.lastIndexOf('/') + 1, path.length()), standard);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Standard getStandardForFile(String filePath) {
        return fileToStandard.get(filePath);
    }
}
