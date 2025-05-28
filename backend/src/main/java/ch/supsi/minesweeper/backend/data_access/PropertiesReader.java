package ch.supsi.minesweeper.backend.data_access;

import java.util.Properties;

public interface PropertiesReader {
    Properties read();

    String getUserPreferencesFilePath();
}