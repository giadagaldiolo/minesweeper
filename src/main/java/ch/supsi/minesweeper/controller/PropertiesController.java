package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesProvider;

public class PropertiesController {
    private final PropertiesProvider provider;

    public PropertiesController(PropertiesService provider) {
        this.provider = provider;
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
