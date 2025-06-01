package ch.supsi.minesweeper.backend.service;

import java.nio.file.Path;

public interface IGamePersistence {
    void save();
    void saveAs(Path path);
    boolean open(Path path, String fileName);
}