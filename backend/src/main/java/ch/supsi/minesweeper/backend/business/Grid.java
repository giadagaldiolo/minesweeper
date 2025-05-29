package ch.supsi.minesweeper.backend.business;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grid {
    private static final int size = 9;
    private static final int maxMines = size * size -1; // TODO: controlla che le preferenze siano < di maxMines
    private static Grid myself;

    @JsonProperty("grid")
    private Cell[][] grid;

    @JsonProperty("numOfMines")
    private int numOfMines;

    private Grid(){
        grid = new Cell[size][size];
    }

    public static Grid getInstance() {
        if (myself == null) {
            myself = new Grid();
        }
        return myself;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public void setNumOfMines(int numOfMine) {
        numOfMines = numOfMine;
    }

    public int getSize() {
        return size;
    }

    public void decrementNumOfMines() {
        numOfMines--;
    }

    public void incrementNumOfMines() {
        numOfMines++;
    }
}