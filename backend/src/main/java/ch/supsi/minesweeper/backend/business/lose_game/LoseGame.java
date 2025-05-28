package ch.supsi.minesweeper.backend.business.lose_game;

import ch.supsi.minesweeper.backend.business.Grid;

public class LoseGame implements ILoseGame{

    private static LoseGame mySelf;

    private LoseGame(){}


    public static LoseGame getInstance() {
        if (mySelf == null) {
            mySelf = new LoseGame();
        }
        return mySelf;
    }

    @Override
    public void loseGame(Grid grid) {
        for (int i = 0; i < grid.getGrid().length; i++) {
            for (int j = 0; j < grid.getGrid()[i].length; j++) {
                if ( grid.getGrid()[i][j].isHasMine()) {
                    grid.getGrid()[i][j].setRevealed(true);
                }
            }
        }
    }

}
