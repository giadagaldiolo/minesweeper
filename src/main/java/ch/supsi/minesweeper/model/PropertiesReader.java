package ch.supsi.minesweeper.model;

import java.util.Properties;

public interface PropertiesReader {
    Properties read();

    String getUserPreferencesFilePath();
}