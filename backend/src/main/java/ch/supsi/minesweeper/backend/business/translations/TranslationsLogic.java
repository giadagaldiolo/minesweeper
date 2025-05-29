package ch.supsi.minesweeper.backend.business.translations;

import ch.supsi.minesweeper.backend.data_access.TranslationsPropertiesDataAccess;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class TranslationsLogic{
    private static TranslationsLogic myself;
    private final List<String> supportedLanguageTags;
    private Properties translations;
    private final TranslationsDataAccesInterface translationsDao;

    private TranslationsLogic() {
        this.translationsDao = TranslationsPropertiesDataAccess.getInstance();
        this.supportedLanguageTags = translationsDao.getSupportedLanguageTags();
    }

    public static TranslationsLogic getInstance() {
        if (myself == null) {
            myself = new TranslationsLogic();
        }
        return myself;
    }

    public String translate(String key) {
        return translations.getProperty(key);
    }

    public boolean changeLanguage(String languageTag) {
        this.translations = translationsDao.getTranslations(Locale.forLanguageTag(languageTag));
        return this.translations != null;
    }

    public boolean isSupportedLanguageTag(String languageTag) {
        if (!this.supportedLanguageTags.contains(languageTag)) {
            return false;
        }
        return true;
    }
}
