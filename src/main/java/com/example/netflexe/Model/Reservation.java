package com.example.netflexe.Model;

public class Reservation {
    Movie movie;
    String horaire;
    String nomCinema;
    String date;

    public Reservation(Movie movie, String horaire, String monCinema, String date)
    {
        this.movie = movie;
        this.horaire = horaire;
        this.nomCinema = monCinema;
        this.date = date;
    }
    public Movie getMovie(){return movie;}

    public String getHoraire(){return horaire;}

    public String getNomCinema(){return nomCinema;}
    public String getDate(){return date;}

}
