package ch.supsi.minesweeper.backend.business.properties;

import ch.supsi.minesweeper.backend.application.PropertiesBusinessInterface;
import ch.supsi.minesweeper.backend.data_access.PropertiesDataAccess;


import java.util.Properties;

public class PropertiesModel implements PropertiesBusinessInterface {
    private static PropertiesModel myself;
    private final PropertiesDataAccessInterface propertiesDao;
    private final Properties userProperties;

    private PropertiesModel() {
        this.propertiesDao = PropertiesDataAccess.getMyself();
        this.userProperties = propertiesDao.getProperties();
    }

    public static PropertiesModel getInstance() {
        if (myself == null){
            myself = new PropertiesModel();
        }
        return myself;
    }

    @Override
    public String getProperty(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        if (userProperties == null) {
            return null;
        }
        return userProperties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        userProperties.setProperty(key, value);
    }
}