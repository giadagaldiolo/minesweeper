package ch.supsi.minesweeper.backend.business.new_game;

import ch.supsi.minesweeper.backend.business.Cell;
import ch.supsi.minesweeper.backend.business.Grid;

import java.util.Random;

public class NewGame implements INewGame{
    private static NewGame mySelf;


    private NewGame(){}


    public static NewGame getInstance() {
        if (mySelf == null) {
            mySelf = new NewGame();
        }
        return mySelf;
    }

    @Override
    public void newGame(Grid grid) {
        initializeGrid(grid);
    }

    //Metodo che inizializza la griglia
    public void initializeGrid(Grid grid){
        int size = grid.getSize();
        int numOfMines = grid.getNumOfMines();
        System.out.println(grid.getNumOfMines()+"!!!!!!");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.getGrid()[i][j] = new Cell();
            }
        }
        placeMines(grid,numOfMines);
        setValueOnCell(grid);
    }

    //Metodo che piazza le mine a random nella griglia
    private void placeMines(Grid grid,int numOfMines){
        int size = grid.getSize();
        Random random = new Random();
        int minesPlaced = 0;
        while(minesPlaced<numOfMines){
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if(!grid.getGrid()[row][col].isHasMine()){
                grid.getGrid()[row][col].setHasMine(true);
                System.out.println(row + " " + col);
                minesPlaced++;
            }
        }
    }

    private void setValueOnCell(Grid grid) {
        int size = grid.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!grid.getGrid()[i][j].isHasMine()) {
                    int minesAround = 0;

                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k == i && l == j) continue;

                            if (k >= 0 && k < size && l >= 0 && l < size) {
                                if (grid.getGrid()[k][l].isHasMine()) {
                                    minesAround++;
                                }
                            }
                        }
                    }
                    grid.getGrid()[i][j].setValue(minesAround);
                }
            }
        }
    }
}
