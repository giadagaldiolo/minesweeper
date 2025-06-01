package ch.supsi.minesweeper.backend.business.open_game;

import ch.supsi.minesweeper.backend.data_access.JsonLoadDataAccessInterface;
import ch.supsi.minesweeper.backend.model.Grid;
import ch.supsi.minesweeper.backend.data_access.JsonLoadDataAccess;

import java.io.IOException;
import java.nio.file.Path;

public class OpenGame implements IOpenGame{

    private static OpenGame myself;
    private final JsonLoadDataAccessInterface loadDataAccess = new JsonLoadDataAccess();

    private OpenGame() {}

    public static OpenGame getInstance() {
        if (myself == null) {
            myself = new OpenGame();
        }
        return myself;
    }

    @Override
    public Grid open(Path path) {
        try {
            Grid loadedGrid = loadDataAccess.loadFromFile(path, Grid.class);

            // Sostituisci lo stato attuale del singleton con quello caricato
            Grid current = Grid.getInstance();
            current.copyFrom(loadedGrid);
            return current;
        } catch (IOException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
        }
        return null;
    }
}
