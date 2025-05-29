package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.frontend.controller.TranslationsController;
import ch.supsi.minesweeper.frontend.model.AbstractModel;


public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model, TranslationsController translationsController);

}
