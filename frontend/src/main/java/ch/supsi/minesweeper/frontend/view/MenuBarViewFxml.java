package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.backend.model.GameStatus;
import ch.supsi.minesweeper.frontend.controller.EventHandler;
import ch.supsi.minesweeper.frontend.controller.GameEventHandler;
import ch.supsi.minesweeper.frontend.model.AbstractModel;
import ch.supsi.minesweeper.frontend.model.GameModel;
import ch.supsi.minesweeper.frontend.model.TranslationsModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MenuBarViewFxml implements ControlledTranslatableFxView {

    private static MenuBarViewFxml myself;

    private GameEventHandler gameEventHandler;
    private GameModel gameModel;
    private TranslationsModel translationsModel;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem saveAsMenuItem;
    @FXML
    private MenuItem quitMenuItem;
    @FXML
    private MenuItem preferencesMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private MenuItem helpMenuItem;

    private MenuBarViewFxml() {}

    public static ControlledTranslatableFxView getInstance() {
        if (myself == null) {
            myself = new MenuBarViewFxml();

            try {
                URL fxmlUrl = MenuBarViewFxml.class.getResource("/menubar.fxml");
                if (fxmlUrl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    fxmlLoader.setController(myself);
                    fxmlLoader.load();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return myself;
    }

    @Override
    public void initialize(EventHandler eventHandler, AbstractModel model, AbstractModel translationsModel) {
        this.createBehaviour();
        this.gameEventHandler = (GameEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
        this.translationsModel = (TranslationsModel) translationsModel;
        this.translateText();
    }

    private void createBehaviour() {
        // new
        this.newMenuItem.setOnAction(event -> this.gameEventHandler.newGame());

        // save
        this.saveMenuItem.setOnAction(event -> this.gameEventHandler.save());

        // save as
        this.saveAsMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.saveAs(stage);
        });

        // open
        this.openMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.open(stage);
        });

        //quit
        this.quitMenuItem.setOnAction(event -> this.gameEventHandler.quit());

        //edit
        this.preferencesMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.editPreferences(stage);
        });

        //help
        this.helpMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.help(stage, "label.helpText");
        });

        //about
        this.aboutMenuItem.setOnAction(event -> {
            Stage stage = (Stage) menuBar.getScene().getWindow();
            this.gameEventHandler.about(stage, "label.aboutText");
        });
    }

    public void translateText() {
        this.fileMenu.setText(this.translationsModel.translate("label.file"));
        this.editMenu.setText(this.translationsModel.translate("label.edit"));
        this.helpMenu.setText(this.translationsModel.translate("label.help"));
        this.newMenuItem.setText(this.translationsModel.translate("label.new"));
        this.openMenuItem.setText(this.translationsModel.translate("label.open"));
        this.saveMenuItem.setText(this.translationsModel.translate("label.save"));
        this.saveAsMenuItem.setText(this.translationsModel.translate("label.saveAs"));
        this.quitMenuItem.setText(this.translationsModel.translate("label.quit"));
        this.preferencesMenuItem.setText(this.translationsModel.translate("label.preferences"));
        this.aboutMenuItem.setText(this.translationsModel.translate("label.about"));
        this.helpMenuItem.setText(this.translationsModel.translate("label.help"));
    }

    @Override
    public Node getNode() {
        return this.menuBar;
    }

    @Override
    public void update() {
        GameStatus status = gameModel.getGameStatus();
        switch (status) {
            case NEW_GAME:
                saveMenuItem.setDisable(false);
                saveAsMenuItem.setDisable(false);
                translateText();
                break;
            case OPEN:
                saveMenuItem.setDisable(false);
                saveAsMenuItem.setDisable(false);
                break;
            case LOSE:
                saveMenuItem.setDisable(true);
                saveAsMenuItem.setDisable(true);
            case WIN:
                saveMenuItem.setDisable(true);
                saveAsMenuItem.setDisable(true);
            default:
                break;
        }
    }

}
