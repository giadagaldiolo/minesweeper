package ch.supsi.minesweeper.frontend.controller;


import ch.supsi.minesweeper.backend.business.PropertiesModel;
import ch.supsi.minesweeper.backend.data_access.PropertiesProvider;

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