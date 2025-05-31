package ch.supsi.minesweeper.frontend.controller;

import ch.supsi.minesweeper.backend.business.GameStatus;
import ch.supsi.minesweeper.frontend.model.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.GameModel;


import ch.supsi.minesweeper.frontend.view.DataView;
import ch.supsi.minesweeper.frontend.view.HelpView;
import ch.supsi.minesweeper.frontend.view.PreferencesView;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class GameController implements GameEventHandler, PlayerEventHandler {

    private static GameController myself;
    private final TranslationsController translationsController;
    private final PropertiesController propertiesController;
    private final GameModel gameModel;
    private final PropertiesBusinessInterface preferencesModel;

    private List<DataView> views;

    private GameController () {
        this.gameModel = GameModel.getInstance();
        this.preferencesModel = PropertiesModel.getInstance();
        this.translationsController = TranslationsController.getInstance();
        this.propertiesController = PropertiesController.getInstance();
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
            gameModel.setGameStatus(GameStatus.NEW_GAME_DEFAULT_PROPERTIES);
            this.views.forEach(DataView::update);
        }
        gameModel.setMines(numMinesPref);
        gameModel.newGame();
        gameModel.setGameStatus(GameStatus.NEW_GAME);
        this.views.forEach(DataView::update);
    }

    @Override
    public void save() {
        gameModel.save();
        gameModel.setGameStatus(GameStatus.SAVE);
        this.views.forEach(DataView::update);
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
            gameModel.setGameStatus(GameStatus.SAVE);
            this.views.forEach(DataView::update);
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
                gameModel.setGameStatus(GameStatus.OPEN);
            } else {
                gameModel.setGameStatus(GameStatus.NOT_OPEN);
            }
            this.views.forEach(DataView::update);
        }
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void editPreferences(Stage stage) {
        for (DataView view : views) {
            if (view instanceof PreferencesView) {
                ((PreferencesView)view).show(stage);
            }
        }
    }

    @Override
    public void help(Stage stage, String label) {
        for (DataView view : views) {
            if (view instanceof HelpView) {
                ((HelpView)view).show(stage, label);
            }
        }
    }

    @Override
    public void toggleFlag(int row, int col) {
        gameModel.toggleFlag(row, col);
        gameModel.setGameStatus(GameStatus.FLAG_UPDATE);
        views.forEach(DataView::update);
    }

    @Override
    public void reveal(int row, int col) {
        if(!gameModel.reveal(row, col)){
            loseGame();
        }
        gameModel.setGameStatus(GameStatus.REVEAL);
        views.forEach(DataView::update);
    }

    @Override
    public void loseGame() {
        gameModel.loseGame();
        gameModel.setGameStatus(GameStatus.LOSE);
        views.forEach(DataView::update);
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
        gameModel.setGameStatus(GameStatus.WIN);
        views.forEach(DataView::update);
    }


}
