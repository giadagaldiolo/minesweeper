package ch.supsi.minesweeper.frontend.model;

import java.util.Properties;

public class PropertiesModel implements PropertiesProvider {
    private static PropertiesModel instance;
    private final PropertiesReader reader;
    private final Properties properties;

    private PropertiesModel() {
        this.reader = PropertiesFileReader.getInstance();
        this.properties = reader.read();
    }

    public static PropertiesModel getInstance() {
        if (instance == null){
            instance = new PropertiesModel();
        }
        return instance;
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}