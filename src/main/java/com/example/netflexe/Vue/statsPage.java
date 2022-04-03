package com.example.netflexe.Vue;

import java.util.ArrayList;

import com.example.netflexe.Model.*;

import javafx.fxml.FXML;
import javafx.scene.chart.*;

public class statsPage {

    @FXML
    private PieChart promoPie;

    @FXML
    private BarChart<String,Number> barreFilm;


    /**
     * créé les diagrammes de stats
     * @param cinema cinema dont les stats seront affichées
     */
    public void init(Cinema cinema, ArrayList<Stats2> temp)
    {
        Stats stats = new Stats(cinema, temp);

        promoPie.getData().addAll(stats.getPromotionUtilisees());
        barreFilm.getData().addAll(stats.getBarreChart());

    }


}
