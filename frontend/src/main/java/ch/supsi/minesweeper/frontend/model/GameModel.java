package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.*;
import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public class GameModel extends AbstractModel implements IGameConfiguration, IGameState, ICellInteraction, IGamePersistence {
    private static final IGamePersistenceApplication persistence = GameApplication.getInstance();
    private static final ICellInteractionApplication cellInteraction = GameApplication.getInstance();
    private static final IGameStateApplication statusManager = GameApplication.getInstance();
    private static final IGameConfigurationApplication gameConfiguration = GameApplication.getInstance();
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
        gameConfiguration.newGame();
    }

    @Override
    public void save() {
        persistence.save();
    }

    @Override
    public void saveAs(String fileName,Path path) {
        persistence.saveAs(fileName,path);
    }

    @Override
    public boolean open(Path path, String fileName) {
        return persistence.open(path, fileName);
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
        return gameConfiguration.getNumOfMines();
    }

    @Override
    public boolean setMines(int numMines) {
        return gameConfiguration.setMines(numMines);
    }

    @Override
    public Grid getGrid() {
        return gameConfiguration.getGrid();
    }
}