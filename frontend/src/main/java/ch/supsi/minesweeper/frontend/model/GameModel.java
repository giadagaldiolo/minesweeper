package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.GameApplication;
import ch.supsi.minesweeper.backend.business.GameLogic;
import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.frontend.controller.GameController;

public class GameModel extends AbstractModel{
    private static GameApplication gameApplication = GameApplication.getInstance();
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


    public void newGame() {
        gameApplication.newGame();
    }


    public void save() {

    }



    public void toggleFlag(int row, int col) {
        gameApplication.toggleCell(row, col);
    }

    public void reveal(int row, int col) {
        if (!isInBounds(row, col)) return;
        gameApplication.reveal(row, col);
    }

    public void loseGame() {
        gameApplication.loseGame();
    }

    public boolean checkForWin(){
        return gameApplication.checkForWin();
    }

    public void winGame() {
        gameApplication.winGame();
    }


    // add all the relevant missing behaviours
    // ...

    public int getNumOfFlags() {
        return gameApplication.getNumOfFlags();
    }

    public void incrementNumOfFlags() {
        gameApplication.incrementNumOfFlags();
    }

    public void decrementNumOfFlags() {
        if (gameApplication.getNumOfFlags()>0)
            gameApplication.decrementNumOfFlags();
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < gameApplication.getGrid().getGrid().length && col >= 0 && col < gameApplication.getGrid().getGrid()[0].length;
    }

    public void setMines(int numMines) {
        gameApplication.setNumOfFlags(numMines);
    }

    public Grid getGrid() {
        return gameApplication.getGrid();
    }
}