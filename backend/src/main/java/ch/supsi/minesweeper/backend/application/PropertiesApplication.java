package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.properties.PropertiesLogic;

public class PropertiesApplication {
    private static PropertiesApplication myself;
    private PropertiesLogic propertiesLogic = PropertiesLogic.getInstance();

    public static PropertiesApplication getInstance() {
        if (myself == null) {
            myself = new PropertiesApplication();
        }
        return myself;
    }

    public String getProperty(String key) {
        return propertiesLogic.getProperty(key);
    }

    public void setProperty(String key, String value) {
        propertiesLogic.setProperty(key, value);
    }

    public String getDefaultProperty(String key) {
        return propertiesLogic.getDefaultProperty(key);
    }
}
