package ch.supsi.minesweeper.backend.business.reveal;

import ch.supsi.minesweeper.backend.business.Grid;

public interface IReveal {

    boolean reveal(Grid grid, int row, int col);
}
