package com.example.netflexe.Model;

public class Stats2 {
    private String nom_film;
    private int nombreBillet;

    public Stats2(String nom_film, int nombreBillet)
    {
        this.nom_film = nom_film;
        this.nombreBillet = nombreBillet;
    }
    public String get_nom()
    {
        return this.nom_film;
    }
    public int get_nombreBillet()
    {
        return this.nombreBillet;
    }
}
