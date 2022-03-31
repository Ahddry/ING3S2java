package com.example.netflexe.Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public XYChart.Series getBarreChart(){

        ArrayList<String> listFilms = cinema.getFilmVendus();
        listFilms.add("Dune");
        listFilms.add("Dune");
        listFilms.add("Dune");
        listFilms.add("Dune");
        listFilms.add("Inception");
        listFilms.add("Inception");
        listFilms.add("Inception");
        listFilms.add("Batman");

        List<String> listDistinct = listFilms.stream().distinct().collect(Collectors.toList());



        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("Film");
        yAxis.setLabel("Places Vendues");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");
        for(int i=0; i <listDistinct.size();i++)
        {
            int count = 0;
            for(int j =0; j < listFilms.size(); j++)
            {
                if(listDistinct.get(i).equals(listFilms.get(j)))
                {
                    count ++;
                }
            }
            series1.getData().add(new XYChart.Data(listDistinct.get(i), count));

        }



        return series1;


    }



}
