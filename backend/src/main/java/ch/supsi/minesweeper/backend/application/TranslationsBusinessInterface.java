package ch.supsi.minesweeper.backend.application;

public interface TranslationsBusinessInterface {

    boolean isSupportedLanguageTag(String languageTag);
    // Cambia la lingua corrente
    boolean changeLanguage(String languageTag);

    // Traduce una chiave in base alla lingua corrente
    String translate(String key);
}