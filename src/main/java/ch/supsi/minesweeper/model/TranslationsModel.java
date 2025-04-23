package ch.supsi.minesweeper.model;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsModel implements TranslationsBusinessInterface {
    private static TranslationsModel instance;
    private final TranslationsDataAccessInterface translationsDao;
    private final List<String> supportedLanguageTags;
    private Properties translations;

    private TranslationsModel() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    public static TranslationsModel getInstance() {
        if (instance == null) {
            instance = new TranslationsModel();
        }
        return instance;
    }

    @Override
    public boolean isSupportedLanguageTag(String languageTag) {
        if (!this.supportedLanguageTags.contains(languageTag)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean changeLanguage(String languageTag) {
        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));
        return this.translations != null;
    }

    @Override
    public String translate(String key) {
        return translations.getProperty(key);
    }
}

