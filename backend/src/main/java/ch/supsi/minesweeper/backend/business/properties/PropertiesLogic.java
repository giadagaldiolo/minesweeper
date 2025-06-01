package ch.supsi.minesweeper.backend.business.properties;

import ch.supsi.minesweeper.backend.data_access.PropertiesDataAccess;
import ch.supsi.minesweeper.backend.data_access.PropertiesDataAccessInterface;

import java.util.Properties;

public class PropertiesLogic implements IPropertiesLogic {
    private static PropertiesLogic myself;
    private final PropertiesDataAccessInterface propertiesDao;
    private final Properties userProperties;
    private final Properties defaultUserProperties;

    private PropertiesLogic() {
        this.propertiesDao = PropertiesDataAccess.getInstance();
        this.userProperties = propertiesDao.getProperties();
        this.defaultUserProperties = propertiesDao.getDefaultProperties();
    }

    public static PropertiesLogic getInstance() {
        if (myself == null) {
            myself = new PropertiesLogic();
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

    @Override
    public void setProperty(String key, String value) {
        userProperties.setProperty(key, value);
    }

    @Override
    public String getDefaultProperty(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        if (defaultUserProperties == null) {
            return null;
        }
        return defaultUserProperties.getProperty(key);
    }

    @Override
    public void savePropertyToFile(String key, String value) {
        propertiesDao.savePropertyToFile(key, value);
    }
}
