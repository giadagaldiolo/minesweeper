package ch.supsi.minesweeper.backend.business;

import ch.supsi.minesweeper.backend.application.GameApplication;
import ch.supsi.minesweeper.backend.business.check_win.CheckWin;
import ch.supsi.minesweeper.backend.business.check_win.ICheckWin;
import ch.supsi.minesweeper.backend.business.flags_handler.FlagsHandler;
import ch.supsi.minesweeper.backend.business.flags_handler.IFlagsHandler;
import ch.supsi.minesweeper.backend.business.lose_game.ILoseGame;
import ch.supsi.minesweeper.backend.business.lose_game.LoseGame;
import ch.supsi.minesweeper.backend.business.new_game.INewGame;
import ch.supsi.minesweeper.backend.business.new_game.NewGame;
import ch.supsi.minesweeper.backend.business.reveal.IReveal;
import ch.supsi.minesweeper.backend.business.reveal.Reveal;
import ch.supsi.minesweeper.backend.business.toggle_cell.IToggleCell;
import ch.supsi.minesweeper.backend.business.toggle_cell.ToggleCell;
import ch.supsi.minesweeper.backend.business.win_game.IWinGame;
import ch.supsi.minesweeper.backend.business.win_game.WinGame;
import ch.supsi.minesweeper.frontend.controller.GameController;
import com.sun.scenario.effect.impl.prism.PrRenderInfo;

public class GameLogic{

    private static IToggleCell toggleCell = ToggleCell.getInstance();
    private static IReveal reveal = Reveal.getInstance();
    private static ILoseGame loseGame = LoseGame.getInstance();
    private static ICheckWin checkWin = CheckWin.getInstance();
    private static IWinGame winGame = WinGame.getInstance();
    private static INewGame newGame = NewGame.getInstance();
    private static IFlagsHandler flagsHandler = new FlagsHandler();

    private static GameLogic myself;
    private static int numOfFlags = 0;
    private static Grid grid;



    public static GameLogic getInstance() {
        if (myself == null) {
            myself = new GameLogic();
        }
        return myself;
    }
    public void newGame() {
        newGame.newGame(grid,numOfFlags);
    }


    public void save() {

    }


    public void toggleCell(int row, int col) {
        //se torna true va decrementato il numero di bandiere di 1(prima l'aveva)
        if(toggleCell.toggleCell(grid, row, col)){
            flagsHandler.decrementNumOfFlags(numOfFlags);
        } else{
            //se torna false va incrementato di 1(prima non l'aveva)
            flagsHandler.incrementNumOfFlags(numOfFlags);
        }
    }




    public void reveal(int row, int col) {
        if (!isInBounds(row, col)) return;
        reveal.reveal(grid, row, col);
    }

    public void loseGame() {
       loseGame.loseGame(grid);
    }

    public boolean checkForWin() {
        return checkWin.checkWin(grid);
    }

    public void winGame() {
        winGame.winGame(grid);
    }

    public int getNumOfFlags() {
        return flagsHandler.getNumOfFlags(numOfFlags);
    }

    public void incrementNumOfFlags() {
        flagsHandler.incrementNumOfFlags(numOfFlags);
    }

    public void decrementNumOfFlags() {
        if (flagsHandler.getNumOfFlags(numOfFlags)>0)
            flagsHandler.decrementNumOfFlags(numOfFlags);
    }

    public void setNumOfFlags(int num) {
        flagsHandler.setNumOfFlags(numOfFlags, num);
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < grid.getGrid().length && col >= 0 && col < grid.getGrid()[0].length;
    }

    public void setMines(int numMines) {
        numOfFlags = numMines;
    }

    public Grid getGrid() {
        return grid;
    }

}