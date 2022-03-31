package com.example.netflexe.Model;

import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.Objects;


public class ActorCollection {
    private ArrayList<Actor> collection = new ArrayList<Actor>();

    public ActorCollection() {
    }

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

    public void addActor(Actor acteur) {
        collection.add(acteur);
    }

    public int getSize() {
        return collection.size();
    }

    public String getName(int i) {
        return collection.get(i).getFirstName() + " " + collection.get(i).getLastName();
    }


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