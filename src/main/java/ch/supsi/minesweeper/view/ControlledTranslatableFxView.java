package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.controller.TranslationsController;
import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.TranslationsInterface;
import javafx.scene.Node;

public interface ControlledTranslatableFxView extends ControlledView{

    Node getNode();
    // Passiamo anche il controller per le traduzioni nella view
    void initialize(EventHandler eventHandler, AbstractModel model, TranslationsInterface translationsController);

}
