package ch.supsi.minesweeper.backend.business.save_game;

import java.nio.file.Path;

public interface ISaveGame {
    String save(String fileName, Path filePath);

    void saveAs(Path path);
}