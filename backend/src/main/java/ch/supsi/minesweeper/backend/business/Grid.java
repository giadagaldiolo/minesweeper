package ch.supsi.minesweeper.backend.business;

import java.util.Random;

public class Grid {
    private static final int size = 9;
    private static final int maxMines = size * size -1; // TODO: controlla che le preferenze siano < di maxMines
    private static Grid myself;
    private static Cell[][] grid;
    private static int numOfMines;


    private Grid(){
        grid = new Cell[size][size];
    }

    public static Grid getInstance() {
        if (myself == null) {
            myself = new Grid();
        }
        return myself;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public void setNumOfMines(int numOfMine) {
        numOfMines = numOfMine;
        System.out.println("numero di mine:"+numOfMines);
    }

    public int getSize() {
        return size;
    }

    public static Cell[][] getGrid() {
        return grid;
    }

    public void decrementNumOfMines() {
        numOfMines--;
    }

    public void incrementNumOfMines() {
        numOfMines++;
    }

}