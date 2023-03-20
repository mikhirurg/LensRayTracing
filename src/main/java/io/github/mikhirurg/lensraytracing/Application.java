package io.github.mikhirurg.lensraytracing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Application {
    private static final Properties appProperties;

    public enum Os {
        WIN, MAC, LINUX, OTHER
    }

    private static Os currentOs;

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

    public static Os getOS() {
        if (currentOs == null) {
            String osStr = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((osStr.contains("mac")) || (osStr.contains("darwin"))) {
                currentOs = Os.MAC;
            } else if (osStr.contains("win")) {
                currentOs = Os.WIN;
            } else if (osStr.contains("nux")) {
                currentOs = Os.LINUX;
            } else {
                currentOs = Os.OTHER;
            }
        }
        return currentOs;
    }

}
