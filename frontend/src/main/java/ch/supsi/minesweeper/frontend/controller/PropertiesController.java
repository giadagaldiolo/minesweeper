package ch.supsi.minesweeper.frontend.controller;

import ch.supsi.minesweeper.frontend.model.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.PropertiesProvider;

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