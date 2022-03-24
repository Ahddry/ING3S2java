package com.example.netflexe.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Salle
{
    private int numero;
    private int id_bdd;
    private List<Seance> seances;
    private int nbPlaces;
    private ArrayList<String> filmP = new ArrayList<>();

    public Salle()
    {
        numero = 0;
        seances = new ArrayList<>();
        nbPlaces = 0;
    }

    public Salle(int id_bdd, int numero, int nbPlaces)
    {
        this.id_bdd = id_bdd;
        this.numero = numero;
        this.seances = new ArrayList<>();
        this.nbPlaces = nbPlaces;
    }
    public Salle(int id_bdd, int numero, List<Seance> seances, int nbPlaces)
    {
        this.id_bdd = id_bdd;
        this.numero = numero;
        this.seances = seances;
        this.nbPlaces = nbPlaces;
    }

    public int get_id_bdd()
    {
        return this.id_bdd;
    }
    public int getNumero()
    {
        return numero;
    }

    public void setNumero(int numero)
    {
        this.numero = numero;
    }

    public List<Seance> getSeances()
    {
        return seances;
    }

    public void addSeance(Seance seance)
    {

        seances.add(seance);
        filmP.add(seance.getName());
    }

    public boolean deleteSeance(String name)
    {
        try
        {
            seances.remove(getSeance(name));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public int getNbPlaces()
    {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces)
    {
        this.nbPlaces = nbPlaces;
    }

    public Image getImage(String name)
    {
        int resultat = 0;
        int i = 0;
        for (var seance : seances)
        {
            if (seance.getName() == name)
                resultat = i;
            i++;
        }
        return seances.get(resultat).getMovie().getImage();
    }

    public Seance getSeance(String name)
    {
        int res = 0;
        int i = 0;
        for (var elem: seances)
        {
            if (elem.getName() == name)
                res = i;
            i++;
        }
        return seances.get(res);
    }

    public ArrayList<String> getFilmP()
    {
        return filmP;
    }

}
