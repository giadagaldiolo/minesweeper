package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesProvider;
import ch.supsi.minesweeper.model.PropertiesService;

public class PropertiesController implements PropertiesProvider {
    private static PropertiesController instance;

    private final PropertiesProvider provider;

    private PropertiesController() {
        this.provider = PropertiesService.getInstance();
    }

    public static PropertiesController getInstance() {
        if (instance == null) {
            instance = new PropertiesController();
        }
        return instance;
    }



    @Override
    public String getProperty(String key) {
        return provider.getProperty(key);
    }



    public void loadPreferences() {
        int numMinesPref = Integer.parseInt(instance.getProperty("numMines"));
        String language = instance.getProperty("language");
        GameController.getInstance().applyPreferences(numMinesPref, language);
    }
}
