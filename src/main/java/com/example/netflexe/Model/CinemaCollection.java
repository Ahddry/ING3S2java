package com.example.netflexe.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class CinemaCollection {
    private ArrayList<Cinema> collection = new ArrayList<Cinema>();

    public Image getImage(String name)
    {
        int resultat = 0;
        for(int i = 0 ; i < collection.size(); i++)
        {
            if (collection.get(i).getName() == name)
            {
                resultat = i ;
            }

        }
        return collection.get(resultat).getImage();
    }

    public Cinema getCinema(int i)
    {
        return collection.get(i);
    }

    public void setImage()
    {
        for(int i = 0 ; i < collection.size(); i++)
        {
            collection.get(i).setImage();
        }
    }

    public void addMovie(Movie movie,String cinemaName)
    {
        for(int i = 0 ; i < collection.size(); i++)
        {
            if (collection.get(i).getName() == cinemaName)
            {
                collection.get(i).ajoutFilm(movie); ;
            }

        }
    }

    public void addCinema(Cinema cinema)
    {
        collection.add(cinema);
    }

    public String getName(int i)
    {
        return collection.get(i).getName();
    }

    public int getSize()
    {
        return collection.size();
    }

    public Cinema getCinema(String cinemaName)
    {
        Cinema result = new Cinema();
        for(int i = 0 ; i < collection.size(); i++)
        {
            if (collection.get(i).getName() == cinemaName)
            {
                result = collection.get(i) ;
            }
        }
        return result;
    }
}
