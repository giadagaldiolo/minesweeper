package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.model.PropertiesProvider;
import ch.supsi.minesweeper.model.PropertiesService;
import ch.supsi.minesweeper.model.TranslationsInterface;
import ch.supsi.minesweeper.model.TranslationsModel;
import ch.supsi.minesweeper.view.DataView;
import ch.supsi.minesweeper.view.MenuBarViewFxml;

import java.util.List;

public class TranslationsController {
    private static TranslationsController instance;
    private final TranslationsInterface model;
    private final PropertiesProvider preferences;
    private List<DataView> views;

    private TranslationsController() {
        this.model = TranslationsModel.getInstance();
        this.preferences = PropertiesService.getInstance();
    }

    public static TranslationsController getInstance() {
        if (instance == null) {
            instance = new TranslationsController();
        }
        return instance;
    }
    public void initialize(List<DataView> views){
        this.views = views;
    }

    public String translate(String key) {
        return model.translate(key);
    }

    public void changeLanguage(String languageTag) {
        model.changeLanguage(languageTag);
        
        for (DataView view : views) {
            if(view instanceof MenuBarViewFxml) {
                ((MenuBarViewFxml) view).translateText();
            }
        }
    }

}
