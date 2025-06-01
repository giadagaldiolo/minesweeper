package ch.supsi.minesweeper.frontend.view;


import ch.supsi.minesweeper.frontend.controller.EventHandler;
import ch.supsi.minesweeper.frontend.model.AbstractModel;
import javafx.scene.Node;

public interface ControlledFxView extends ControlledView {

    Node getNode();
    void initialize(EventHandler eventHandler, AbstractModel model);

}
