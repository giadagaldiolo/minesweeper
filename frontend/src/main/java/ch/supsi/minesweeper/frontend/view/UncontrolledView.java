package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.backend.data_access.TranslationsInterface;
import ch.supsi.minesweeper.frontend.model.AbstractModel;


public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model, TranslationsInterface translationsController);

}
