package ch.supsi.minesweeper.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grid {
    private static Grid myself;

    @JsonProperty("size")
    private int size = 9;

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

    public void setSize(int size) {
        this.size = size;
    }

    public void decrementNumOfMines() {
        numOfMines--;
    }

    public void incrementNumOfMines() {
        numOfMines++;
    }

}