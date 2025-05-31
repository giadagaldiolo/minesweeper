package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.frontend.controller.PropertiesController;
import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import ch.supsi.minesweeper.frontend.model.AbstractModel;
import ch.supsi.minesweeper.frontend.model.PropertiesModel;
import ch.supsi.minesweeper.frontend.model.TranslationsModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PreferencesView implements UncontrolledFxView {
    private static PreferencesView myself;
    private PropertiesModel preferencesModel;
    private TranslationsModel translationsModel;
    private VBox root;

    private PreferencesView() {
    }

    public static PreferencesView getInstance() {
        if (myself == null) {
            myself = new PreferencesView();
        }
        return myself;
    }

    public void show(Stage owner) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle(translationsModel.translate("label.editPreferences"));
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        VBox content = new VBox(10);
        content.setPadding(new Insets(15));

        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("en-US", "it-CH");
        languageComboBox.setValue(preferencesModel.getProperty("language"));

        Slider bombSlider = new Slider(1, 80, Integer.parseInt(preferencesModel.getProperty("numMines")));
        bombSlider.setShowTickLabels(true);
        bombSlider.setShowTickMarks(true);
        bombSlider.setMajorTickUnit(10);
        bombSlider.setMinorTickCount(1);
        bombSlider.setSnapToTicks(true);

        Label bombLabel = new Label(translationsModel.translate("label.numberOfMines") + " " + (int) bombSlider.getValue());
        bombSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                bombLabel.setText(translationsModel.translate("label.numberOfMines") + " " + newVal.intValue()));

        Button saveButton = new Button(translationsModel.translate("label.save"));
        saveButton.setOnAction(e -> {
            preferencesModel.savePropertyToFile("language", languageComboBox.getValue());
            preferencesModel.savePropertyToFile("numMines", String.valueOf((int) bombSlider.getValue()));
            dialogStage.close();
        });

        Button cancelButton = new Button(translationsModel.translate("label.cancel"));
        cancelButton.setOnAction(e -> dialogStage.close());

        HBox buttons = new HBox(10, saveButton, cancelButton);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        content.getChildren().addAll(
                new Label(translationsModel.translate("label.languageLabel")), languageComboBox,
                bombLabel, bombSlider,
                buttons
        );

        Scene scene = new Scene(content);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        this.root = content;
    }

    @Override
    public Node getNode() {
        return root;
    }

    @Override
    public void initialize(AbstractModel preferencesModel, AbstractModel translationsModel) {
        this.translationsModel = (TranslationsModel) translationsModel;
        this.preferencesModel = (PropertiesModel) preferencesModel;
    }

    @Override
    public void update() {
        // Per estensioni future
    }
}
