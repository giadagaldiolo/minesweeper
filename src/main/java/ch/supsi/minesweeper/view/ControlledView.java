package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.controller.TranslationsController;
import ch.supsi.minesweeper.model.AbstractModel;
import ch.supsi.minesweeper.model.TranslationsBusinessInterface;

public interface ControlledView extends DataView {

    // Passiamo anche il controller per le traduzioni nelle view
    void initialize(EventHandler eventHandler, AbstractModel model, TranslationsController translationsController );

}
