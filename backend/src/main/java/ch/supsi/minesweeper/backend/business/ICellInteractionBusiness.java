package ch.supsi.minesweeper.backend.business;

public interface ICellInteractionBusiness {
    void toggleCell(int row, int col);
    boolean reveal(int row, int col);
}
