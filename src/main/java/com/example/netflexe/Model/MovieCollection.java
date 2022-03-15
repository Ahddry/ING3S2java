package com.example.netflexe.Model;

import javafx.scene.image.Image;
import java.time.LocalDate;


import java.util.ArrayList;
import java.util.Objects;

public class MovieCollection {
    private ArrayList<Movie> collection = new ArrayList<Movie>();

    public void MovieCollection(){}

    public Movie getMovie(String name)
    {
        int resultat = 0;
        for(int i = 0 ; i < collection.size(); i++)
        {
            if (collection.get(i).getTitle() == name)
            {
                resultat = i;
            }

        }
        return collection.get(resultat);
    }

    public Movie getMovie(int i)
    {
        return collection.get(i);
    }

    public void addMovie(Movie movie)
    {
        collection.add(movie);
    }

    public int getSize()
    {
        return collection.size();
    }

    public String getName(int i)
    {
        return collection.get(i).getTitle();
    }
    public LocalDate getDateDeSortie_LD(int i) { return collection.get(i).getDate_de_sortie_LD(); }
    public String getDateDeSortie_S(int i) { return collection.get(i).getDate_de_sortie_S();}
    public String getDuree(int i) { return collection.get(i).getDuree(); }
    public String getSynopsis(int i) { return collection.get(i).getSynopsis(); }
    public String getSlogan(int i) { return collection.get(i).getSlogan(); }

    public Image getImage(String name)
    {
        int resultat = 0;
        for(int i = 0 ; i < collection.size(); i++)
        {
            if (collection.get(i).getTitle() == name)
            {
                resultat = i ;
            }

        }
        return collection.get(resultat).getImage();
    }

    public void setImage()
    {
        for(int i = 0 ; i < collection.size(); i++)
        {
            collection.get(i).setImage(new Image(collection.get(i).getImageString()));
        }
    }

    public boolean checkBoolean(String name)
    {
        boolean resultat = false;
        for(int i = 0 ; i < collection.size(); i++)
        {

            if(Objects.equals(collection.get(i).getTitle(), name))
            {
                resultat = true;
            }
        }

        return resultat;
    }

    public boolean deleteMovie(String name)
    {
        try
        {
            collection.remove(getMovie(name));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }
}
