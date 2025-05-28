package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.GameLogic;
import ch.supsi.minesweeper.backend.business.Grid;

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

    public void reveal(int row, int col){
        gameLogic.reveal(row, col);
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

    public int getNumOfFlags(){
        return gameLogic.getNumOfFlags();
    }

    public void incrementNumOfFlags(){
        gameLogic.incrementNumOfFlags();
    }

    public void decrementNumOfFlags(){
        gameLogic.decrementNumOfFlags();
    }

    public void setNumOfFlags(int numOfFlags){
        gameLogic.setNumOfFlags(numOfFlags);
    }

    public Grid getGrid(){
        return gameLogic.getGrid();
    }


}
