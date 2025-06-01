package ch.supsi.minesweeper.backend.service;

import ch.supsi.minesweeper.backend.business.flags_handler.FlagsHandler;
import ch.supsi.minesweeper.backend.business.flags_handler.IFlagsHandler;
import ch.supsi.minesweeper.backend.business.reveal.IReveal;
import ch.supsi.minesweeper.backend.business.reveal.Reveal;
import ch.supsi.minesweeper.backend.business.toggle_cell.IToggleCell;
import ch.supsi.minesweeper.backend.business.toggle_cell.ToggleCell;
import ch.supsi.minesweeper.backend.model.Grid;

public class CellInteractionService implements ICellInteraction {
    private final IToggleCell toggleCell = ToggleCell.getInstance();
    private final IReveal reveal = Reveal.getInstance();
    private final IFlagsHandler flagsHandler = FlagsHandler.getInstance();
    private final Grid grid = Grid.getInstance();

    @Override
    public void toggleCell(int row, int col) {
        //se torna true la cella aveva una bandiera e ora bisogna toglierla -> va decrementato il numero di bandiere di 1
        if (toggleCell.toggleCell(grid, row, col)) {
            flagsHandler.decrementNumOfFlags();
            grid.incrementNumOfMines();
        } else {
            //se torna false va incrementato di 1(prima non l'aveva)
            flagsHandler.incrementNumOfFlags();
            grid.decrementNumOfMines();
        }
    }

    @Override
    public boolean reveal(int row, int col) {
        return reveal.reveal(grid, row, col);
    }
}
