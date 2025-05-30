package ch.supsi.minesweeper.backend.business.properties;

import java.util.Properties;

public interface PropertiesDataAccessInterface {
    Properties getProperties();
    Properties getDefaultProperties();

    void savePropertyToFile(String key, String value);
}