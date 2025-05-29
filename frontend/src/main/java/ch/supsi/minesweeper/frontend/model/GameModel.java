package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.backend.application.GameApplication;
import ch.supsi.minesweeper.backend.business.Grid;

public class GameModel extends AbstractModel{
    private static final GameApplication gameApplication = GameApplication.getInstance();
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

    public boolean reveal(int row, int col) {
        if (!isInBounds(row, col)) return true;
        return gameApplication.reveal(row, col);
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

    public int getNumOfMines() {
        return gameApplication.getNumOfMines();
    }

    private boolean isInBounds(int row, int col) {
        return gameApplication.isInBounds(row, col);
    }

    public void setMines(int numMines) {
        gameApplication.setMines(numMines);
    }

    public Grid getGrid() {
        return gameApplication.getGrid();
    }
}