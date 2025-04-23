package ch.supsi.minesweeper.model;

public interface TranslationsBusinessInterface {
    boolean isSupportedLanguageTag(String languageTag);
    boolean changeLanguage(String languageTag);
    String translate(String key);
}
