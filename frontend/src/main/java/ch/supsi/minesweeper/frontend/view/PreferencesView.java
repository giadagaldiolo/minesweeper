package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.frontend.controller.PropertiesController;
import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PreferencesView {
    private final Stage dialogStage;
    private final PropertiesController preferencesController;
    private final TranslationsController translationsController;

    public PreferencesView(Stage owner, PropertiesController controller) {
        this.preferencesController = controller;
        this.translationsController = TranslationsController.getInstance();

        dialogStage = new Stage();
        dialogStage.setTitle(translationsController.translate("label.editPreferences"));
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Selettore lingua
        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("en-US", "it-CH");
        languageComboBox.setValue(preferencesController.getProperty("language"));

        // Slider numero di bombe
        //todo dovrebbe andare da 1 a (size*size)-1
        Slider bombSlider = new Slider(1, 80, Integer.parseInt(preferencesController.getProperty("numMines")));
        bombSlider.setShowTickLabels(true);
        bombSlider.setShowTickMarks(true);
        bombSlider.setMajorTickUnit(10);
        bombSlider.setMinorTickCount(1);
        bombSlider.setSnapToTicks(true);

        Label bombLabel = new Label(translationsController.translate("label.numberOfMines") + " " + (int) bombSlider.getValue());
        bombSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                bombLabel.setText(translationsController.translate("label.numberOfMines") + " " + newVal.intValue()));

        // Pulsanti
        Button saveButton = new Button(translationsController.translate("label.save"));
        saveButton.setOnAction(e -> {
            preferencesController.savePropertyToFile("language", languageComboBox.getValue());
            preferencesController.savePropertyToFile("numMines", String.valueOf((int) bombSlider.getValue()));
            dialogStage.close();
        });

        Button cancelButton = new Button(translationsController.translate("label.cancel"));
        cancelButton.setOnAction(e -> dialogStage.close());

        HBox buttons = new HBox(10, saveButton, cancelButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(
                new Label(translationsController.translate("label.languageLabel")), languageComboBox,
                bombLabel, bombSlider,
                buttons
        );

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
    }

    public void show() {
        dialogStage.showAndWait();
    }
}