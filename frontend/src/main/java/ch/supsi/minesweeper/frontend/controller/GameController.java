package ch.supsi.minesweeper.frontend.controller;

import ch.supsi.minesweeper.frontend.model.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.GameModel;


import ch.supsi.minesweeper.frontend.view.DataView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    TranslationsController translationsController;

    private GameModel gameModel;
    private PropertiesBusinessInterface preferencesModel;

    private List<DataView> views;

    private GameController () {
        this.gameModel = GameModel.getInstance();
        this.preferencesModel = PropertiesModel.getInstance();
        this.translationsController = TranslationsController.getInstance();
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
        this.views.forEach(DataView::updateForSavedGame);
    }

    @Override
    public void saveAs(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(translationsController.translate("label.titleOfFileChooser"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("File JSON", "*.json")
        );
        fileChooser.setInitialFileName("game.json");

        File file = fileChooser.showSaveDialog(stage);  // <-- CORRETTO: finestra di salvataggio

        if (file != null) {
            gameModel.saveAs(file.toPath());
            this.views.forEach(DataView::updateForSavedGame);
        }
    }

    @Override
    public void open(Stage stage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(translationsController.translate("label.titleOfFileChooserLoader"));
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("File JSON", "*.json")
        );
        fileChooser.setInitialFileName("game.json");

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            gameModel.open(file.toPath());
            //this.views.forEach(DataView::updateForSavedGame);
        }
    }

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


}
