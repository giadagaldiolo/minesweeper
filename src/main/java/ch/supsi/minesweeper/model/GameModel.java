package ch.supsi.minesweeper.model;

public class GameModel extends AbstractModel implements GameEventHandler, PlayerEventHandler{

    private static GameModel myself;
    private static int numOfFlags = 0;

    private GameModel() {
        super();
    }

    public static GameModel getInstance() {
        if (myself == null) {
            myself = new GameModel();
        }

        return myself;
    }

    @Override
    public void newGame() {

    }

    @Override
    public void save() {

    }

    @Override
    public void move() {
        return;
    }

    @Override
    public void flag() {

    }

    // add all the relevant missing behaviours
    // ...

    public int getNumOfFlags() {
        return numOfFlags;
    }

    public void incrementNumOfFlags() {
        numOfFlags++;
    }

    public void decrementNumOfFlags() {
        if (numOfFlags>0)
            numOfFlags--;
    }



}
