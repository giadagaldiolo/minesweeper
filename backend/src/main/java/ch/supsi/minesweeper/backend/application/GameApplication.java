package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.service.*;
import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public class GameApplication implements IGamePersistenceApplication, IGameStateApplication, ICellInteractionApplication, IGameConfigurationApplication {
    private static GameApplication instance;
    private final CellInteractionService cellService = new CellInteractionService();
    private final GameConfigurationService configService = new GameConfigurationService();
    private final GamePersistenceService persistenceService = new GamePersistenceService();
    private final GameStateService stateService = new GameStateService();

    public static GameApplication getInstance() {
        if (instance == null) {
            instance = new GameApplication();
        }
        return instance;
    }


    @Override
    public void newGame() {
        persistenceService.reset();
        configService.newGame();
    }

    @Override
    public void save() {
        persistenceService.save();
    }

    @Override
    public void saveAs(String fileName,Path path) {
        persistenceService.saveAs(fileName,path);
    }

    @Override
    public boolean open(Path path, String fileName) {
        return persistenceService.open(path, fileName);
    }

    @Override
    public void toggleCell(int row, int col) {
        cellService.toggleCell(row, col);
    }

    @Override
    public boolean reveal(int row, int col) {
        return cellService.reveal(row, col);
    }

    @Override
    public void loseGame() {
        stateService.loseGame();
    }

    @Override
    public boolean checkForWin(){
        return stateService.checkForWin();
    }

    @Override
    public void winGame() {
        stateService.winGame();
    }

    @Override
    public GameStatus getGameStatus() {
        return stateService.getGameStatus();
    }

    @Override
    public void setGameStatus(GameStatus status) {
        stateService.setGameStatus(status);
    }

    @Override
    public int getNumOfMines() {
        return configService.getNumOfMines();
    }

    @Override
    public boolean setMines(int numMines) {
        return configService.setMines(numMines);
    }

    @Override
    public Grid getGrid() {
        return configService.getGrid();
    }
}

