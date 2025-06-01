package ch.supsi.minesweeper.backend.business.translations;

public interface TranslationsLogicInterface {
    String translate(String key);
    boolean changeLanguage(String languageTag);
    boolean isSupportedLanguageTag(String languageTag);

}
