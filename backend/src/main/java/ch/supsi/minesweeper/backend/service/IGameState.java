package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.model.GameStatus;

public interface IGameState {
    void winGame();
    void loseGame();
    boolean checkForWin();
    GameStatus getGameStatus();
    void setGameStatus(GameStatus status);
}