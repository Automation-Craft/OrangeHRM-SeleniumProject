package pom.hrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Loads properties file only once (singleton-style)
    public static void initProperties() {
        if (properties == null) {
            properties = new Properties();
            try {
                FileInputStream fis = new FileInputStream("src/test/java/resources/config.properties");
                properties.load(fis);
            } catch (IOException e) {
                System.out.println("❌ Failed to load config.properties file!");
                e.printStackTrace();
            }
        }
    }

    // Returns value for the given key
    public static String getProperty(String key) {
        if (properties == null) {
            initProperties();
        }
        return properties.getProperty(key);
    }

    // Optional: return int property
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    // Optional: return boolean property
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

	
}

