package ch.supsi.minesweeper.backend.application;

public interface TranslationsApplicationInterface {
    String translate(String key);
    boolean changeLanguage(String languageTag);
    boolean isSupportedLanguageTag(String languageTag);
}
