package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.properties.PropertiesLogic;

public class PropertiesApplication implements PropertiesApplicationInterface {
    private static PropertiesApplication myself;
    private PropertiesLogic propertiesLogic = PropertiesLogic.getInstance();

    public static PropertiesApplication getInstance() {
        if (myself == null) {
            myself = new PropertiesApplication();
        }
        return myself;
    }

    @Override
    public String getProperty(String key) {
        return propertiesLogic.getProperty(key);
    }

    @Override
    public void setProperty(String key, String value) {
        propertiesLogic.setProperty(key, value);
    }

    @Override
    public String getDefaultProperty(String key) {
        return propertiesLogic.getDefaultProperty(key);
    }

    @Override
    public void savePropertyToFile(String key, String value) {
        propertiesLogic.savePropertyToFile(key,value);
    }
}
