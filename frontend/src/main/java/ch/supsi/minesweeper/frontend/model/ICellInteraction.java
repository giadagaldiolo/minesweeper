package ch.supsi.minesweeper.frontend.model;

public interface ICellInteraction {
    void toggleCell(int row, int col);
    boolean reveal(int row, int col);
}
