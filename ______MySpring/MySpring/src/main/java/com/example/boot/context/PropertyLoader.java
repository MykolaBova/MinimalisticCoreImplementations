package com.example.boot.context;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private final Properties props = new Properties();

    public PropertyLoader(String resource) {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resource)) {
            if (in != null) {
                props.load(in);
            } else {
                System.err.println("Resource not found: " + resource);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from " + resource, e);
        }
    }

    public String get(String key, String defaultVal) {
        return props.getProperty(key, defaultVal);
    }

    public boolean contains(String key) {
        return props.containsKey(key);
    }
}

