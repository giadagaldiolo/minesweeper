package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.frontend.controller.EventHandler;
import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import ch.supsi.minesweeper.frontend.model.AbstractModel;

import javafx.scene.Node;

public interface ControlledTranslatableFxView extends ControlledView{

    Node getNode();
    // Passiamo anche il controller per le traduzioni nella view
    void initialize(EventHandler eventHandler, AbstractModel model, TranslationsController translationsController);

}
