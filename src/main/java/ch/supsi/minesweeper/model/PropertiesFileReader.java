package ch.supsi.minesweeper.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesFileReader implements PropertiesReader {
    private static PropertiesFileReader instance;
    private final String defaultPropertiesPath = "/default.properties";
    private final String userHomeDirectory = System.getProperty("user.home");
    private final String propertiesDirectory = ".minesweeper";
    private final String propertiesFile = "user.properties";
    private Properties userProperties;


    public static PropertiesFileReader getInstance() {
        if (instance == null) {
            instance = new PropertiesFileReader();
        }
        return instance;
    }

    private Path getUserPropertiesDirectoryPath() {
        return Path.of(userHomeDirectory, propertiesDirectory);
    }

    private boolean userPropertiesDirectoryExists() {
        return Files.exists(this.getUserPropertiesDirectoryPath());
    }

    private boolean userPropertiesFileExists() {
        return Files.exists(this.getUserPropertiesFilePath());
    }

    private Path getUserPropertiesFilePath() {
        return Path.of(userHomeDirectory, propertiesDirectory, propertiesFile);
    }

    private Path createUserPropertiesDirectory() {
        try {
            return Files.createDirectories(this.getUserPropertiesDirectoryPath());

        } catch (IOException ignoredForDemoPurposes) {
            ignoredForDemoPurposes.printStackTrace();
        }

        return null;
    }

    private Properties loadDefaultProperties() {
        Properties defaultProperties = new Properties();
        try {
            InputStream defaultPropertiesStream = this.getClass().getResourceAsStream(defaultPropertiesPath);
            defaultProperties.load(defaultPropertiesStream);

        } catch (IOException ignored) {
            ;
        }
        return defaultProperties;
    }

    private Properties loadProperties(Path path) {
        Properties preferences = new Properties();
        try {
            preferences.load(new FileInputStream(String.valueOf(path)));

        } catch (IOException ignoredForDemoPurposes) {
            return null;
        }
        return preferences;
    }



    private boolean createUserPropertiesFile(Properties defaultProperties) {
        if (defaultProperties == null) {
            return false;
        }

        if (!userPropertiesDirectoryExists()) {
            this.createUserPropertiesDirectory();
        }

        if (!userPropertiesFileExists()) {
            try {
                FileOutputStream outputStream = new FileOutputStream(String.valueOf(this.getUserPropertiesFilePath()));
                defaultProperties.store(outputStream, null);
                return true;
            } catch (IOException ignoredForDemoPurposes) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getUserPreferencesFilePath() {
        return getUserPropertiesFilePath().toString();
    }

    @Override
    public Properties read() {
        if (userProperties != null) {
            return userProperties;
        }

        if (userPropertiesFileExists()) {
            userProperties = loadProperties(getUserPropertiesFilePath());
            return userProperties;
        }
        userProperties = loadDefaultProperties();
        createUserPropertiesFile(userProperties);
        return userProperties;


    }
}
