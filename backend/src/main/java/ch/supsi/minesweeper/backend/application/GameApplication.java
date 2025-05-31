package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.*;
import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public class GameApplication implements IGameLifeCycleApplication, IGameStatusManagerApplication, ICellInteractionApplication, IGridConfigurationApplication {
    private static GameApplication instance;
    private static final IGameLifeCycleBusiness lifeCycle = GameLogic.getInstance();
    private static final ICellInteractionBusiness cellInteraction = GameLogic.getInstance();
    private static final IGameStatusManagerBusiness statusManager = GameLogic.getInstance();
    private static final IGridConfigurationBusiness gridConfiguration = GameLogic.getInstance();

    public static GameApplication getInstance() {
        if (instance == null) {
            instance = new GameApplication();
        }
        return instance;
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

