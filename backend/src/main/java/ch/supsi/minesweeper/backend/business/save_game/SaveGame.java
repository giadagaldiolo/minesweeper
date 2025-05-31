package ch.supsi.minesweeper.backend.business.save_game;

import ch.supsi.minesweeper.backend.model.Grid;
import ch.supsi.minesweeper.backend.service.JsonSaveService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class SaveGame implements ISaveGame{

    private static SaveGame myself;
    private final JsonSaveService saveService = new JsonSaveService();
    private final String userHomeDirectory = System.getProperty("user.home");
    private final String projectDirectory = ".minesweeper";
    private final String savedDirectory = "saved";

    private SaveGame() {}

    public static SaveGame getInstance() {
        if (myself == null) {
            myself = new SaveGame();
        }
        return myself;
    }

    @Override
    public String save(String fileName) {
        try {
            Path dir = Paths.get(userHomeDirectory, projectDirectory, savedDirectory);
            saveService.ensureDirectoryExists(dir);
            if (fileName.isEmpty()) {
                fileName = LocalDateTime.now().toString().replace(":", "-") + ".json";
            }
            Path filePath = dir.resolve(fileName);
            Grid grid = Grid.getInstance();
            saveService.saveToFile(filePath, grid);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
        return fileName;
    }

    @Override
    public void saveAs(Path path) {
        try {
            Grid grid = Grid.getInstance();
            saveService.saveToFile(path, grid);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }
}
