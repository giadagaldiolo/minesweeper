package ch.supsi.minesweeper.backend.business.save_game;

import ch.supsi.minesweeper.backend.data_access.JsonSaveDataAccessInterface;
import ch.supsi.minesweeper.backend.model.Grid;
import ch.supsi.minesweeper.backend.data_access.JsonSaveDataAccess;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class SaveGame implements ISaveGame{

    private static SaveGame myself;
    private final JsonSaveDataAccessInterface saveDataAccess = new JsonSaveDataAccess();
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
    public String save(String fileName, Path path) {
        try {
            if (path == null) {
                path = Paths.get(userHomeDirectory, projectDirectory, savedDirectory);
                saveDataAccess.ensureDirectoryExists(path);
                if (fileName.isEmpty()) {
                    fileName = LocalDateTime.now().toString().replace(":", "-") + ".json";
                }
                path = path.resolve(fileName);
            } else {
                saveDataAccess.ensureDirectoryExists(path.getParent());
            }

            Grid grid = Grid.getInstance();
            saveDataAccess.saveToFile(path, grid);

        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
        return fileName;
    }


    @Override
    public void saveAs(Path path) {
        try {
            Grid grid = Grid.getInstance();
            saveDataAccess.saveToFile(path, grid);
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }
}
