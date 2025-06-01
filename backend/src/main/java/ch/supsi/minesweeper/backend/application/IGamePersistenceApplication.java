package ch.supsi.minesweeper.backend.application;

import java.nio.file.Path;

public interface IGamePersistenceApplication {
    void save();
    void saveAs(Path path);
    boolean open(Path path, String fileName);
}