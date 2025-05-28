package ch.supsi.minesweeper.backend.application;

import ch.supsi.minesweeper.backend.business.GameLogic;

public class GameApplication {
    private static GameApplication instance;
    private GameLogic gameLogic;

    public static GameApplication getInstance() {
        if (instance == null) {
            instance = new GameApplication();
        }
        return instance;
    }
}
