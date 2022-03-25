package com.example.netflexe.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.Image;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Seance {
    String filmJoue;
    Movie movie;
    LocalDate date;
    String heure;
    int salle;
    double prix;
    int id_seance;

    public Seance(String film,Movie movie,LocalDate newDate,String newHeure, int newSalle, double newPrix ,int id_seance)
    {
        this.filmJoue = film;
        this.movie = movie;
        this.date = newDate;
        this.heure = newHeure;
        this.salle = newSalle;
        this.prix = newPrix;
        this.id_seance = id_seance;
    }

    public String getName()
    {
        return filmJoue;
    }

    public String getHeure()
    {
        return heure;
    }

    public  LocalDate getDate(){ return date;}

    public double getPrix(){ return prix;}

    public Movie getMovie()
    {
        return movie;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    public void setImage()
    {
        movie.setImage(new Image(movie.getImageString()));
    }
    public int get_idSeance()
    {
        return this.id_seance;
    }
}


