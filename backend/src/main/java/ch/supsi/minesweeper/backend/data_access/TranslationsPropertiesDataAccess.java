package ch.supsi.minesweeper.backend.data_access;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TranslationsPropertiesDataAccess implements TranslationsDataAccessInterface {
    // Istanza singleton della classe
    private static TranslationsPropertiesDataAccess myself;

    // Percorso del bundle di traduzioni (base name)
    private static final String BUNDLE_PATH = "i18n.labels";

    // Percorso del file con le lingue supportate
    private static final String SUPPORTED_LANGUAGES_PATH = "/supported-languages.properties";

    // Ritorna il Singelton
    public static TranslationsPropertiesDataAccess getInstance() {
        if (myself == null) {
            myself = new TranslationsPropertiesDataAccess();
        }
        return myself;
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
        Properties translations = new Properties();
        ResourceBundle bundle;

        try {
            // Carica il bundle di risorse senza fallback
            bundle = ResourceBundle.getBundle(
                    BUNDLE_PATH,
                    locale,
                    ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_DEFAULT)
            );
        } catch (MissingResourceException mrex) {
            System.err.println("Lingua non supportata..." + locale.toLanguageTag());
            List<String> supportedLanguageTags = getSupportedLanguageTags();
            String fallbackLanguageTag = supportedLanguageTags.get(0);
            System.err.println("Ripristino a..." + fallbackLanguageTag);
            bundle = ResourceBundle.getBundle(
                    BUNDLE_PATH,
                    Locale.forLanguageTag(fallbackLanguageTag),
                    ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_DEFAULT)
            );
        }

        // Copia tutte le chiavi e i valori dal bundle nelle properties
        for (String key : bundle.keySet()) {
            translations.put(key, bundle.getString(key));
        }

        return translations;
    }

    private Properties loadSupportedLanguageTags() {
        Properties supportedLanguageTags = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(SUPPORTED_LANGUAGES_PATH);
            supportedLanguageTags.load(supportedLanguageTagsStream);
        } catch (IOException ignored) {
            ;
        }
        // return the properties object with the loaded preferences
        return supportedLanguageTags;
    }
}