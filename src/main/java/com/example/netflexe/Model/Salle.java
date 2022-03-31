package com.example.netflexe.Model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Représente une salle de cinéma avec un numéro, des séances, un id pour la base de données et un nombre de places.
 */
public class Salle
{
    private final int numero;
    private int id_bdd;
    private final List<Seance> seances;
    private int nbPlaces;
    private final ArrayList<String> filmP = new ArrayList<>();

    /**
     * Création d'une salle vide avec pour numéro 0 et 0 places
     */
    public Salle()
    {
        numero = 0;
        seances = new ArrayList<>();
        nbPlaces = 0;
    }

    /**
     * Création d'une salle avec les paramètres spécifiés
     * @param id_bdd Id de la salle dans la base de données
     * @param numero Numéro de la salle de cinéma
     * @param nbPlaces Nombre de places dans la salle de cinéma
     */
    public Salle(int id_bdd, int numero, int nbPlaces)
    {
        this.id_bdd = id_bdd;
        this.numero = numero;
        this.seances = new ArrayList<>();
        this.nbPlaces = nbPlaces;
    }

    /**
     * Création d'une salle avec les paramètres spécifiés
     * @param id_bdd Id de la salle dans la base de données
     * @param numero Numéro de la salle de cinéma
     * @param seances Liste des séances de la salle
     * @param nbPlaces Nombre de places dans la salle de cinéma
     */
    public Salle(int id_bdd, int numero, List<Seance> seances, int nbPlaces)
    {
        this.id_bdd = id_bdd;
        this.numero = numero;
        this.seances = seances;
        this.nbPlaces = nbPlaces;
    }

    /**
     * Retourne l'id de base de données de la salle
     * @return l'id de base de données de la salle
     */
    public int get_id_bdd()
    {
        return this.id_bdd;
    }

    /**
     * Retourne le numéro de la salle
     * @return numéro de la salle
     */
    public int getNumero()
    {
        return numero;
    }

    /**
     * Retourne l'ensemble des séances de la salle
     * @return Liste des séances de la salle
     */
    public List<Seance> getSeances()
    {
        return seances;
    }

    /**
     * Ajoute une séance à la salle
     * @param seance Séance à ajouter
     */
    public void addSeance(Seance seance)
    {

        seances.add(seance);
        filmP.add(seance.getName());
    }

    /**
     * Supprime une séance da la salle et retourne un booléen attestant de la suppression ou non de la salle
     * @param name Nom de la séance à supprimer
     * @return True si la salle a été supprimée et False sinon.
     */
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

    /**
     * Retourne le nombre de places dans la salle
     * @return nombre de places dans la salle
     */
    public int getNbPlaces()
    {
        return nbPlaces;
    }

    /**
     * Change le nombre de places dans la salle par al valeur spécifiée en paramètres
     * @param nbPlaces Nouveau nombre de places de la salle
     */
    public void setNbPlaces(int nbPlaces)
    {
        this.nbPlaces = nbPlaces;
    }

    /**
     * Retourne l'image de la séance dont le nom est spécifié en paramètres
     * @param name Nom de la séance dont il faut retourner l'image
     * @return Image de la séance
     */
    public Image getImage(String name)
    {
        int resultat = 0;
        int i = 0;
        for (var seance : seances)
        {
            if (Objects.equals(seance.getName(), name))
                resultat = i;
            i++;
        }
        return seances.get(resultat).getMovie().getImage();
    }

    /**
     * Retourne une séance dont le nom correspond à celui placé en paramètres
     * @param name Nom de la séance recherchée
     * @return Séance recherchée.
     */
    public Seance getSeance(String name)
    {
        int res = 0;
        int i = 0;
        for (var elem: seances)
        {
            if (Objects.equals(elem.getName(), name))
                res = i;
            i++;
        }
        return seances.get(res);
    }

    /**
     * Retourne la liste des noms des films joués
     * @return Liste des noms des films joués
     */
    public ArrayList<String> getFilmP()
    {
        return filmP;
    }

}
