package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.controller.GameController;
import ch.supsi.minesweeper.view.GameBoardViewFxml;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;
    private static int numOfFlags = 0;
    private static Grid grid;

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
        System.out.println("New game started model");
        grid = new Grid(numOfFlags);
    }

    @Override
    public void save() {

    }


    @Override
    public void toggleFlag(int row, int col) {
        if(grid.getGrid()[row][col].isHasFlag()) {
            grid.getGrid()[row][col].setHasFlag(false);
            incrementNumOfFlags();
        } else{
            grid.getGrid()[row][col].setHasFlag(true);
            decrementNumOfFlags();
        }
    }

    public void reveal(int row, int col) {
        if (!isInBounds(row, col)) return;

        Cell cell = grid.getGrid()[row][col];

        if (cell.isRevealed() || cell.isHasFlag()) return;

        cell.setRevealed(true);

        if (cell.isHasMine()) {
            GameController.getInstance().endGame();
            return;
        }

        // STOP: non ricorsione se ha un numero > 0
        if (cell.getValue() > 0) {
            return;
        }

        // Se value == 0, continua a rivelare intorno
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                reveal(row + dx, col + dy);
            }
        }
    }

    @Override
    public void endGame() {
        for (int i = 0; i < grid.getGrid().length; i++) {
            for (int j = 0; j < grid.getGrid()[i].length; j++) {
                if ( grid.getGrid()[i][j].isHasMine()) {
                    grid.getGrid()[i][j].setRevealed(true);
                }
            }
        }
    }


    // add all the relevant missing behaviours
    // ...

    public int getNumOfFlags() {
        return numOfFlags;
    }

    public void incrementNumOfFlags() {
        numOfFlags++;
    }

    public void decrementNumOfFlags() {
        if (numOfFlags>0)
            numOfFlags--;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < grid.getGrid().length && col >= 0 && col < grid.getGrid()[0].length;
    }

    public void setMines(int numMines) {
        numOfFlags = numMines;
    }


    public Grid getGrid() {
        return grid;
    }
}
