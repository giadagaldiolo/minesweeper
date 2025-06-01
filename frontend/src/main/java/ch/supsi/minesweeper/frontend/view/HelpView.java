package ch.supsi.minesweeper.frontend.view;


import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import ch.supsi.minesweeper.frontend.model.AbstractModel;
import ch.supsi.minesweeper.frontend.model.TranslationsModel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.MessageFormat;

public class HelpView implements UncontrolledFxView{

    private static HelpView myself;
    private Stage dialogStage;
    private VBox root;
    private String labelKey;
    private TranslationsModel translationsModel;

    private HelpView() {
    }

    public static HelpView getInstance() {
        if (myself == null) {
            myself = new HelpView();
        }
        return myself;
    }

    public void show(Stage owner, String labelKey) {
        this.labelKey = labelKey;

        dialogStage = new Stage();
        dialogStage.setTitle("label.help");
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);

        Label textLabel = new Label(translationsModel.translate(labelKey));
        textLabel.setWrapText(true);

        ScrollPane scrollPane = new ScrollPane(textLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        root = new VBox(scrollPane);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 450, 400);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
    }

    @Override
    public Node getNode() {
        return root;
    }

    @Override
    public void initialize(AbstractModel model, AbstractModel translationsModel) {
        this.translationsModel = (TranslationsModel) translationsModel;
    }

    @Override
    public void update() {

    }
}
