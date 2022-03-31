package com.example.netflexe.Vue;

import com.example.netflexe.Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.chart.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class statsPage {

    @FXML
    private PieChart promoPie;

    @FXML
    private BarChart<String,Number> barreFilm;



    public void init(Cinema cinema)
    {
        Stats stats = new Stats(cinema);


        promoPie.getData().addAll(stats.getPromotionUtilisees());
        barreFilm.getData().addAll(stats.getBarreChart()) ;

    }


}
