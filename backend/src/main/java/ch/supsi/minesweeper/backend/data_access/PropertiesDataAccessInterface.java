package ch.supsi.minesweeper.backend.data_access;

import java.util.Properties;

public interface PropertiesDataAccessInterface {
    Properties getProperties();
    Properties getDefaultProperties();

    void savePropertyToFile(String key, String value);
}