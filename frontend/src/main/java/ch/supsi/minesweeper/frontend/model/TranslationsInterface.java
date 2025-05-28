package ch.supsi.minesweeper.frontend.model;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

public interface TranslationsInterface {
    // Restituisce la lista dei linguaggi supportati
    List<String> getSupportedLanguageTags();

    // Restituisce le traduzioni per una lingua specifica
    Properties getTranslations(Locale locale);

    // Cambia la lingua corrente
    void changeLanguage(String languageTag);

    // Traduce una chiave in base alla lingua corrente
    String translate(String key);
}