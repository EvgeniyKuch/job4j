package ru.job4j.magnet;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties values;

    public Config() {
        this.values = new Properties();
        this.init();
    }

    private void init() {
        try (InputStream in =
                     Config.class.getClassLoader()
                             .getResourceAsStream("magnet.properties")
        ) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}
