package com.example.netflexe.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Représente une liste de cinéma
 */
public class CinemaCollection {
    private ArrayList<Cinema> collection = new ArrayList<Cinema>();

    /**
     * Retourne l'image du cinéma dont le nom est spécifié en paramètres
     * @param name Nom du cinéma dont on cherche l'image
     * @return Image du cinéma
     */
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

    /**
     * Retourne le cinéma dont l'indice de liste est spécifié
     * @param i Indice de la position du cinéma dans la liste
     * @return Cinéma recherché
     */
    public Cinema getCinema(int i)
    {
        return collection.get(i);
    }

    /**
     * Applique les images des cinémas
     */
    public void setImage()
    {
        for(int i = 0 ; i < collection.size(); i++)
        {
            collection.get(i).setImage();
        }
    }

    /**
     * Ajoute un film dans le cinéma dont le nom est précisé
     * @param movie Film à ajouter
     * @param cinemaName Nom du cinéma dans lequel ajouter le film
     */
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

    /**
     * Ajoute un cinéma dans la liste
     * @param cinema Cinéma à ajouter
     */
    public void addCinema(Cinema cinema)
    {
        collection.add(cinema);
    }

    /**
     * Retourne le nom du cinéma recherché
     * @param i Indice dans al liste du cinéma recherché
     * @return Nom du cinéma
     */
    public String getName(int i)
    {
        return collection.get(i).getName();
    }

    /**
     * Retourne la taille de la liste de cinémas
     * @return taille de la liste de cinémas
     */
    public int getSize()
    {
        return collection.size();
    }

    /**
     * Retourne le cinéma dont on spécifie le nom
     * @param cinemaName Nom du cinéma recherché
     * @return Cinéma recherché
     */
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
