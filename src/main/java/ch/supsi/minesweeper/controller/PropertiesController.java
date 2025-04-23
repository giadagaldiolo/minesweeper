package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesFileReader;
import ch.supsi.minesweeper.model.PropertiesProvider;

public class PropertiesController {
    private static PropertiesController instance;

    private final PropertiesProvider provider;

    private PropertiesController() {
        this.provider = new PropertiesService(new PropertiesFileReader());
    }

    public static PropertiesController getInstance() {
        if (instance == null) {
            instance = new PropertiesController();
        }
        return instance;
    }

    public String getMinesCount() {
        return provider.getProperty("numMines","10");
    }

    public String getLanguage() {
        return provider.getProperty("language","en");
    }

    public String getUserPreferencesFilePath() {
        return provider.getUserPreferencesFilePath();
    }
}
