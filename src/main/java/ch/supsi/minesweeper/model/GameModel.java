package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.view.GameBoardViewFxml;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;
    private static int numOfFlags = 0;
    private static Grid grid = new Grid(numOfFlags);

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
        if(grid.getGrid()[row][col].isHasMine()){
            System.out.println("Mina trovata");
            GameBoardViewFxml.getButtons()[row][col].setText("\uD83D\uDCA3");
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

    public static void setMines(int numMines) {
        numOfFlags = numMines;
    }



}
