package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.GameLogic;
import ch.supsi.minesweeper.backend.business.Grid;

import java.nio.file.Path;

public class GameApplication {
    private static GameApplication instance;
    private GameLogic gameLogic = GameLogic.getInstance();

    public static GameApplication getInstance() {
        if (instance == null) {
            instance = new GameApplication();
        }
        return instance;
    }

    public void newGame() {
        gameLogic.newGame();
    }

    public void save(){
        gameLogic.save();
    }

    public void toggleCell(int row, int col){
        gameLogic.toggleCell(row, col);
    }

    public boolean reveal(int row, int col){
        return gameLogic.reveal(row, col);
    }

    public void loseGame(){
        gameLogic.loseGame();
    }

    public void winGame(){
        gameLogic.winGame();
    }

    public boolean checkForWin(){
        return gameLogic.checkForWin();
    }

    public int getNumOfMines(){
        return gameLogic.getNumOfMines();
    }

    public boolean setMines(int numOfMines){
        return gameLogic.setMines(numOfMines);
    }

    public boolean isInBounds(int row, int col) {
        return gameLogic.isInBounds(row, col);
    }

    public Grid getGrid(){
        return gameLogic.getGrid();
    }


    public void saveAs(Path path) {
        gameLogic.saveAs(path);
    }

    public boolean open(Path path) {
        return gameLogic.open(path);
    }
}
