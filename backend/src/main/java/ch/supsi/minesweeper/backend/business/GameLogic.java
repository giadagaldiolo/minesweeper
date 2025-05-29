package ch.supsi.minesweeper.backend.business;

import ch.supsi.minesweeper.backend.business.check_win.CheckWin;
import ch.supsi.minesweeper.backend.business.check_win.ICheckWin;
import ch.supsi.minesweeper.backend.business.flags_handler.FlagsHandler;
import ch.supsi.minesweeper.backend.business.flags_handler.IFlagsHandler;
import ch.supsi.minesweeper.backend.business.lose_game.ILoseGame;
import ch.supsi.minesweeper.backend.business.lose_game.LoseGame;
import ch.supsi.minesweeper.backend.business.new_game.INewGame;
import ch.supsi.minesweeper.backend.business.new_game.NewGame;
import ch.supsi.minesweeper.backend.business.open_game.IOpenGame;
import ch.supsi.minesweeper.backend.business.open_game.OpenGame;
import ch.supsi.minesweeper.backend.business.reveal.IReveal;
import ch.supsi.minesweeper.backend.business.reveal.Reveal;
import ch.supsi.minesweeper.backend.business.save_game.ISaveGame;
import ch.supsi.minesweeper.backend.business.save_game.SaveGame;
import ch.supsi.minesweeper.backend.business.toggle_cell.IToggleCell;
import ch.supsi.minesweeper.backend.business.toggle_cell.ToggleCell;
import ch.supsi.minesweeper.backend.business.win_game.IWinGame;
import ch.supsi.minesweeper.backend.business.win_game.WinGame;

import java.nio.file.Path;

public class GameLogic {

    private static IToggleCell toggleCell = ToggleCell.getInstance();
    private static IReveal reveal = Reveal.getInstance();
    private static ILoseGame loseGame = LoseGame.getInstance();
    private static ICheckWin checkWin = CheckWin.getInstance();
    private static IWinGame winGame = WinGame.getInstance();
    private static INewGame newGame = NewGame.getInstance();
    private static IFlagsHandler flagsHandler = FlagsHandler.getInstance();
    private static ISaveGame saveGame = SaveGame.getInstance();
    private static IOpenGame openGame = OpenGame.getInstance();

    private static GameLogic myself;

    private static Grid grid = Grid.getInstance();


    public static GameLogic getInstance() {
        if (myself == null) {
            myself = new GameLogic();
        }
        return myself;
    }

    public void newGame() {
        newGame.newGame(grid);
    }


    public void save() {
        saveGame.save();
    }


    public void toggleCell(int row, int col) {
        System.out.println("sono stato chiamato");
        //se torna true va decrementato il numero di bandiere di 1(prima l'aveva)
        if(toggleCell.toggleCell(grid, row, col)){
            flagsHandler.decrementNumOfFlags();
            grid.incrementNumOfMines();
            System.out.println("sono nell if");
        } else{
            //se torna false va incrementato di 1(prima non l'aveva)
            flagsHandler.incrementNumOfFlags();
            grid.decrementNumOfMines();
            System.out.println("sono nell else");
        }

    }


    public boolean reveal(int row, int col) {
        return reveal.reveal(grid, row, col);
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

    public int getNumOfMines() {
        return grid.getNumOfMines();
    }



    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < grid.getSize() && col >= 0 && col < grid.getSize();
    }

    public void setMines(int numMines) {
        grid.setNumOfMines(numMines);
    }

    public Grid getGrid() {
        return grid;
    }

    public void saveAs(Path path) {
        saveGame.saveAs(path);
    }

    public void open(Path path) {
        openGame.open(path);
    }
}