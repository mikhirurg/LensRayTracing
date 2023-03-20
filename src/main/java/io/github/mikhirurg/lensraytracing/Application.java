package io.github.mikhirurg.lensraytracing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Application {
    private static final Properties appProperties;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("app.properties"));
        } catch (IOException e) {
            try {
                properties.load(LensRayTracingDemo.class.getClassLoader().getResourceAsStream("app.properties"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        appProperties = properties;
    }

    public static String getProperty(String key) {
        return appProperties.getProperty(key);
    }

}
