package ch.supsi.minesweeper.frontend.model;

import ch.supsi.minesweeper.frontend.controller.EventHandler;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    // add all the relevant missing behaviours
    // ...

}
