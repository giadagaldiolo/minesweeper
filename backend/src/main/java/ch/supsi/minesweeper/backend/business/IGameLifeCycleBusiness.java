package ch.supsi.minesweeper.backend.business;

import java.nio.file.Path;

public interface IGameLifeCycleBusiness {
    void newGame();
    void save();
    void saveAs(Path path);
    boolean open(Path path, String fileName);
}