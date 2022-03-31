package com.example.netflexe.Model;
import javafx.scene.image.Image;

import java.time.LocalDate;

/**
 * Représente une séance d'une salle de cinéma avec le film qu'elle joue et son nom, la date de la séance, l'heure de
 * projection, le numéro de la salle dans laquelle la séance est jouée, le prix de la séance et son id dans al base de données.
 */
public class Seance {
    String filmJoue;
    Movie movie;
    LocalDate date;
    String heure;
    int salle;
    double prix;
    int id_seance;

    /**
     * Création d'une séance d'après les paramètres spécifiés
     * @param film Nom du film joué
     * @param movie Film joué
     * @param newDate Date de la séance
     * @param newHeure Heure de la séance
     * @param newSalle Numéro de salle de la séance
     * @param newPrix Prix de la séance
     * @param id_seance Id de la séance dans la base de données
     */
    public Seance(String film,Movie movie,LocalDate newDate,String newHeure, int newSalle, double newPrix ,int id_seance)
    {
        this.filmJoue = film;
        this.movie = movie;
        this.date = newDate;
        this.heure = newHeure;
        this.salle = newSalle;
        this.prix = newPrix;
        this.id_seance = id_seance;
    }

    /**
     * Retourne le nom du film joué
     * @return nom du film joué
     */
    public String getName()
    {
        return filmJoue;
    }

    /**
     * Retourne l'heure de la séance
     * @return heure de la séance
     */
    public String getHeure()
    {
        return heure;
    }

    /**
     * Retourne la date de la séance
     * @return date de la séance
     */
    public  LocalDate getDate(){ return date;}

    /**
     * Retourne le prix de la séance
     * @return prix de la séance
     */
    public double getPrix(){ return prix;}

    /**
     * Retourne le film projeté
     * @return film projeté lors de la séance
     */
    public Movie getMovie()
    {
        return movie;
    }

    /**
     * Change le film projeté lors de la séance
     * @param movie Nouveau film
     */
    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    /**
     * Change l'image de la séance par celle du film projeté
     */
    public void setImage()
    {
        movie.setImage(new Image(movie.getImageString()));
    }

    /**
     * Retourne l'id de la séance dans la base de données
     * @return id de la séance dans la base de données
     */
    public int get_idSeance()
    {
        return this.id_seance;
    }
}


