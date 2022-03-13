package com.example.netflexe;

import javafx.scene.image.Image;

public class Cinema {

    private String nom;
    private MovieCollection filmP = new MovieCollection();
    private Image image = null;
    private String imageString = "";

    public Cinema()
    {

    }

    public Cinema(String nom,String image)
    {
        this.nom = nom;
        this.imageString = image;
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

    public String getName()
    {
        return this.nom;
    }


}
