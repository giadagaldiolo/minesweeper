package ch.supsi.minesweeper.backend.business.open_game;

import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.backend.business.new_game.NewGame;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;

public class OpenGame implements IOpenGame{

    private static OpenGame myself;

    private OpenGame() {}

    public static OpenGame getInstance() {
        if (myself == null) {
            myself = new OpenGame();
        }
        return myself;
    }

    @Override
    public void open(Path path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Grid loadedGrid = mapper.readValue(path.toFile(), Grid.class);

            // Sostituisci lo stato attuale del singleton con quello caricato
            Grid current = Grid.getInstance();
            current.setSize(loadedGrid.getSize());
            current.setNumOfMines(loadedGrid.getNumOfMines());
            current.setGrid(loadedGrid.getGrid());

        } catch (IOException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
        }
    }
}
