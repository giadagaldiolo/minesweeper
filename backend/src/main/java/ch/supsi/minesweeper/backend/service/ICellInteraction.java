package ch.supsi.minesweeper.backend.service;

public interface ICellInteraction {
    void toggleCell(int row, int col);
    boolean reveal(int row, int col);
}
