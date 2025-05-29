package ch.supsi.minesweeper.backend.business.check_win;

import ch.supsi.minesweeper.backend.business.Grid;

public interface ICheckWin {
    boolean checkWin(Grid grid);
}
