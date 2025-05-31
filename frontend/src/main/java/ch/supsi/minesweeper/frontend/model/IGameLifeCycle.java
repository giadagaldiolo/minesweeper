package ch.supsi.minesweeper.frontend.model;

import java.nio.file.Path;

public interface IGameLifeCycle {
    void newGame();
    void save();
    void saveAs(Path path);
    boolean open(Path path, String fileName);
}