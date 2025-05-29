package ch.supsi.minesweeper.frontend.controller;


import ch.supsi.minesweeper.backend.application.PropertiesBusinessInterface;
import ch.supsi.minesweeper.backend.application.TranslationsBusinessInterface;
import ch.supsi.minesweeper.backend.business.properties.PropertiesModel;
import ch.supsi.minesweeper.backend.business.translations.TranslationsModel;

public class TranslationsController {
    private static TranslationsController myself;
    private final TranslationsBusinessInterface translationsModel;
    private final PropertiesBusinessInterface propertiesModel;


    private TranslationsController() {
        this.translationsModel = TranslationsModel.getInstance();
        this.propertiesModel = PropertiesModel.getInstance();

        String currentLanguage = propertiesModel.getProperty("language");
        if (currentLanguage != null) {
            this.translationsModel.changeLanguage(currentLanguage);
        }
    }

    public static TranslationsController getInstance() {
        if (myself == null) {
            myself = new TranslationsController();
        }
        return myself;
    }

    public String translate(String key) {
        return this.translationsModel.translate(key);
    }


}
