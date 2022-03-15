package com.example.netflexe.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Seance {
    String filmJoue;
    LocalDate date;
    String heure;
    int salle;
    int prix;

    public Seance(String film,LocalDate newDate,String newHeure, int newSalle, int newPrix )
    {
        this.filmJoue = film;
        this.date = newDate;
        this.heure = newHeure;
        this.salle = newSalle;
        this.prix = newPrix;
    }

    public String getName()
    {
        return filmJoue;
    }

    public String getHeure()
    {
        return heure;
    }


}


