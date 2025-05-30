package ch.supsi.minesweeper.frontend.view;


import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpView {

    private final Stage dialogStage;

    public HelpView(Stage owner) {
        dialogStage = new Stage();
        dialogStage.setTitle("Guida");
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);


        TranslationsController translationsController = TranslationsController.getInstance();
        Label textLabel = new Label(translationsController.translate("label.helpText"));
        textLabel.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        VBox root = new VBox(scrollPane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 450, 400);
        dialogStage.setScene(scene);
    }

    public void show() {
        dialogStage.showAndWait();
    }
}
