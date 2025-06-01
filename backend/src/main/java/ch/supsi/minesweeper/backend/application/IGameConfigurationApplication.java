package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.model.Grid;

public interface IGameConfigurationApplication {
    int getNumOfMines();
    boolean setMines(int numMines);
    Grid getGrid();
    void newGame();
}