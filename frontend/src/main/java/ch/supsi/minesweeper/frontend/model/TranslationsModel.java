package ch.supsi.minesweeper.frontend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TranslationsModel extends AbstractModel implements TranslationsInterface {
    // Istanza singleton della classe
    private static TranslationsModel instance;

    // Proprietà contenente le traduzioni correnti
    private Properties translations;

    // Locale corrente dell'applicazione
    private Locale currentLocale;

    // Percorso del bundle di traduzioni (base name)
    private static final String BUNDLE_PATH = "i18n.labels";

    // Percorso del file con le lingue supportate
    private static final String SUPPORTED_LANGUAGES_FILE = "/supported-languages.properties";

    private TranslationsModel() {
        // Imposta il locale corrente come quello di default del sistema
        this.currentLocale = Locale.getDefault();
        // Carica le traduzioni per il locale corrente
        this.translations = loadTranslations(currentLocale);
    }

    // Ritorna il Singelton
    public static TranslationsModel getInstance() {
        if (instance == null) {
            instance = new TranslationsModel();
        }
        return instance;
    }

    // Cambia la lingua dell'applicazione
    @Override
    public void changeLanguage(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        this.getTranslations(locale);
    }

    // Restituisce la traduzione associata a una chiave, o la chiave stessa se mancante
    @Override
    public String translate(String key) {
        return translations.getProperty(key, key);
    }

    @Override
    public List<String> getSupportedLanguageTags() {
        Properties supportedLanguageTags = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(SUPPORTED_LANGUAGES_FILE);
            supportedLanguageTags.load(supportedLanguageTagsStream);
        } catch (IOException ignored) {} // Ignora eventuali errori di caricamento

        List<String> languageTags = new ArrayList<>();
        for (Object key : supportedLanguageTags.keySet()) {
            languageTags.add(supportedLanguageTags.getProperty((String) key));
        }
        return languageTags;
    }

    // Restituisce le traduzioni per un dato locale
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
}