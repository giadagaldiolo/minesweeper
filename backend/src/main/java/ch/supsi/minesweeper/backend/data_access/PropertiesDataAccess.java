package ch.supsi.minesweeper.backend.data_access;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesDataAccess implements PropertiesDataAccessInterface {
    private static PropertiesDataAccess myself;
    private final String defaultPropertiesPath = "/default.properties";
    private final String userHomeDirectory = System.getProperty("user.home");
    private final String propertiesDirectory = ".minesweeper";
    private final String propertiesFile = "user.properties";
    private Properties userProperties;
    private Properties deafultUserProperties;

    public static PropertiesDataAccess getInstance() {
        if (myself == null) {
            myself = new PropertiesDataAccess();
        }
        return myself;
    }

    private Path getUserPropertiesDirectoryPath() {
        return Path.of(userHomeDirectory, propertiesDirectory);
    }

    private boolean userPropertiesDirectoryExists() {
        return Files.exists(this.getUserPropertiesDirectoryPath());
    }

    private boolean userPropertiesFileExists() {
        return Files.exists(this.getUserPropertiesFilePath());
    }

    private Path getUserPropertiesFilePath() {
        return Path.of(userHomeDirectory, propertiesDirectory, propertiesFile);
    }

    private Path createUserPropertiesDirectory() {
        try {
            return Files.createDirectories(this.getUserPropertiesDirectoryPath());
        } catch (IOException e) {
            System.err.println("Errore nella creazione della directory delle proprietà utente: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private Properties loadDefaultProperties() {
        Properties defaultProperties = new Properties();
        try (InputStream defaultPropertiesStream = this.getClass().getResourceAsStream(defaultPropertiesPath)) {
            if (defaultPropertiesStream != null) {
                defaultProperties.load(defaultPropertiesStream);
            } else {
                System.err.println("File default.properties non trovato nel classpath.");
            }
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle proprietà di default: " + e.getMessage());
            e.printStackTrace();
        }
        return defaultProperties;
    }

    private Properties loadProperties(Path path) {
        Properties preferences = new Properties();
        try (FileInputStream inputStream = new FileInputStream(path.toFile())) {
            preferences.load(inputStream);
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle proprietà da file: " + path);
            e.printStackTrace();
            return null;
        }
        return preferences;
    }

    private boolean createUserPropertiesFile(Properties defaultProperties) {
        if (defaultProperties == null) {
            System.err.println("Proprietà di default nulle, impossibile creare il file utente.");
            return false;
        }

        if (!userPropertiesDirectoryExists()) {
            createUserPropertiesDirectory();
        }

        if (!userPropertiesFileExists()) {
            try (FileOutputStream outputStream = new FileOutputStream(this.getUserPropertiesFilePath().toFile())) {
                defaultProperties.store(outputStream, null);
                return true;
            } catch (IOException e) {
                System.err.println("Errore nella creazione del file di proprietà dell’utente: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public Properties getProperties() {
        if (userProperties != null) {
            return userProperties;
        }

        if (userPropertiesFileExists()) {
            userProperties = loadProperties(getUserPropertiesFilePath());
            return userProperties;
        }

        userProperties = loadDefaultProperties();
        createUserPropertiesFile(userProperties);
        return userProperties;
    }

    @Override
    public Properties getDefaultProperties() {
        if (deafultUserProperties != null) {
            return deafultUserProperties;
        }

        deafultUserProperties = loadDefaultProperties();
        createUserPropertiesFile(deafultUserProperties);
        return deafultUserProperties;
    }

    @Override
    public void savePropertyToFile(String key, String value) {
        Properties props = new Properties();

        // Carica le proprietà esistenti
        try (FileInputStream in = new FileInputStream(getUserPropertiesFilePath().toFile())) {
            props.load(in);
        } catch (IOException e) {
            System.err.println("Errore nel caricamento delle preferenze esistenti da file: " + e.getMessage());
            e.printStackTrace();
        }

        // Aggiunge o aggiorna la proprietà
        props.setProperty(key, value);

        // Salva tutte le proprietà
        try (FileOutputStream out = new FileOutputStream(getUserPropertiesFilePath().toFile())) {
            props.store(out, "User preferences");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio delle preferenze: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
