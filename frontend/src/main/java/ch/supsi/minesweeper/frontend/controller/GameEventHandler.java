package ch.supsi.minesweeper.frontend.controller;

import javafx.stage.Stage;

public interface GameEventHandler extends EventHandler {

    void newGame();

    void save();

    void saveAs(Stage stage);

    void open(Stage stage);

    void quit();

    void editPreferences(Stage stage);

    void help(Stage stage, String label);
}
