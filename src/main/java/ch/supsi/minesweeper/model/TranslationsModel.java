package ch.supsi.minesweeper.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TranslationsModel extends AbstractModel implements TranslationsInterface {
    private static TranslationsModel instance;
    private Properties translations;
    private Locale currentLocale;

    private static final String BUNDLE_PATH = "i18n.labels";
    private static final String SUPPORTED_LANGUAGES_FILE = "/supported-languages.properties";


    private TranslationsModel() {
        this.currentLocale = Locale.getDefault();
        this.translations = loadTranslations(currentLocale);
    }

    public static TranslationsModel getInstance() {
        if (instance == null) {
            instance = new TranslationsModel();
        }
        return instance;
    }


    @Override
    public void changeLanguage(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        this.getTranslations(locale);
    }

    @Override
    public String translate(String key) {
        return translations.getProperty(key, key); // Ritorna la chiave se la traduzione non è trovata
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        return loadSupportedLanguageTags();
    }

    @Override
    public Properties getTranslations(Locale locale) {
        if (!locale.equals(currentLocale)) {
            this.currentLocale = locale;
            this.translations = loadTranslations(locale);
        }
        return translations;
    }



    private Properties loadTranslations(Locale locale) {
        Properties translations = new Properties();
        ResourceBundle bundle;

        try {
            bundle = ResourceBundle.getBundle(
                    BUNDLE_PATH,
                    locale,
                    ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_DEFAULT)
            );
        } catch (MissingResourceException mrex) {
            System.err.println("Lingua non supportata..." + locale.toLanguageTag());
            List<String> supportedLanguageTags = loadSupportedLanguageTags();
            String fallbackLanguageTag = supportedLanguageTags.get(0);
            System.err.println("Ripristino a..." + fallbackLanguageTag);
            bundle = ResourceBundle.getBundle(
                    BUNDLE_PATH,
                    Locale.forLanguageTag(fallbackLanguageTag),
                    ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_DEFAULT)
            );
        }

        for (String key : bundle.keySet()) {
            translations.put(key, bundle.getString(key));
        }

        return translations;
    }

    private List<String> loadSupportedLanguageTags() {
        Properties supportedLanguageTags = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(SUPPORTED_LANGUAGES_FILE);
            supportedLanguageTags.load(supportedLanguageTagsStream);
        } catch (IOException ignored) {
        }

        List<String> languageTags = new ArrayList<>();
        for (Object key : supportedLanguageTags.keySet()) {
            languageTags.add(supportedLanguageTags.getProperty((String) key));
        }
        return languageTags;
    }
}

