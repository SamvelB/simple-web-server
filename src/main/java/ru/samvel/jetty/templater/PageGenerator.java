package ru.samvel.jetty.templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


public class PageGenerator {
    private static final String HTML_DIR = "src/main/templates";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    public String getBayPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(data, stream);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

/*    public String getPageJson(String filename, String gsonString) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(gsonString, stream);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }*/

    public String getPage(String filename) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(null, stream);
        }
        catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration();
    }
}
