package ch.supsi.minesweeper.frontend;

import ch.supsi.minesweeper.frontend.controller.*;
import ch.supsi.minesweeper.frontend.model.*;
import ch.supsi.minesweeper.frontend.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class MainFx extends Application {

    public static final String APP_TITLE = "mine sweeper";

    private final AbstractModel gameModel;
    private final AbstractModel propertiesModel;
    private final AbstractModel translationsModel;

    private final ControlledTranslatableFxView menuBarView;
    private final ControlledFxView gameBoardView;
    private final UncontrolledFxView userFeedbackView;
    private final PreferencesView preferencesView;
    private final HelpView helpView;
    private final AboutView aboutView;

    private final GameEventHandler gameEventHandler;
    private final PlayerEventHandler playerEventHandler;
    private final PropertiesController propertiesController;
    private final TranslationsController translationsController;

    public MainFx() {
        // MODELS
        this.gameModel = GameModel.getInstance();
        this.propertiesModel = PropertiesModel.getInstance();
        this.translationsModel = TranslationsModel.getInstance();

        // VIEWS
        this.menuBarView = MenuBarViewFxml.getInstance();
        this.gameBoardView = GameBoardViewFxml.getInstance();
        this.userFeedbackView = UserFeedbackViewFxml.getInstance();
        this.preferencesView = PreferencesView.getInstance();
        this.helpView = HelpView.getInstance();
        this.aboutView = AboutView.getInstance();

        // CONTROLLERS
        this.gameEventHandler = GameController.getInstance();
        this.playerEventHandler = GameController.getInstance();
        this.propertiesController = PropertiesController.getInstance();
        this.translationsController = TranslationsController.getInstance();

        // SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.gameEventHandler, this.gameModel, this.translationsModel);
        this.gameBoardView.initialize(this.playerEventHandler, this.gameModel);
        this.userFeedbackView.initialize(this.gameModel, this.translationsModel);
        this.preferencesView.initialize(this.propertiesModel, this.translationsModel);
        this.helpView.initialize(this.propertiesModel, this.translationsModel);
        this.aboutView.initialize(this.propertiesModel, this.translationsModel);
        GameController.getInstance().initialize(List.of(this.menuBarView, this.gameBoardView, this.userFeedbackView, this.preferencesView, this.helpView, this.aboutView));
    }

    @Override
    public void start(Stage primaryStage) {
        gameEventHandler.setPrimaryStage(primaryStage);
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    gameEventHandler.quit();
                }
        );

        // SCAFFOLDING OF MAIN PANE
        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setTop(this.menuBarView.getNode());
        mainBorderPane.setCenter(this.gameBoardView.getNode());
        mainBorderPane.setBottom(this.userFeedbackView.getNode());

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // PRIMARY STAGE
        primaryStage.setTitle(MainFx.APP_TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.toFront();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}