package ch.supsi.minesweeper.backend.business.translations;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import ch.supsi.minesweeper.backend.application.TranslationsBusinessInterface;
import ch.supsi.minesweeper.backend.data_access.TranslationsPropertiesDataAccess;

public class TranslationsModel implements TranslationsBusinessInterface {
    private static TranslationsModel myself;
    private final TranslationsDataAccesInterface translationsDao;
    private final List<String> supportedLanguageTags;
    private Properties translations;


    protected TranslationsModel() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }


    public static TranslationsModel getInstance() {
        if (myself == null) {
            myself = new TranslationsModel();
        }
        return myself;
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
        return this.translations.getProperty(key);
    }

}
