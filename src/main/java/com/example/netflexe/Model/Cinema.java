package com.example.netflexe.Model;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cinema {

    private String nom;
    private MovieCollection filmP = new MovieCollection();
    private Image image = null;
    private String imageString = "";
    private ArrayList<Seance> seances = new ArrayList<Seance>();

    public Cinema()
    {

    }

    public Cinema(String nom,String image)
    {
        this.nom = nom;
        this.imageString = image;
    }

    public boolean checkMovie(String name)
    {
        return filmP.checkBoolean(name);
    }



    public void ajoutFilm(Movie movie)
    {
        filmP.addMovie(movie);
    }

    public void setImage()
    {
        image = new Image(imageString);

    }

    public Image getImage()
    {
        return image;
    }

    public String getImageString()
    {
        return imageString;
    }

    public String getName()
    {
        return this.nom;
    }

    public MovieCollection getFilmP()
    {
        return filmP;
    }

    public void ajouterSeance(String movieTitle, String date,String heure,int salle, int prix)
    {
        seances.add(new Seance(movieTitle,LocalDate.parse(date),heure,salle,prix));
    }

    public void setFilmP(MovieCollection filmP)
    {
        this.filmP = filmP;
    }

    public ArrayList<Seance> getAllSeances(){return seances;}
}
