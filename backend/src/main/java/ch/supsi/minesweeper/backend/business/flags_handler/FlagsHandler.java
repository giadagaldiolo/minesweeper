package ch.supsi.minesweeper.backend.business.flags_handler;

public class FlagsHandler implements IFlagsHandler{
    @Override
    public int getNumOfFlags(int numOfFlags) {
        return numOfFlags;
    }

    @Override
    public void incrementNumOfFlags(int numOfFlags) {
        numOfFlags++;
    }

    @Override
    public void decrementNumOfFlags(int numOfFlags) {
        numOfFlags--;
    }

    @Override
    public void setNumOfFlags(int numOfFlags, int num) {
        numOfFlags = num;
    }
}
