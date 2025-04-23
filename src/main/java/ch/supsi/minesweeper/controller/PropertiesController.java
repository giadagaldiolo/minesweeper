package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesProvider;
import ch.supsi.minesweeper.model.PropertiesService;

public class PropertiesController {
    private static PropertiesController instance;

    private final PropertiesProvider provider;

    private PropertiesController() {
        this.provider = PropertiesService.getInstance();
    }

    public static PropertiesController getInstance() {
        if (instance == null) {
            instance = new PropertiesController();
        }
        return instance;
    }


    public String getPreference(String key) {
        return provider.getProperty(key);
    }

    public String getUserPreferencesFilePath() {
        return provider.getUserPreferencesFilePath();
    }
}
