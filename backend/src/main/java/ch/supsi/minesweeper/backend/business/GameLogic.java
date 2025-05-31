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
import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public class GameLogic implements IGameLifeCycleBusiness, ICellInteractionBusiness, IGameStatusManagerBusiness, IGridConfigurationBusiness {

    private static final IToggleCell toggleCell = ToggleCell.getInstance();
    private static final IReveal reveal = Reveal.getInstance();
    private static final ILoseGame loseGame = LoseGame.getInstance();
    private static final ICheckWin checkWin = CheckWin.getInstance();
    private static final IWinGame winGame = WinGame.getInstance();
    private static final INewGame newGame = NewGame.getInstance();
    private static final IFlagsHandler flagsHandler = FlagsHandler.getInstance();
    private static final ISaveGame saveGame = SaveGame.getInstance();
    private static final IOpenGame openGame = OpenGame.getInstance();

    private static GameLogic myself;
    private String fileName = "";
    private GameStatus status = GameStatus.NEW_GAME;

    private static Grid grid = Grid.getInstance();


    public static GameLogic getInstance() {
        if (myself == null) {
            myself = new GameLogic();
        }
        return myself;
    }

    @Override
    public void toggleCell(int row, int col) {
        //se torna true la cella aveva una bandiera e ora bisogna toglierla -> va decrementato il numero di bandiere di 1
        if (toggleCell.toggleCell(grid, row, col)){
            flagsHandler.decrementNumOfFlags();
            grid.incrementNumOfMines();
        } else{
            //se torna false va incrementato di 1(prima non l'aveva)
            flagsHandler.incrementNumOfFlags();
            grid.decrementNumOfMines();
        }

    }

    @Override
    public boolean reveal(int row, int col) {
        return reveal.reveal(grid, row, col);
    }

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
    public int getNumOfMines() {
        return grid.getNumOfMines();
    }

    @Override
    public boolean setMines(int numMines) {
        int maxMines = grid.getSize() * grid.getSize() - 1;
        if (numMines <= 0 || numMines > maxMines) {
            return false;
        }
        grid.setNumOfMines(numMines);
        return true;
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public void saveAs(Path path) {
        saveGame.saveAs(path);
    }

    @Override
    public boolean open(Path path, String fileName) {
        Grid grid = openGame.open(path);
        this.fileName = fileName;
        if (grid != null) {
            GameLogic.grid = grid;
            return true;
        }
        return false;
    }

    @Override
    public void newGame() {
        newGame.newGame(grid);
    }

    @Override
    public void save() {
        this.fileName = saveGame.save(this.fileName);
    }

    @Override
    public GameStatus getGameStatus() {
        return this.status;
    }

    @Override
    public void setGameStatus(GameStatus status) {
        this.status = status;
    }
}
