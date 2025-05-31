package ch.supsi.minesweeper.backend.application;

public interface PropertiesApplicationInterface {
    String getProperty(String key);
    void setProperty(String key, String value);
    String getDefaultProperty(String key);
    void savePropertyToFile(String key, String value);
}
