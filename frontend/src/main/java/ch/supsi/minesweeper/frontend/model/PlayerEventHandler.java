package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.frontend.controller.EventHandler;

public interface PlayerEventHandler extends EventHandler {
    void toggleFlag(int row, int col);
    void reveal(int row, int col);
    void loseGame();
    boolean checkForWin();
    void winGame();
    // add all the relevant missing behaviours
    // TODO
}
