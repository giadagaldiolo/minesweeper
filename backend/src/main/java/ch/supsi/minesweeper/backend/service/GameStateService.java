package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.business.check_win.CheckWin;
import ch.supsi.minesweeper.backend.business.check_win.ICheckWin;
import ch.supsi.minesweeper.backend.business.lose_game.ILoseGame;
import ch.supsi.minesweeper.backend.business.lose_game.LoseGame;
import ch.supsi.minesweeper.backend.business.win_game.IWinGame;
import ch.supsi.minesweeper.backend.business.win_game.WinGame;
import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.backend.model.Grid;

public class GameStateService implements IGameState {
    private final ILoseGame loseGame = LoseGame.getInstance();
    private final ICheckWin checkWin = CheckWin.getInstance();
    private final IWinGame winGame = WinGame.getInstance();
    private final Grid grid = Grid.getInstance();
    private GameStatus status = GameStatus.NEW_GAME;

    @Override
    public void loseGame() {
        loseGame.loseGame(grid);
    }

    @Override
    public boolean checkForWin() {
        return checkWin.checkWin(grid);
    }

    @Override
    public void winGame() {
        winGame.winGame(grid);
    }

    @Override
    public GameStatus getGameStatus() {
        return status;
    }

    @Override
    public void setGameStatus(GameStatus status) {
        this.status = status;
    }
}

