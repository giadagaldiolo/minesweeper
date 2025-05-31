package ch.supsi.minesweeper.backend.business;

import ch.supsi.minesweeper.backend.model.Grid;

public interface IGridConfigurationBusiness {
    int getNumOfMines();
    boolean setMines(int numMines);
    Grid getGrid();
}