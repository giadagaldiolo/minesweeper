package ch.supsi.minesweeper.view;

import ch.supsi.minesweeper.controller.TranslationsController;
import ch.supsi.minesweeper.model.AbstractModel;

public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model, TranslationsController translationsController);

}
