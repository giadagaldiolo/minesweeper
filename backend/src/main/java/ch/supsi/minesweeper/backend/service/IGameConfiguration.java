package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.model.Grid;

public interface IGameConfiguration {
    int getNumOfMines();
    boolean setMines(int numMines);
    Grid getGrid();
    void newGame();
}