package ch.supsi.minesweeper.frontend.controller;

import ch.supsi.minesweeper.frontend.model.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.GameModel;


import ch.supsi.minesweeper.frontend.view.DataView;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private final TranslationsController translationsController;
    private final GameModel gameModel;
    private final PropertiesBusinessInterface preferencesModel;

    private List<DataView> views;

    private GameController () {
        this.gameModel = GameModel.getInstance();
        this.preferencesModel = PropertiesModel.getInstance();
        this.translationsController = TranslationsController.getInstance();
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
        int numMinesPref = Integer.parseInt(preferencesModel.getProperty("numMines"));

        if (!gameModel.setMines(numMinesPref)) {
            numMinesPref = Integer.parseInt(preferencesModel.getDefaultProperty("numMines"));
            // TODO: avvisa che hai usato default properties
        }
        gameModel.setMines(numMinesPref);
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

        File file = fileChooser.showSaveDialog(stage);

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
            if (gameModel.open(file.toPath(), file.getName())) {
                this.views.forEach(DataView::updateForOpen);
            } else {
                this.views.forEach(DataView::updateForNotOpen);
            }

        }
    }

    @Override
    public void quit() {
        Platform.exit();
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
