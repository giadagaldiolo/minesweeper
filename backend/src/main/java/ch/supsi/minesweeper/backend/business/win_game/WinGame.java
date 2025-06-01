package ch.supsi.minesweeper.backend.business.win_game;

import ch.supsi.minesweeper.backend.model.Cell;
import ch.supsi.minesweeper.backend.model.Grid;

public class WinGame implements IWinGame{

    private static WinGame mySelf;

    private WinGame(){}


    public static WinGame getInstance(){
        if(mySelf == null){
            mySelf = new WinGame();
        }
        return mySelf;
    }

    @Override
    public void winGame(Grid grid) {
        for (Cell[] row : grid.getGrid()) {
            for (Cell cell : row) {
                if (cell.isHasFlag()) {
                    cell.setHasFlag(false);
                }
            }
        }
    }
}
