package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.business.reveal.IReveal;
import ch.supsi.minesweeper.backend.business.reveal.Reveal;
import ch.supsi.minesweeper.backend.business.toggle_cell.IToggleCell;
import ch.supsi.minesweeper.backend.business.toggle_cell.ToggleCell;
import ch.supsi.minesweeper.backend.model.Grid;

public class CellInteractionService implements ICellInteraction {
    private final IToggleCell toggleCell = ToggleCell.getInstance();
    private final IReveal reveal = Reveal.getInstance();
    private final Grid grid = Grid.getInstance();

    @Override
    public void toggleCell(int row, int col) {
        if (toggleCell.toggleCell(grid, row, col)) {
            grid.incrementNumOfMines();
        } else {
            grid.decrementNumOfMines();
        }
    }

    @Override
    public boolean reveal(int row, int col) {
        return reveal.reveal(grid, row, col);
    }
}
