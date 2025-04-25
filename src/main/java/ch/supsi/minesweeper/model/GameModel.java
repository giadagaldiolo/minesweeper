package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.view.GameBoardViewFxml;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;
    private static int numOfFlags = 0;
    private static Grid grid = new Grid();

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

    }

    @Override
    public void save() {

    }

    public void move(int row, int col) {
                if(!grid.getGrid()[row][col].isRevealed()) return;
                if(grid.getGrid()[row][col].isHasMine()){
                    System.out.println("Mina trovata");
                    GameBoardViewFxml.getButtons()[row][col].setText("\uD83D\uDCA3");
                } else if (grid.getGrid()[row][col].getValue()>0) {
                    GameBoardViewFxml.getButtons()[row][col].setText(String.valueOf(grid.getGrid()[row][col].getValue()));
                }
        //Controlla se non ha la bandiera
        if (!grid.getGrid()[row][col].isHasFlag()) {
            //Rivela il contenuto della cella
            grid.getGrid()[row][col].reveal();

            // Se la cella è vuota, puoi fare un flood fill ricorsivo o iterativo
            if (grid.getGrid()[row][col].getValue() == 0) {
                //revealNeighbors(row, col); // da implementare
            }

            // Se è una mina: game over (aggiungerai la logica)
        }
    }

    @Override
    public void toggleFlag(int row, int col) {
        if(grid.getGrid()[row][col].isHasFlag()) {
            grid.getGrid()[row][col].setHasFlag(false);
            System.out.println("Bandiera tolta");
            GameBoardViewFxml.getButtons()[row][col].setText("");
        } else{
            grid.getGrid()[row][col].setHasFlag(true);
            System.out.println("Bandiera messa");
            GameBoardViewFxml.getButtons()[row][col].setText("\uD83D\uDEA9");

        }
    }

    public void reveal(int row, int col) {
        if (!isInBounds(row, col)) return;

        Cell cell = grid.getGrid()[row][col];

        if (cell.isRevealed() || cell.isHasFlag()) return;

        cell.setRevealed(true);

        if (cell.isHasMine()) {
            // TODO: Gestisci Game Over
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

    public static Grid getGrid() {
        return grid;
    }
}
