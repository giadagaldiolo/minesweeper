package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesProvider;
import ch.supsi.minesweeper.model.PropertiesService;
import ch.supsi.minesweeper.model.TranslationsBusinessInterface;
import ch.supsi.minesweeper.model.TranslationsModel;

public class TranslationsController {
    private static TranslationsController instance;
    private final TranslationsBusinessInterface model;
    private final PropertiesProvider preferences;

    private TranslationsController() {
        this.model = TranslationsModel.getInstance();
        this.preferences = PropertiesService.getInstance();
        String currentLanguage = preferences.getCurrentLanguage();
        this.model.changeLanguage(currentLanguage);
    }

    public static TranslationsController getInstance() {
        if (instance == null) {
            instance = new TranslationsController();
        }
        return instance;
    }

    public String translate(String key) {
        return model.translate(key);
    }

}
