package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.translations.TranslationsLogic;

public class TranslationsApplication implements TranslationsApplicationInterface{
    private static TranslationsApplication myself;
    private final TranslationsLogic translationsLogic = TranslationsLogic.getInstance();

    public static TranslationsApplication getInstance() {
        if (myself == null) {
            myself = new TranslationsApplication();
        }
        return myself;
    }

    @Override
    public String translate(String key) {
        return translationsLogic.translate(key);
    }

    @Override
    public boolean changeLanguage(String languageTag) {
        return translationsLogic.changeLanguage(languageTag);
    }

    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        return translationsLogic.isSupportedLanguageTag(languageTag);
    }
}
