package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.controller.EventHandler;

public interface PlayerEventHandler extends EventHandler {

    void toggleFlag(int row, int col);
    void reveal(int row, int col);
    void endGame();
    // add all the relevant missing behaviours
    // ...

}
