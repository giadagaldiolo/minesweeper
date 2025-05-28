package ch.supsi.minesweeper.backend.business.new_game;

import ch.supsi.minesweeper.backend.business.Grid;

public interface INewGame {
    void newGame(Grid grid, int numOfFlags);
}
