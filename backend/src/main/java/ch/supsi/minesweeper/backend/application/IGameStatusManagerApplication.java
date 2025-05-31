package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.model.GameStatus;

public interface IGameStatusManagerApplication {
    void winGame();
    void loseGame();
    boolean checkForWin();
    GameStatus getGameStatus();
    void setGameStatus(GameStatus status);
}