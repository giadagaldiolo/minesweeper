package ch.supsi.minesweeper.model;

import java.util.Locale;
import java.util.*;

public interface TranslationsDataAccessInterface {
    List<String> getSupportedLanguageTags();
    Properties getTranslations(Locale locale);
}

