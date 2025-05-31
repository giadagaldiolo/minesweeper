package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.model.GameStatus;

public interface IGameStatusManager {
    void winGame();
    void loseGame();
    boolean checkForWin();
    GameStatus getGameStatus();
    void setGameStatus(GameStatus status);
}