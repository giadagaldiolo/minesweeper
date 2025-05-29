package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.translations.TranslationsLogic;

public class TranslationsApplication {
    private static TranslationsApplication myself;
    private TranslationsLogic translationsLogic = TranslationsLogic.getInstance();

    public static TranslationsApplication getInstance() {
        if (myself == null) {
            myself = new TranslationsApplication();
        }
        return myself;
    }


    public String translate(String key) {
        return translationsLogic.translate(key);
    }

    public boolean changeLanguage(String languageTag) {
        return translationsLogic.changeLanguage(languageTag);
    }

    public boolean isSupportedLanguageTag(String languageTag) {
        return translationsLogic.isSupportedLanguageTag(languageTag);
    }
}
