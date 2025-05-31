package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.*;
import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public class GameModel extends AbstractModel implements IGridConfiguration, IGameStatusManager, ICellInteraction, IGameLifeCycle {
    private static final IGameLifeCycleApplication lifeCycle = GameApplication.getInstance();
    private static final ICellInteractionApplication cellInteraction = GameApplication.getInstance();
    private static final IGameStatusManagerApplication statusManager = GameApplication.getInstance();
    private static final IGridConfigurationApplication gridConfiguration = GameApplication.getInstance();
    private static GameModel myself;


    private GameModel() {
        super();

    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }

        return myself;
    }

    @Override
    public void newGame() {
        lifeCycle.newGame();
    }

    @Override
    public void save() {
        lifeCycle.save();
    }

    @Override
    public void saveAs(Path path) {
        lifeCycle.saveAs(path);
    }

    @Override
    public boolean open(Path path, String fileName) {
        return lifeCycle.open(path, fileName);
    }

    @Override
    public void toggleCell(int row, int col) {
        cellInteraction.toggleCell(row, col);
    }

    @Override
    public boolean reveal(int row, int col) {
        return cellInteraction.reveal(row, col);
    }

    @Override
    public void loseGame() {
        statusManager.loseGame();
    }

    @Override
    public boolean checkForWin(){
        return statusManager.checkForWin();
    }

    @Override
    public void winGame() {
        statusManager.winGame();
    }

    @Override
    public GameStatus getGameStatus() {
        return statusManager.getGameStatus();
    }

    @Override
    public void setGameStatus(GameStatus status) {
        statusManager.setGameStatus(status);
    }

    @Override
    public int getNumOfMines() {
        return gridConfiguration.getNumOfMines();
    }

    @Override
    public boolean setMines(int numMines) {
        return gridConfiguration.setMines(numMines);
    }

    @Override
    public Grid getGrid() {
        return gridConfiguration.getGrid();
    }
}