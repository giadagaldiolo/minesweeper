package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.backend.business.GameStatus;
import ch.supsi.minesweeper.frontend.model.AbstractModel;
import ch.supsi.minesweeper.frontend.model.GameModel;
import ch.supsi.minesweeper.frontend.model.TranslationsModel;
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
    private TranslationsModel translationsModel;

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
    public void initialize(AbstractModel model, AbstractModel translationsModel) {
        this.gameModel = (GameModel) model;
        this.translationsModel = (TranslationsModel) translationsModel;
        this.userFeedbackBar.setText(this.translationsModel.translate("label.startText"));
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
                        translationsModel.translate("label.preferencesAplliedMines") + " " + gameModel.getNumOfMines() + "\n" +
                                translationsModel.translate("label.preferencesAplliedLanguage") + " " +
                                translationsModel.translate("label.language") + "\n" +
                                translationsModel.translate("label.firstMove")
                );
                break;

            case NEW_GAME_DEFAULT_PROPERTIES:
                this.userFeedbackBar.setText(
                        translationsModel.translate("label.defaultPreferencesAplliedMines") + " " + gameModel.getNumOfMines() + "\n" +
                                translationsModel.translate("label.defaultPreferencesAplliedLanguage") + " " +
                                translationsModel.translate("label.language") + "\n" +
                                translationsModel.translate("label.firstMove")
                );
                break;

            case FLAG_UPDATE:
                this.userFeedbackBar.setText(
                        translationsModel.translate("label.minesLeft") + " " + gameModel.getNumOfMines()
                );
                break;

            case WIN:
                this.userFeedbackBar.setText(translationsModel.translate("label.win"));
                break;

            case LOSE:
                this.userFeedbackBar.setText(translationsModel.translate("label.gameOver"));
                break;

            case SAVE:
                this.userFeedbackBar.setText(translationsModel.translate("label.savedGame"));
                break;

            case OPEN:
                this.userFeedbackBar.setText(translationsModel.translate("label.gameOpened"));
                break;

            case NOT_OPEN:
                this.userFeedbackBar.setText(translationsModel.translate("label.gameNotOpened"));
                break;

            default:
                break;
        }
    }

}
