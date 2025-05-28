package ch.supsi.minesweeper.backend.business.win_game;

import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.backend.business.reveal.Reveal;

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
        for(int i = 0; i < grid.getGrid().length; i++) {
            for(int j = 0; j < grid.getGrid()[i].length; j++) {
                if (grid.getGrid()[i][j].isHasFlag()) {
                    grid.getGrid()[i][j].setHasFlag(false);
                }
            }
        }
    }
}
