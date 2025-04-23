package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesProvider;
import ch.supsi.minesweeper.model.PropertiesReader;

import java.util.Properties;

public class PropertiesService implements PropertiesProvider {
    private final PropertiesReader reader;
    private final Properties properties;

    public PropertiesService(PropertiesReader reader) {
        this.reader = reader;
        this.properties = reader.read();
    }

    @Override
    public String getUserPreferencesFilePath() {
        return reader.getUserPreferencesFilePath();
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

}
