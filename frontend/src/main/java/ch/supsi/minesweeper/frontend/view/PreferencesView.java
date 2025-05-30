package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.frontend.controller.PropertiesController;
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

    public PreferencesView(Stage owner, PropertiesController controller) {
        this.preferencesController = controller;

        dialogStage = new Stage();
        dialogStage.setTitle("Modifica Preferenze");
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Selettore lingua
        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("en", "it");
        languageComboBox.setValue(preferencesController.getProperty("language"));

        // Slider numero di bombe
        //todo dovrebbe andare da 1 a (size*size)-1
        Slider bombSlider = new Slider(1, 80, Double.parseDouble(preferencesController.getProperty("numMines")));
        bombSlider.setShowTickLabels(true);
        bombSlider.setShowTickMarks(true);
        bombSlider.setMajorTickUnit(10);
        bombSlider.setMinorTickCount(1);
        bombSlider.setSnapToTicks(true);

        Label bombLabel = new Label("Numero di bombe: " + (int) bombSlider.getValue());
        bombSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                bombLabel.setText("Numero di bombe: " + newVal.intValue()));

        // Pulsanti
        Button saveButton = new Button("Salva");
        saveButton.setOnAction(e -> {
            preferencesController.setProperty("language", languageComboBox.getValue());
            preferencesController.setProperty("numMines",String.valueOf(bombSlider.getValue()));
            dialogStage.close();
        });

        Button cancelButton = new Button("Annulla");
        cancelButton.setOnAction(e -> dialogStage.close());

        HBox buttons = new HBox(10, saveButton, cancelButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(
                new Label("Lingua:"), languageComboBox,
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