package ch.supsi.minesweeper.frontend;

import ch.supsi.minesweeper.frontend.controller.GameController;
import ch.supsi.minesweeper.frontend.controller.PropertiesController;
import ch.supsi.minesweeper.frontend.controller.TranslationsController;
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
    private final ControlledTranslatableFxView menuBarView;
    private final ControlledFxView gameBoardView;
    private final UncontrolledFxView userFeedbackView;
    private final GameEventHandler gameEventHandler;
    private final PlayerEventHandler playerEventHandler;

    private final PropertiesController propertiesController;
    private final TranslationsInterface translationsInterface;

    public MainFx() {
        // GAME MODEL
        this.gameModel = GameModel.getInstance();

        // VIEWS
        this.menuBarView = MenuBarViewFxml.getInstance();
        this.gameBoardView = GameBoardViewFxml.getInstance();
        this.userFeedbackView = UserFeedbackViewFxml.getInstance();

        // CONTROLLERS
        this.gameEventHandler = GameController.getInstance();
        this.playerEventHandler = GameController.getInstance();
        this.propertiesController = PropertiesController.getInstance();
        this.translationsInterface = TranslationsController.getInstance();

        // SCAFFOLDING of M-V-C
        this.menuBarView.initialize(this.gameEventHandler, this.gameModel, this.translationsInterface);
        this.gameBoardView.initialize(this.playerEventHandler, this.gameModel);
        this.userFeedbackView.initialize(this.gameModel, this.translationsInterface);
        GameController.getInstance().initialize(List.of(this.menuBarView, this.gameBoardView, this.userFeedbackView));
        TranslationsController.getInstance().initialize(List.of(this.menuBarView, this.userFeedbackView));
    }

    @Override
    public void start(Stage primaryStage) {


        // handle the main window close request
        // in real life, this event should not be dealt with here!
        // it should actually be delegated to a suitable ExitController!
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    // quit the app
                    // replace this hard close
                    // by delegating the work to a suitable controller
                    primaryStage.close();
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
