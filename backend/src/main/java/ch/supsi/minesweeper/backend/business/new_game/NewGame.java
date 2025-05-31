package ch.supsi.minesweeper.backend.business.new_game;

import ch.supsi.minesweeper.backend.model.Cell;
import ch.supsi.minesweeper.backend.model.Grid;
import ch.supsi.minesweeper.backend.service.MinePlacementService;

import java.util.Random;

public class NewGame implements INewGame{
    private static NewGame mySelf;
    private final MinePlacementService minePlacementService = new MinePlacementService();


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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.getGrid()[i][j] = new Cell();
            }
        }
        minePlacementService.placeMines(grid,numOfMines);
        setValueOnCell(grid);
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
