package ru.job4j.sqlparse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values;
    private final String path;
    private static final Logger LOG = LogManager.getLogger(Config.class.getName());

    public Config(String path) {
        this.path = path;
        this.values = new Properties();
        this.init();
    }

    private void init() {
        try (InputStream in = new FileInputStream(path)) {
            values.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
