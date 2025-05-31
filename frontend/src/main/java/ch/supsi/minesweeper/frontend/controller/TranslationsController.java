package ch.supsi.minesweeper.frontend.controller;


import ch.supsi.minesweeper.frontend.model.PropertiesInterface;
import ch.supsi.minesweeper.frontend.model.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.TranslationsInterface;
import ch.supsi.minesweeper.frontend.model.TranslationsModel;

public class TranslationsController {
    private static TranslationsController myself;
    private final TranslationsInterface translationsModel;
    private final PropertiesInterface propertiesModel;


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
