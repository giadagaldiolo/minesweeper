package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.business.new_game.INewGame;
import ch.supsi.minesweeper.backend.business.new_game.NewGame;
import ch.supsi.minesweeper.backend.model.Grid;

public class GameConfigurationService implements IGameConfiguration {
    private final INewGame newGame = NewGame.getInstance();
    private final Grid grid = Grid.getInstance();

    @Override
    public boolean setMines(int numMines) {
        int maxMines = grid.getSize() * grid.getSize() - 1;
        if (numMines <= 0 || numMines > maxMines) return false;
        grid.setNumOfMines(numMines);
        return true;
    }

    @Override
    public int getNumOfMines() {
        return grid.getNumOfMines();
    }

    @Override
    public void newGame() {
        newGame.newGame(grid);
    }

    @Override
    public Grid getGrid() {
        return grid;
    }
}
