package ch.supsi.minesweeper.backend.business.toggle_cell;

import ch.supsi.minesweeper.backend.model.Grid;

public class ToggleCell implements IToggleCell{

    private static ToggleCell mySelf;

    private ToggleCell(){}


    public static ToggleCell getInstance(){
        if(mySelf == null){
            mySelf = new ToggleCell();
        }
        return mySelf;
    }

    @Override
    public boolean toggleCell(Grid grid, int row, int col) {
        return grid.getGrid()[row][col].toggleFlag();
    }
}
