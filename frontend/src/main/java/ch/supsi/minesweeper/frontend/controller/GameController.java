package ch.supsi.minesweeper.frontend.controller;

import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.frontend.model.*;


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
    private final PropertiesInterface preferencesModel;
    private static final IGamePersistence persistence = GameModel.getInstance();
    private static final ICellInteraction cellInteraction = GameModel.getInstance();
    private static final IGameState statusManager = GameModel.getInstance();
    private static final IGameConfiguration gameConfiguration = GameModel.getInstance();
    private Stage primaryStage;

    private List<DataView> views;

    private GameController () {
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

        if (!gameConfiguration.setMines(numMinesPref)) {
            numMinesPref = Integer.parseInt(preferencesModel.getDefaultProperty("numMines"));
            statusManager.setGameStatus(GameStatus.NEW_GAME_DEFAULT_PROPERTIES);
            this.views.forEach(DataView::update);
        }
        gameConfiguration.setMines(numMinesPref);
        gameConfiguration.newGame();
        statusManager.setGameStatus(GameStatus.NEW_GAME);
        this.views.forEach(DataView::update);
    }

    @Override
    public void save() {
        persistence.save();
        statusManager.setGameStatus(GameStatus.SAVE);
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
            persistence.saveAs(file.getName(),file.toPath());
            statusManager.setGameStatus(GameStatus.SAVE);
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
            if (persistence.open(file.toPath(), file.getName())) {
                statusManager.setGameStatus(GameStatus.OPEN);
            } else {
                statusManager.setGameStatus(GameStatus.NOT_OPEN);
            }
            this.views.forEach(DataView::update);
        }
    }

    @Override
    public void quit() {
        if (primaryStage != null) {
            primaryStage.close();
        }
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
    public void toggleCell(int row, int col) {
        cellInteraction.toggleCell(row, col);
        statusManager.setGameStatus(GameStatus.FLAG_UPDATE);
        views.forEach(DataView::update);
    }

    @Override
    public void reveal(int row, int col) {
        if(!cellInteraction.reveal(row, col)){
            loseGame();
        }
        statusManager.setGameStatus(GameStatus.REVEAL);
        views.forEach(DataView::update);
    }

    @Override
    public void loseGame() {
        statusManager.loseGame();
        statusManager.setGameStatus(GameStatus.LOSE);
        views.forEach(DataView::update);
    }

    @Override
    public boolean checkForWin() {
        if (statusManager.checkForWin()) {
            winGame();
            return true;
        }
        return false;
    }

    @Override
    public void winGame() {
        statusManager.winGame();
        statusManager.setGameStatus(GameStatus.WIN);
        views.forEach(DataView::update);
    }

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

}
