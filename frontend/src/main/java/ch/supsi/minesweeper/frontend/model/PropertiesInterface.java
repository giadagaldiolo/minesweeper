package ch.supsi.minesweeper.frontend.model;

public interface PropertiesInterface {
    String getProperty(String key);

    String getDefaultProperty(String key);

    void setProperty(String key, String value);

    void savePropertyToFile(String key, String value);

}