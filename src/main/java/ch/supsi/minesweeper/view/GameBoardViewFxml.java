package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.GameModel;
import ch.supsi.minesweeper.model.PlayerEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameBoardViewFxml implements ControlledFxView {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;

    private Button[][] buttons = new Button[9][9];

    private static GameBoardViewFxml myself;

    private PlayerEventHandler playerEventHandler;

    private GameModel gameModel;

    @FXML
    private GridPane containerPane;

    @FXML
    private Button cell00;

    @FXML
    private Button cell01;

    @FXML
    private Button cell02;

    @FXML
    private Button cell03;

    @FXML
    private Button cell04;

    @FXML
    private Button cell05;

    @FXML
    private Button cell06;

    @FXML
    private Button cell07;

    @FXML
    private Button cell08;

    @FXML
    private Button cell10;

    @FXML
    private Button cell11;

    @FXML
    private Button cell12;

    @FXML
    private Button cell13;

    @FXML
    private Button cell14;

    @FXML
    private Button cell15;

    @FXML
    private Button cell16;

    @FXML
    private Button cell17;

    @FXML
    private Button cell18;

    @FXML
    private Button cell20;

    @FXML
    private Button cell21;

    @FXML
    private Button cell22;

    @FXML
    private Button cell23;

    @FXML
    private Button cell24;

    @FXML
    private Button cell25;

    @FXML
    private Button cell26;

    @FXML
    private Button cell27;

    @FXML
    private Button cell28;

    @FXML
    private Button cell30;

    @FXML
    private Button cell31;

    @FXML
    private Button cell32;

    @FXML
    private Button cell33;

    @FXML
    private Button cell34;

    @FXML
    private Button cell35;

    @FXML
    private Button cell36;

    @FXML
    private Button cell37;

    @FXML
    private Button cell38;

    @FXML
    private Button cell40;

    @FXML
    private Button cell41;

    @FXML
    private Button cell42;

    @FXML
    private Button cell43;

    @FXML
    private Button cell44;

    @FXML
    private Button cell45;

    @FXML
    private Button cell46;

    @FXML
    private Button cell47;

    @FXML
    private Button cell48;

    @FXML
    private Button cell50;

    @FXML
    private Button cell51;

    @FXML
    private Button cell52;

    @FXML
    private Button cell53;

    @FXML
    private Button cell54;

    @FXML
    private Button cell55;

    @FXML
    private Button cell56;

    @FXML
    private Button cell57;

    @FXML
    private Button cell58;

    @FXML
    private Button cell60;

    @FXML
    private Button cell61;

    @FXML
    private Button cell62;

    @FXML
    private Button cell63;

    @FXML
    private Button cell64;

    @FXML
    private Button cell65;

    @FXML
    private Button cell66;

    @FXML
    private Button cell67;

    @FXML
    private Button cell68;

    @FXML
    private Button cell70;

    @FXML
    private Button cell71;

    @FXML
    private Button cell72;

    @FXML
    private Button cell73;

    @FXML
    private Button cell74;

    @FXML
    private Button cell75;

    @FXML
    private Button cell76;

    @FXML
    private Button cell77;

    @FXML
    private Button cell78;

    @FXML
    private Button cell80;

    @FXML
    private Button cell81;

    @FXML
    private Button cell82;

    @FXML
    private Button cell83;

    @FXML
    private Button cell84;

    @FXML
    private Button cell85;

    @FXML
    private Button cell86;

    @FXML
    private Button cell87;

    @FXML
    private Button cell88;

    private GameBoardViewFxml() {}

    public static GameBoardViewFxml getInstance() {
        if (myself == null) {
            myself = new GameBoardViewFxml();

            try {
                URL fxmlUrl = GameBoardViewFxml.class.getResource("/gameboard.fxml");
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
    public void initialize(EventHandler eventHandler, AbstractModel model) {
        createButtonsMatrix();
        this.createBehaviour();
        this.playerEventHandler = (PlayerEventHandler) eventHandler;
        this.gameModel = (GameModel) model;
    }

    private void createBehaviour() {
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++) {
                buttons[i][j].setOnAction(event-> this.playerEventHandler.move());
            }
        }
    }

    private void createButtonsMatrix() {
        buttons[0][0] = cell00;
        buttons[0][1] = cell01;
        buttons[0][2] = cell02;
        buttons[0][3] = cell03;
        buttons[0][4] = cell04;
        buttons[0][5] = cell05;
        buttons[0][6] = cell06;
        buttons[0][7] = cell07;
        buttons[0][8] = cell08;
        buttons[1][0] = cell10;
        buttons[1][1] = cell11;
        buttons[1][2] = cell12;
        buttons[1][3] = cell13;
        buttons[1][4] = cell14;
        buttons[1][5] = cell15;
        buttons[1][6] = cell16;
        buttons[1][7] = cell17;
        buttons[1][8] = cell18;
        buttons[2][0] = cell20;
        buttons[2][1] = cell21;
        buttons[2][2] = cell22;
        buttons[2][3] = cell23;
        buttons[2][4] = cell24;
        buttons[2][5] = cell25;
        buttons[2][6] = cell26;
        buttons[2][7] = cell27;
        buttons[2][8] = cell28;
        buttons[3][0] = cell30;
        buttons[3][1] = cell31;
        buttons[3][2] = cell32;
        buttons[3][3] = cell33;
        buttons[3][4] = cell34;
        buttons[3][5] = cell35;
        buttons[3][6] = cell36;
        buttons[3][7] = cell37;
        buttons[3][8] = cell38;
        buttons[4][0] = cell40;
        buttons[4][1] = cell41;
        buttons[4][2] = cell42;
        buttons[4][3] = cell43;
        buttons[4][4] = cell44;
        buttons[4][5] = cell45;
        buttons[4][6] = cell46;
        buttons[4][7] = cell47;
        buttons[4][8] = cell48;
        buttons[5][0] = cell50;
        buttons[5][1] = cell51;
        buttons[5][2] = cell52;
        buttons[5][3] = cell53;
        buttons[5][4] = cell54;
        buttons[5][5] = cell55;
        buttons[5][6] = cell56;
        buttons[5][7] = cell57;
        buttons[5][8] = cell58;
        buttons[6][0] = cell60;
        buttons[6][1] = cell61;
        buttons[6][2] = cell62;
        buttons[6][3] = cell63;
        buttons[6][4] = cell64;
        buttons[6][5] = cell65;
        buttons[6][6] = cell66;
        buttons[6][7] = cell67;
        buttons[6][8] = cell68;
        buttons[7][0] = cell70;
        buttons[7][1] = cell71;
        buttons[7][2] = cell72;
        buttons[7][3] = cell73;
        buttons[7][4] = cell74;
        buttons[7][5] = cell75;
        buttons[7][6] = cell76;
        buttons[7][7] = cell77;
        buttons[7][8] = cell78;
        buttons[8][0] = cell80;
        buttons[8][1] = cell81;
        buttons[8][2] = cell82;
        buttons[8][3] = cell83;
        buttons[8][4] = cell84;
        buttons[8][5] = cell85;
        buttons[8][6] = cell86;
        buttons[8][7] = cell87;
        buttons[8][8] = cell88;
    }

    @Override
    public Node getNode() {
        return this.containerPane;
    }

    @Override
    public void update() {
        // get your data from the model, if needed
        // then update this view here
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(this.getClass().getSimpleName() + " updated..." + dateFormat.format(date));
    }

}