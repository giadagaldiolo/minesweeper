package ch.supsi.minesweeper.model;

import ch.supsi.minesweeper.controller.EventHandler;

public interface PlayerEventHandler extends EventHandler {

    void move();
    void flag();

    // add all the relevant missing behaviours
    // ...

}
