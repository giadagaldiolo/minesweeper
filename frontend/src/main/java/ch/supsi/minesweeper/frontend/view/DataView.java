package ch.supsi.minesweeper.frontend.view;

public interface DataView {

    void updateForNewGame();
    void updateFlags(int row, int col);
    void updateReveal();
    void loseGame();
    void winGame();
    void updateForSavedGame();
    void updateForOpen();

    void updateForNotOpen();
}
