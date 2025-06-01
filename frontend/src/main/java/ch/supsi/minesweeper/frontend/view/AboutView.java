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

public class AboutView implements UncontrolledFxView{

    private static AboutView myself;
    private Stage dialogStage;
    private VBox root;
    private String labelKey;
    private TranslationsModel translationsModel;
    private ManifestInfoReader manifestInfo;

    private AboutView() {
        manifestInfo = new ManifestInfoReader();
    }

    public static AboutView getInstance() {
        if (myself == null) {
            myself = new AboutView();
        }
        return myself;
    }

    public void show(Stage owner, String labelKey) {
        this.labelKey = labelKey;

        dialogStage = new Stage();
        dialogStage.setTitle("label.about");
        dialogStage.initOwner(owner);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);


        String raw = translationsModel.translate(labelKey);

        String dev1 = "";
        String dev2 = "";
        String dev3 = "";
        if (manifestInfo.getDevelopers().size() >= 1) dev1 = manifestInfo.getDevelopers().get(0);
        if (manifestInfo.getDevelopers().size() >= 2) dev2 = manifestInfo.getDevelopers().get(1);
        if (manifestInfo.getDevelopers().size() >= 3) dev3 = manifestInfo.getDevelopers().get(2);

        String formatted = MessageFormat.format(
                raw,
                manifestInfo.getVersion(),
                manifestInfo.getData(),
                dev1,
                dev2,
                dev3
        );

        Label textLabel = new Label(formatted);
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
