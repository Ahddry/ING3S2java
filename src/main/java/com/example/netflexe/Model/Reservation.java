package com.example.netflexe.Model;

public class Reservation {
    Movie movie;
    String horaire;
    String nomCinema;
    String date;
    int nb_place;
    double tarif;

    public Reservation(Movie movie, String horaire, String monCinema, String date, int nb_place)
    {
        this.movie = movie;
        this.horaire = horaire;
        this.nomCinema = monCinema;
        this.date = date;
        this.nb_place = nb_place;
    }
    public Movie getMovie(){return movie;}

    public String getHoraire(){return horaire;}

    public String getNomCinema(){return nomCinema;}
    public String getDate(){return date;}
    public int getPLace()
    {
        return this.nb_place;
    }
}
