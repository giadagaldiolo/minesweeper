package ch.supsi.minesweeper.frontend.model;

import java.nio.file.Path;

public interface IGamePersistence {
    void save();
    void saveAs(String filename , Path path);
    boolean open(Path path, String fileName);
}