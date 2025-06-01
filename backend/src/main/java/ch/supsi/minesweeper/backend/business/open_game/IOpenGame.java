package ch.supsi.minesweeper.backend.business.open_game;

import ch.supsi.minesweeper.backend.model.Grid;

import java.nio.file.Path;

public interface IOpenGame {
    Grid open(Path path);
}
