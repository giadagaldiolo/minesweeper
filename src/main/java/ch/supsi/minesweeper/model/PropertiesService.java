package ch.supsi.minesweeper.model;

import java.util.Properties;

public class PropertiesService implements PropertiesProvider {
    private static PropertiesService instance;
    private final PropertiesReader reader;
    private final Properties properties;

    private PropertiesService() {
        this.reader = PropertiesFileReader.getInstance();
        this.properties = reader.read();
    }

    public static PropertiesService getInstance() {
        if (instance == null){
            return new PropertiesService();
        }
        return instance;
    }

    @Override
    public String getUserPreferencesFilePath() {
        return reader.getUserPreferencesFilePath();
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @Override
    public String getCurrentLanguage() {
        return properties.getProperty("language");
    }

}
