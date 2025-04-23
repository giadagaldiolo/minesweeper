package ch.supsi.minesweeper.model;

public interface PropertiesProvider {
    String getUserPreferencesFilePath();
    String getProperty(String key, String defaultValue);
}
