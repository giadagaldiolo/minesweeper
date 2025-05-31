package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.backend.business.GameStatus;
import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import ch.supsi.minesweeper.frontend.model.AbstractModel;
import ch.supsi.minesweeper.frontend.model.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;

public class UserFeedbackViewFxml implements UncontrolledFxView {

    private static UserFeedbackViewFxml myself;

    private GameModel gameModel;
    private TranslationsController translationsController;

    @FXML
    private ScrollPane containerPane;

    @FXML
    private Text userFeedbackBar;

    private UserFeedbackViewFxml() {}

    public static UserFeedbackViewFxml getInstance() {
        if (myself == null) {
            myself = new UserFeedbackViewFxml();

            try {
                URL fxmlUrl = UserFeedbackViewFxml.class.getResource("/userfeedbackbar.fxml");
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
    public void initialize(AbstractModel model, TranslationsController translationsController) {
        this.gameModel = (GameModel) model;
        this.translationsController = translationsController;
        this.userFeedbackBar.setText(
                translationsController.translate("label.startText")
        );
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }



    @Override
    public void update() {
        GameStatus status = gameModel.getGameStatus();
        switch (status) {
            case NEW_GAME:
                this.userFeedbackBar.setText(
                        translationsController.translate("label.preferencesAplliedMines") + " " + gameModel.getNumOfMines() + "\n" +
                                translationsController.translate("label.preferencesAplliedLanguage") + " " +
                                translationsController.translate("label.language") + "\n" +
                                translationsController.translate("label.firstMove")
                );
                break;

            case NEW_GAME_DEFAULT_PROPERTIES:
                this.userFeedbackBar.setText(
                        translationsController.translate("label.defaultPreferencesAplliedMines") + " " + gameModel.getNumOfMines() + "\n" +
                                translationsController.translate("label.defaultPreferencesAplliedLanguage") + " " +
                                translationsController.translate("label.language") + "\n" +
                                translationsController.translate("label.firstMove")
                );
                break;

            case FLAG_UPDATE:
                this.userFeedbackBar.setText(
                        translationsController.translate("label.minesLeft") + " " + gameModel.getNumOfMines()
                );
                break;

            case WIN:
                this.userFeedbackBar.setText(translationsController.translate("label.win"));
                break;

            case LOSE:
                this.userFeedbackBar.setText(translationsController.translate("label.gameOver"));
                break;

            case SAVE:
                this.userFeedbackBar.setText(translationsController.translate("label.savedGame"));
                break;

            case OPEN:
                this.userFeedbackBar.setText(translationsController.translate("label.gameOpened"));
                break;

            case NOT_OPEN:
                this.userFeedbackBar.setText(translationsController.translate("label.gameNotOpened"));
                break;

            default:
                break;
        }
    }

}
