package ch.supsi.minesweeper.frontend.controller;

public interface PlayerEventHandler extends EventHandler {

    //mettere quit, help e cose che non influenzano il gioco
    void toggleFlag(int row, int col);
    void reveal(int row, int col);
    void loseGame();
    boolean checkForWin();
    void winGame();
}
