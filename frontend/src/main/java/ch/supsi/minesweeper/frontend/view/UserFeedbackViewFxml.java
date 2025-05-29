package ch.supsi.minesweeper.frontend.view;

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
    public void updateForNewGame() {
        this.userFeedbackBar.setText(
                translationsController.translate("label.preferencesAplliedMines") + " " + gameModel.getNumOfMines() + "\n" +
                        translationsController.translate("label.preferencesAplliedLanguage") + " " +
                        translationsController.translate("label.language") + "\n" +
                        translationsController.translate("label.firstMove")
        );
    }


    @Override
    public void updateFlags(int row, int col) {
        this.userFeedbackBar.setText(
                translationsController.translate("label.minesLeft") + " " + gameModel.getNumOfMines()
        );

    }

    @Override
    public void updateReveal() {

    }

    @Override
    public void winGame() {
        this.userFeedbackBar.setText(translationsController.translate("label.win"));
    }

    @Override
    public void updateForSavedGame() {
        this.userFeedbackBar.setText(translationsController.translate("label.savedGame"));
    }

    @Override
    public void loseGame() {
        this.userFeedbackBar.setText(translationsController.translate("label.gameOver"));
    }


}
