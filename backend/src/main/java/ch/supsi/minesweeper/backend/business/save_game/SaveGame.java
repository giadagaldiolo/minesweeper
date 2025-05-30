package ch.supsi.minesweeper.backend.business.save_game;

import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.backend.business.new_game.NewGame;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveGame implements ISaveGame{

    private static SaveGame myself;
    private final String userHomeDirectory = System.getProperty("user.home");
    private final String projectDirectory = ".minesweeper";
    private final String savedDirectory = "saved";
    private String currentFileName = "";

    private SaveGame() {}

    public static SaveGame getInstance() {
        if (myself == null) {
            myself = new SaveGame();
        }
        return myself;
    }

    @Override
    public void save() { //TODO: non sovrascrive un file già salvato, forse il problema è nel nome del file che non viene modificato quando si carica una partita
        try {
            File dir = new File(userHomeDirectory, projectDirectory + File.separator + savedDirectory);
            if (!dir.exists()) {
                Files.createDirectories(dir.toPath());
            }
            String fileName;
            if (currentFileName.isEmpty()) {
                fileName = LocalDateTime.now().toString().replace(":", "-") + ".json";
                currentFileName = fileName;
            } else {
                fileName = currentFileName;
            }
            File file = new File(dir, fileName);

            Grid grid = Grid.getInstance();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, grid);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    @Override
    public void saveAs(Path path) {
        try {
            Grid grid = Grid.getInstance();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter().writeValue(path.toFile(), grid);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }
}
