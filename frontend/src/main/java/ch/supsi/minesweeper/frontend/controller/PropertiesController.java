package ch.supsi.minesweeper.frontend.controller;


import ch.supsi.minesweeper.frontend.model.PropertiesModel;

public class PropertiesController implements PropertiesBusinessInterface {
    private static PropertiesController myself;
    private final PropertiesBusinessInterface propertiesModel;

    private PropertiesController() {
        this.propertiesModel = PropertiesModel.getInstance();
    }

    public static PropertiesController getInstance() {
        if (myself == null) {
            myself = new PropertiesController();
        }
        return myself;
    }

    @Override
    public String getProperty(String key) {
        return propertiesModel.getProperty(key);
    }

    @Override
    public String getDefaultProperty(String key) {
        return propertiesModel.getDefaultProperty(key);
    }

    @Override
    public void setProperty(String key, String value) {
        this.propertiesModel.setProperty(key, value);
    }

    @Override
    public void savePropertyToFile(String key, String value) {
        this.propertiesModel.savePropertyToFile(key, value);
    }
}