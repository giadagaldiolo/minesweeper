package ch.supsi.minesweeper.backend.application;

public interface ICellInteractionApplication {
    void toggleCell(int row, int col);
    boolean reveal(int row, int col);
}
