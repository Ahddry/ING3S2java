package com.example.netflexe.Model;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;

import java.util.ArrayList;

public class Stats {

    Cinema cinema;

    public Stats(Cinema cinema)
    {
        this.cinema = cinema;
    }

    public ObservableList<PieChart.Data> getPromotionUtilisees()
    {
        ArrayList<String> listPromoCinema = cinema.getPromoUtilisees();
        ArrayList<String> listPromo = new ArrayList<String>();
        listPromo.add("Pas de promotion");
        listPromo.add("Promotion jeune");
        listPromo.add("Promotion senior");

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();

        for(int i = 0; i< listPromo.size(); i++) {
            int count = 0;
            for (int j = 0; j <  4/*listPromoCinema.size()*/; j++) {
                //if (listPromo.get(i).equals(listPromoCinema.get(j))) {
                    count++;
                //}

            }
            pieChartData.add(new PieChart.Data(listPromo.get(i), 10));

        }

        return pieChartData;
    }
}
