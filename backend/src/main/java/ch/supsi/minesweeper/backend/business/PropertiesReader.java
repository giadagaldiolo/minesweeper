package ch.supsi.minesweeper.backend.business;

import java.util.Properties;

public interface PropertiesReader {
    Properties read();

    String getUserPreferencesFilePath();
}