package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.model.Grid;

public interface IGameConfiguration {
    int getNumOfMines();
    boolean setMines(int numMines);
    Grid getGrid();
    void newGame();
}