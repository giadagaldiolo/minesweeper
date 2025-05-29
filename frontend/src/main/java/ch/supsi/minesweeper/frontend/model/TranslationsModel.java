package ch.supsi.minesweeper.frontend.model;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import ch.supsi.minesweeper.backend.application.TranslationsApplication;
import ch.supsi.minesweeper.frontend.controller.TranslationsBusinessInterface;
import ch.supsi.minesweeper.backend.business.translations.TranslationsDataAccesInterface;
import ch.supsi.minesweeper.backend.data_access.TranslationsPropertiesDataAccess;

public class TranslationsModel implements TranslationsBusinessInterface {
    private static TranslationsModel myself;
    private static final TranslationsApplication application = TranslationsApplication.getInstance();


    protected TranslationsModel() {}


    public static TranslationsModel getInstance() {
        if (myself == null) {
            myself = new TranslationsModel();
        }
        return myself;
    }


    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        return application.isSupportedLanguageTag(languageTag);
    }

    @Override
    public boolean changeLanguage(String languageTag) {
        return application.changeLanguage(languageTag);
    }


    @Override
    public String translate(String key) {
        return application.translate(key);
    }

}
