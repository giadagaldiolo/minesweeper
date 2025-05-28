package ch.supsi.minesweeper.backend.business.flags_handler;

public interface IFlagsHandler {

    int getNumOfFlags(int numOfFlags);

    void incrementNumOfFlags(int numOfFlags);

    void decrementNumOfFlags(int numOfFlags);

    void setNumOfFlags(int numOfFlags, int num);
}
