package ch.supsi.minesweeper.backend.business.reveal;

import ch.supsi.minesweeper.backend.business.Cell;
import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.backend.business.lose_game.ILoseGame;
import ch.supsi.minesweeper.backend.business.lose_game.LoseGame;


public class Reveal implements IReveal{

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

        if (row < 0 || row >= grid.getSize() || col < 0 || col >= grid.getSize()) {
            return true;
        }

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
                
                if (newRow >= 0 && newRow < grid.getSize() && newCol >= 0 && newCol < grid.getSize()) {
                    reveal(grid, newRow, newCol);
                }
            }
        }

        return true;
    }

}
