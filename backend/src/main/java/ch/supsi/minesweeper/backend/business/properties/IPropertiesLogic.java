package ch.supsi.minesweeper.backend.business.properties;

public interface IPropertiesLogic {
    String getProperty(String key);
    void setProperty(String key, String value);
    String getDefaultProperty(String key);
    void savePropertyToFile(String key, String value);
}
