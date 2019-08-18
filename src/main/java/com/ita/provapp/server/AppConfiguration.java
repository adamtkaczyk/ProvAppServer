package com.ita.provapp.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfiguration {
    private static AppConfiguration instance = null;

    private Properties properties = new Properties();

    private AppConfiguration() {
        try {
            InputStream input = new FileInputStream("application_test.properties");
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public static AppConfiguration getInstance() {
        if (instance == null)
            instance = new AppConfiguration();

        return instance;
    }
}
