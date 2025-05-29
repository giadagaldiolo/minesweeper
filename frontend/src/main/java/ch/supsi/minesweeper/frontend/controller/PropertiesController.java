package ch.supsi.minesweeper.frontend.controller;


import ch.supsi.minesweeper.backend.application.PropertiesBusinessInterface;
import ch.supsi.minesweeper.backend.business.properties.PropertiesModel;

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