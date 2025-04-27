package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesModel;
import ch.supsi.minesweeper.model.PropertiesProvider;
import ch.supsi.minesweeper.model.GameEventHandler;
import ch.supsi.minesweeper.model.GameModel;
import ch.supsi.minesweeper.model.PlayerEventHandler;
import ch.supsi.minesweeper.view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;

    private GameModel gameModel;
    private PropertiesProvider preferencesModel;

    private List<DataView> views;

    private GameController () {
        this.gameModel = GameModel.getInstance();
        this.preferencesModel = PropertiesModel.getInstance();
        int numMinesPref = Integer.parseInt(preferencesModel.getProperty("numMines"));
        gameModel.setMines(numMinesPref);
    }

    public static GameController getInstance() {
        if (myself == null) {
            myself = new GameController();
        }

        return myself;
    }

    public void initialize(List<DataView> views) {
        this.views = views;
    }

    @Override
    public void newGame() {
        gameModel.newGame();
        this.views.forEach(DataView::updateForNewGame);
    }

    @Override
    public void save() {
        // do whatever you must do to start a new game

        // then update your views
        //this.views.forEach(DataView::updateForNewGame);
    }

    // add all the relevant methods to handle all those defined by the GameEventHandler interface
    // ...


    @Override
    public void toggleFlag(int row, int col) {
        gameModel.toggleFlag(row, col);
        views.forEach(view -> view.updateFlags(row, col));
    }

    @Override
    public void reveal(int row, int col) {
        gameModel.reveal(row, col);
        views.forEach(DataView::updateReveal);
    }

    @Override
    public void loseGame() {
        gameModel.loseGame();
        views.forEach(DataView::loseGame);
    }

    @Override
    public boolean checkForWin() {
        if (gameModel.checkForWin()) {
            winGame();
            return true;
        }

        return false;
    }

    @Override
    public void winGame() {
        gameModel.winGame();
        views.forEach(DataView::winGame);
    }

    public void applyPreferences(int mines) {
        gameModel.setMines(mines);
    }



}
