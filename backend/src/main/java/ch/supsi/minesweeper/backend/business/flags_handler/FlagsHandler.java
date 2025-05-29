package ch.supsi.minesweeper.backend.business.flags_handler;

import ch.supsi.minesweeper.backend.business.new_game.NewGame;

public class FlagsHandler implements IFlagsHandler{


    private static FlagsHandler mySelf;

    private FlagsHandler(){}


    public static FlagsHandler getInstance() {
        if (mySelf == null) {
            mySelf = new FlagsHandler();
        }
        return mySelf;
    }

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
