package ch.supsi.minesweeper.backend.business.flags_handler;

public interface IFlagsHandler {

    int getNumOfFlags();

    void incrementNumOfFlags();

    void decrementNumOfFlags();

}
