package ch.supsi.minesweeper.frontend.controller;

public interface PropertiesBusinessInterface {
    String getProperty(String key);

    String getDefaultProperty(String key);

    void setProperty(String key, String value);

    void savePropertyToFile(String key, String value);

}