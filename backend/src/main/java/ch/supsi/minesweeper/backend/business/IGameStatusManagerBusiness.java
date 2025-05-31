package ch.supsi.minesweeper.backend.business;

import ch.supsi.minesweeper.backend.model.GameStatus;

public interface IGameStatusManagerBusiness {
    void winGame();
    void loseGame();
    boolean checkForWin();
    GameStatus getGameStatus();
    void setGameStatus(GameStatus status);
}