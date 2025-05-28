package ch.supsi.minesweeper.backend.business.reveal;

import ch.supsi.minesweeper.backend.business.Cell;
import ch.supsi.minesweeper.backend.business.Grid;
import ch.supsi.minesweeper.frontend.controller.GameController;

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
    public void reveal(Grid grid, int row, int col) {

        Cell cell = grid.getGrid()[row][col];

        if (cell.isRevealed() || cell.isHasFlag()) return;

        cell.setRevealed(true);

        if (cell.isHasMine()) {
            GameController.getInstance().loseGame();
            return;
        }

        // STOP: non ricorsione se ha un numero > 0
        if (cell.getValue() > 0) {
            return;
        }
        // Se value == 0, continua a rivelare intorno
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                reveal(grid,row + dx, col + dy);
            }
        }
    }
}
