package ch.supsi.minesweeper.backend.business.toggle_cell;

import ch.supsi.minesweeper.backend.business.Grid;

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
        if (grid.getGrid()[row][col].isHasFlag()) {
            //aveva la bandiera
            grid.getGrid()[row][col].setHasFlag(false);
            return true;
        } else {
            //non aveva la bandiera
            grid.getGrid()[row][col].setHasFlag(true);
            return false;
        }
    }
}
