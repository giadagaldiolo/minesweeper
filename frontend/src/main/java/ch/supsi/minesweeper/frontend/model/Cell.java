package ch.supsi.minesweeper.frontend.model;

public class Cell {
    private int row;
    private int col;
    private int value; //Number of near mines
    private boolean revealed;
    private boolean hasMine;
    private boolean hasFlag;

    public boolean isRevealed() {
        return revealed;
    }
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public void reveal() {
        revealed = true;
    }

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public boolean isHasFlag() {
        return hasFlag;
    }

    public void setHasFlag(boolean hasFlag) {
        this.hasFlag = hasFlag;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

}
