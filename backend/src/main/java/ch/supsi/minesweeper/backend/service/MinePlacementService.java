package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.model.Grid;

import java.util.Random;

public class MinePlacementService {
    private final Random random;

    public MinePlacementService() {
        this.random = new Random();
    }

    public void placeMines(Grid grid, int numOfMines) {
        int size = grid.getSize();
        int minesPlaced = 0;
        while(minesPlaced < numOfMines){
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if(!grid.getGrid()[row][col].isHasMine()){
                grid.getGrid()[row][col].setHasMine(true);
                minesPlaced++;
            }
        }
    }
}
