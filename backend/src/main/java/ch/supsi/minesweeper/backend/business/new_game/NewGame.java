package ch.supsi.minesweeper.backend.business.new_game;

import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.backend.business.check_win.CheckWin;

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
    public void newGame(Grid grid, int numOfFlags) {
        grid = new Grid(numOfFlags);
    }
}
