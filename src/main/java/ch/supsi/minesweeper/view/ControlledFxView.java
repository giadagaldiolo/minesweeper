package ch.supsi.minesweeper.view;


import ch.supsi.minesweeper.controller.EventHandler;
import ch.supsi.minesweeper.model.AbstractModel;
import javafx.scene.Node;

public interface ControlledFxView extends ControlledView {

    Node getNode();
    void initialize(EventHandler eventHandler, AbstractModel model);

}
