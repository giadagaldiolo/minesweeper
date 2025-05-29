package ch.supsi.minesweeper.backend.business;

public class Grid {
    private static int size = 9; // si
    private static final int maxMines = size * size -1; // TODO: controlla che le preferenze siano < di maxMines
    private static Grid myself;
    private static Cell[][] grid; // si
    private static int numOfMines; // si

    private Grid(){
        grid = new Cell[size][size];
    }

    public static Grid getInstance() {
        if (myself == null) {
            myself = new Grid();
        }
        return myself;
    }

    public static Cell[][] getGrid() {
        return grid;
    }

    public static void setGrid(Cell[][] grid) {
        Grid.grid = grid;
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

    public void setSize(int size) { Grid.size = size; }

    public void decrementNumOfMines() {
        numOfMines--;
    }

    public void incrementNumOfMines() {
        numOfMines++;
    }
}