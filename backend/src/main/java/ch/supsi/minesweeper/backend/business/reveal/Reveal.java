package ch.supsi.minesweeper.backend.business.reveal;

import ch.supsi.minesweeper.backend.model.Cell;
import ch.supsi.minesweeper.backend.model.Grid;


public class Reveal implements IReveal {

    private static Reveal mySelf;

    private Reveal(){}

    public static Reveal getInstance(){
        if(mySelf == null){
            mySelf = new Reveal();
        }
        return mySelf;
    }

    @Override
    public boolean reveal(Grid grid, int row, int col) {
        int size = grid.getSize();
        if (!isValidPosition(row, col, size)) {
            return true;
        }
        return revealRecursive(grid, row, col, size);
    }

    private boolean revealRecursive(Grid grid, int row, int col, int size) {
        Cell cell = grid.getGrid()[row][col];

        if (cell.isRevealed() || cell.isHasFlag()) return true;

        cell.setRevealed(true);

        if (cell.isHasMine()) {
            // Hai perso
            return false;
        }

        if (cell.getValue() > 0) {
            return true;
        }

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int newRow = row + dx;
                int newCol = col + dy;

                if (isValidPosition(newRow, newCol, size)) {
                    revealRecursive(grid, newRow, newCol, size);
                }
            }
        }

        return true;
    }

    private boolean isValidPosition(int row, int col, int size) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}

