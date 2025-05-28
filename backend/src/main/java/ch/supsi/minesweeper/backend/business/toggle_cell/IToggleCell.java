package ch.supsi.minesweeper.backend.business.toggle_cell;

import ch.supsi.minesweeper.backend.business.Grid;

public interface IToggleCell {

    boolean toggleCell(Grid grid, int row, int col);
}
