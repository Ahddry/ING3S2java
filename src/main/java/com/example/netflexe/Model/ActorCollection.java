package com.example.netflexe.Model;

import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.Objects;

/**
 * Représente une liste d'acteurs
 */
public class ActorCollection {
    private ArrayList<Actor> collection = new ArrayList<Actor>();

    public ActorCollection() {
    }

    /**
     * Retourne l'acteur qui correspond au nom entré en paramètre du getter
     * @param name  nom de l'acteur recherché
     * @return  l'acteur concerné
     */
    public Actor getActor(String name) {
        int resultat = 0;
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getName().equals(name)) {
                resultat = i;
            }

        }
        return collection.get(resultat);
    }

    public Actor getActor(int i) {
        return collection.get(i);
    }

    /**
     * méthode d'ajout d'un acteur dans la liste d'acteurs
     * @param acteur Acteur à ajouter
     */
    public void addActor(Actor acteur) {
        collection.add(acteur);
    }

    /**
     * Détermine le nombre d'acteurs dans la collection
     * @return la taille de la liste
     */
    public int getSize() {
        return collection.size();
    }

    /**
     * Retourne le nom complet de l'acteur situé à la position i dans la liste
     * @param i position de l'acteur dans la liste
     * @return le nom de l'acteur
     */
    public String getName(int i) {
        return collection.get(i).getFirstName() + " " + collection.get(i).getLastName();
    }

    /**
     * retourne l'adresse de l'image de l'acteur via son nom
     * @param name nom de l'acteur
     * @return URL de l'image
     */
    public Image getImage(String name)
    {
        int resultat = 0;
        for(int i = 0 ; i < collection.size(); i++)
        {
            if (collection.get(i).getName().equals(name))
            {
                resultat = i ;
            }

        }
        return collection.get(resultat).getImageActeur();
    }

    /**
     * Set les images des acteurs
     */
    public void setImage()
    {
        for(int i = 0 ; i < collection.size(); i++)
        {
            collection.get(i).setImageActeur(new Image(collection.get(i).getImageNameActeur()));
        }
    }

    public boolean checkBoolean(String name)
    {
        boolean resultat = false;
        for(int i = 0 ; i < collection.size(); i++)
        {

            if(Objects.equals(collection.get(i).getName(), name))
            {
                resultat = true;
            }
        }

        return resultat;
    }

    public boolean deleteActor(String name)
    {
        try
        {
            collection.remove(getActor(name));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public void erase()
    {
        collection.clear();
    }
}