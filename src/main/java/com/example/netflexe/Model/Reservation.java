package com.example.netflexe.Model;

public class Reservation {
    Movie movie;
    String horaire;
    String nomCinema;
    String date;
    int nb_place;
    double tarif;

    /**
     * contructeur de réservations
     * @param movie objet film qui est réservé
     * @param horaire heure  de la réservation en string
     * @param monCinema nom du cinéma
     * @param date Local date à laqielle la réservation est
     * @param nb_place Nombre de place réservées
     */
    public Reservation(Movie movie, String horaire, String monCinema, String date, int nb_place)
    {
        this.movie = movie;
        this.horaire = horaire;
        this.nomCinema = monCinema;
        this.date = date;
        this.nb_place = nb_place;
    }

    /**
     * on return le film associé à la réservation
     * @return
     */
    public Movie getMovie(){return movie;}

    public String getHoraire(){return horaire;}

    public String getNomCinema(){return nomCinema;}
    public String getDate(){return date;}

    /**
     * on donne le nombre de place associés à une réservatio,
     * @return
     */
    public int getPLace()
    {
        return this.nb_place;
    }
}
