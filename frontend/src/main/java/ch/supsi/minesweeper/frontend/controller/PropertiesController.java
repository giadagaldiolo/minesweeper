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
}