package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.TranslationsApplication;

public class TranslationsModel extends AbstractModel implements TranslationsInterface {
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
