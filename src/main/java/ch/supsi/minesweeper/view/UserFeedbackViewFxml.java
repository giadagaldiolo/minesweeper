package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.GameModel;
import ch.supsi.minesweeper.model.TranslationsInterface;
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
    private TranslationsInterface translationsController;

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
    public void initialize(AbstractModel model, TranslationsInterface translationsController) {
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
                translationsController.translate("label.preferencesAplliedMines") + " " + gameModel.getNumOfFlags() + "\n" +
                        translationsController.translate("label.preferencesAplliedLanguage") + " " +
                        translationsController.translate("label.language") + "\n" +
                        translationsController.translate("label.firstMove")
        );
    }


    @Override
    public void updateFlags(int row, int col) {
        this.userFeedbackBar.setText(
                translationsController.translate("label.minesLeft") + " " + gameModel.getNumOfFlags()
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
    public void loseGame() {
        this.userFeedbackBar.setText(translationsController.translate("label.gameOver"));
    }


}
