package ch.supsi.minesweeper.frontend.model;

import java.util.Random;

public class Grid {
    private static final int size = 9;
    private static final int maxMines = size * size -1;

    private Cell[][] grid = new Cell[size][size];

    public Grid(int numOfMines){
        initializeGrid(numOfMines);
    }

    public Cell[][] getGrid() {
        return grid;
    }

    //Metodo che inizializza la griglia
    private void initializeGrid(int numOfMines){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
        placeMines(numOfMines);
        setValueOnCell();
    }

    //Metodo che piazza le mine a random nella griglia
    private void placeMines(int numOfMines){
        Random random = new Random();
        int minesPlaced = 0;
        while(minesPlaced<numOfMines){
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if(!grid[row][col].isHasMine()){
                grid[row][col].setHasMine(true);
                System.out.println(row + " " + col);
                minesPlaced++;
            }
        }
    }

    private void setValueOnCell() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!grid[i][j].isHasMine()) {
                    int minesAround = 0;

                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k == i && l == j) continue;

                            if (k >= 0 && k < size && l >= 0 && l < size) {
                                if (grid[k][l].isHasMine()) {
                                    minesAround++;
                                }
                            }
                        }
                    }
                    grid[i][j].setValue(minesAround);
                }
            }
        }
    }
}