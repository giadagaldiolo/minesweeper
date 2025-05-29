package ch.supsi.minesweeper.backend.business.flags_handler;
public class FlagsHandler implements IFlagsHandler{


    private static FlagsHandler mySelf;
    private static int numOfFlags = 0;

    private FlagsHandler(){}


    public static FlagsHandler getInstance() {
        if (mySelf == null) {
            mySelf = new FlagsHandler();
        }
        return mySelf;
    }

    @Override
    public int getNumOfFlags() {
        return numOfFlags;
    }

    @Override
    public void incrementNumOfFlags() {
        numOfFlags++;

    }

    @Override
    public void decrementNumOfFlags() {
        numOfFlags--;
    }


}
