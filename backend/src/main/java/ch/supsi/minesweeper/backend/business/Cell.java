package ch.supsi.minesweeper.backend.business;

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


}
