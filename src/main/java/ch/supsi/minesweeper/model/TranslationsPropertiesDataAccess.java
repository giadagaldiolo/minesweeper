package ch.supsi.minesweeper.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import static java.util.ResourceBundle.Control.FORMAT_DEFAULT;

public class TranslationsPropertiesDataAccess implements TranslationsDataAccessInterface {
    private static TranslationsPropertiesDataAccess instance;
    private static final String BUNDLE_PATH = "i18n.labels";
    private static final String SUPPORTED_LANGUAGES_FILE = "/supported-languages.properties";


    public static TranslationsPropertiesDataAccess getInstance() {
        if (instance == null) {
            instance = new TranslationsPropertiesDataAccess();
        }
        return instance;
    }

    private Properties loadSupportedLanguageTags() {
        Properties supportedLanguageTags = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(SUPPORTED_LANGUAGES_FILE);
            supportedLanguageTags.load(supportedLanguageTagsStream);
        } catch (IOException ignored) {
            ;
        }
        // return the properties object with the loaded preferences
        return supportedLanguageTags;
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        ArrayList<String> supportedLanguageTags = new ArrayList<>();
        Properties props = this.loadSupportedLanguageTags();
        for (Object key: props.keySet()) {
            supportedLanguageTags.add(props.getProperty((String)key));
        }
        // return
        return supportedLanguageTags;
    }

    @Override
    public Properties getTranslations(Locale locale) {
        final Properties translations = new Properties();
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle(
                    BUNDLE_PATH,
                    locale,
                    ResourceBundle.Control.getNoFallbackControl(FORMAT_DEFAULT)
            );
        } catch (MissingResourceException mrex) {
            System.err.println("unsupported language tag..." + locale.toLanguageTag());
            List<String> supportedLanguageTags = this.getSupportedLanguageTags();
            String fallbackLanguageTag = supportedLanguageTags.get(0);
            System.err.println("falling back to..." + fallbackLanguageTag);
            bundle = ResourceBundle.getBundle(
                    BUNDLE_PATH,
                    Locale.forLanguageTag(fallbackLanguageTag),
                    ResourceBundle.Control.getNoFallbackControl(FORMAT_DEFAULT)
            );
        }
        for (String key : bundle.keySet()) {
            translations.put(key, bundle.getString(key));
        }
        return translations;
    }
}
