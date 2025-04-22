package ch.supsi.minesweeper.model;

import javafx.scene.control.Button;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;
    private static int numOfFlags = 0;
    private static Cell[][] grid;

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
        //Controlla se non ha la bandiera
        if (!grid[row][col].isHasFlag()) {
            //Rivela il contenuto della cella
            grid[row][col].reveal();

            // Se la cella è vuota, puoi fare un flood fill ricorsivo o iterativo
            if (grid[row][col].getValue() == 0) {
                //revealNeighbors(row, col); // da implementare
            }

            // Se è una mina: game over (aggiungerai la logica)
        }
    }

    @Override
    public void toggleFlag(int row, int col) {
        if(grid[row][col].isHasFlag()) {
            grid[row][col].setHasFlag(false);
            System.out.println("Bandiera tolta");
        } else{
            grid[row][col].setHasFlag(true);
            System.out.println("Bandiera messa");
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



}
