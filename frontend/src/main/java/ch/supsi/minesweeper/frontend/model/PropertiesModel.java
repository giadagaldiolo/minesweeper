package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.PropertiesApplication;
import ch.supsi.minesweeper.frontend.controller.PropertiesBusinessInterface;
import ch.supsi.minesweeper.backend.business.properties.PropertiesDataAccessInterface;
import ch.supsi.minesweeper.backend.data_access.PropertiesDataAccess;


import java.util.Properties;

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

    public void setProperty(String key, String value) {
        application.setProperty(key, value);
    }
}