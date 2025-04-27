package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesModel;
import ch.supsi.minesweeper.model.PropertiesProvider;

public class PropertiesController implements PropertiesProvider {
    private static PropertiesController instance;

    private final PropertiesProvider provider;

    private PropertiesController() {
        this.provider = PropertiesModel.getInstance();
    }

    public static PropertiesController getInstance() {
        if (instance == null) {
            instance = new PropertiesController();
        }
        return instance;
    }



    @Override
    public String getProperty(String key) {
        return provider.getProperty(key);
    }

}
