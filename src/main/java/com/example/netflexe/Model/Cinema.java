package com.example.netflexe.Model;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private String nom;
    private MovieCollection filmP = new MovieCollection();
    private Image image = null;
    private String imageString = "";
    private List<Salle> salles = new ArrayList<>();
    private ArrayList<String> promoUtilisees = new ArrayList<String>();

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

    public void setFilmP(MovieCollection filmP)
    {
        this.filmP = filmP;
    }

    public List<Salle> getSalles()
    {
        return salles;
    }

    public void addSalles(Salle salle)
    {
        salles.add(salle);
    }

    public void addSeance(int salle, Seance seance)
    {
        for (var elem : salles)
        {
            if (elem.getNumero() == salle)
            {
                elem.addSeance(seance);
            }
        }
    }

    public void updateStatsPromo(String promo)
    {
        promoUtilisees.add(promo);
    }

    public ArrayList<String> getPromoUtilisees()
    {
        return promoUtilisees;
    }

    public void setSalles(List<Salle> salles)
    {
        this.salles = salles;
    }
}
