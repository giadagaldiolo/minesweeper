package ch.supsi.minesweeper.backend.business.check_win;

import ch.supsi.minesweeper.backend.model.Grid;

public class CheckWin implements ICheckWin{

    private static CheckWin mySelf;

    private CheckWin(){}


    public static CheckWin getInstance() {
        if (mySelf == null) {
            mySelf = new CheckWin();
        }
        return mySelf;
    }
    @Override
    public boolean checkWin(Grid grid) {
        for (int i = 0; i < grid.getGrid().length; i++) {
            for (int j = 0; j < grid.getGrid()[i].length; j++) {
                if (!grid.getGrid()[i][j].isRevealed() && !grid.getGrid()[i][j].isHasMine()) {
                    return false;
                }
            }
        }
        return true;
    }
}
