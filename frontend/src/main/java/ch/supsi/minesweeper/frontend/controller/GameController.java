package ch.supsi.minesweeper.frontend.controller;

import ch.supsi.minesweeper.backend.application.PropertiesBusinessInterface;
import ch.supsi.minesweeper.backend.business.properties.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.GameModel;


import ch.supsi.minesweeper.frontend.view.DataView;

import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;

    private GameModel gameModel;
    private PropertiesBusinessInterface preferencesModel;

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
        gameModel.setMines(Integer.parseInt(preferencesModel.getProperty("numMines")));
        gameModel.newGame();
        this.views.forEach(DataView::updateForNewGame);
    }

    @Override
    public void save() {
        gameModel.save();

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
        if(!gameModel.reveal(row, col)){
            loseGame();
            views.forEach(DataView::updateReveal);
        };
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
