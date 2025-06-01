package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.business.open_game.IOpenGame;
import ch.supsi.minesweeper.backend.business.open_game.OpenGame;
import ch.supsi.minesweeper.backend.business.save_game.ISaveGame;
import ch.supsi.minesweeper.backend.business.save_game.SaveGame;
import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public class GamePersistenceService implements IGamePersistence {
    private final ISaveGame saveGame = SaveGame.getInstance();
    private final IOpenGame openGame = OpenGame.getInstance();
    private String fileName = "";
    private Path filePath;
    private Grid grid = Grid.getInstance();

    @Override
    public void saveAs(Path path) {
        this.filePath = path;
        saveGame.saveAs(path);
    }

    @Override
    public boolean open(Path path, String fileName) {
        Grid opened = openGame.open(path);
        if (opened != null) {
            System.out.println(path);
            System.out.println(fileName);
            grid = opened;
            this.fileName = fileName;
            this.filePath = path;
            return true;
        }
        return false;
    }

    @Override
    public void save() {
        this.fileName = saveGame.save(this.fileName, this.filePath);
    }

}
