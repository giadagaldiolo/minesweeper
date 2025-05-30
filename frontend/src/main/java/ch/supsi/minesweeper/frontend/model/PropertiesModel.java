package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.PropertiesApplication;
import ch.supsi.minesweeper.frontend.controller.PropertiesBusinessInterface;

public class PropertiesModel implements PropertiesBusinessInterface {
    private static PropertiesModel myself;
    private static final PropertiesApplication application = PropertiesApplication.getInstance();


    public static PropertiesModel getInstance() {
        if (myself == null){
            myself = new PropertiesModel();
        }
        return myself;
    }

    @Override
    public String getProperty(String key) {
        return application.getProperty(key);
    }

    @Override
    public String getDefaultProperty(String key) {
        return application.getDefaultProperty(key);
    }

    public void setProperty(String key, String value) {
        application.setProperty(key, value);
    }

    public void savePropertyToFile(String key, String value) {
        application.savePropertyToFile(key,value);
    }
}