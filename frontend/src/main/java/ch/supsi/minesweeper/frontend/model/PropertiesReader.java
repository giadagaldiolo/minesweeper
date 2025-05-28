package ch.supsi.minesweeper.frontend.model;

import java.util.Properties;

public interface PropertiesReader {
    Properties read();

    String getUserPreferencesFilePath();
}