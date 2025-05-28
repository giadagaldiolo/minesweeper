package ch.supsi.minesweeper.frontend.view;

import ch.supsi.minesweeper.frontend.model.AbstractModel;
import ch.supsi.minesweeper.frontend.model.TranslationsInterface;

public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model, TranslationsInterface translationsController);

}
