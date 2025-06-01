package ch.supsi.minesweeper.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {
    private int row;
    private int col;

    @JsonProperty("value")
    private int value; //Number of near mines
    @JsonProperty("revealed")
    private boolean revealed;
    @JsonProperty("hasMine")
    private boolean hasMine;
    @JsonProperty("hasFlag")
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

    public boolean toggleFlag() {
        this.hasFlag = !this.hasFlag;
        return this.hasFlag;
    }

    @Override
    public Cell clone() {
        Cell cloned = new Cell();
        cloned.value = this.value;
        cloned.revealed = this.revealed;
        cloned.hasMine = this.hasMine;
        cloned.hasFlag = this.hasFlag;
        cloned.row = this.row;
        cloned.col = this.col;
        return cloned;
    }

}
