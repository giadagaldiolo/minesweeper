package ch.supsi.minesweeper.view;

public interface DataView {

    void updateForNewGame();
    void updateFlags(int row, int col);
    void updateReveal();

}
