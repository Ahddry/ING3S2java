package com.example.netflexe.Model;

public class Genre {
    String genre_nom;
    int id_genre;
    public Genre()
    {

    }
    public Genre(String genre_nom, int id_genre)
    {
        this.id_genre = id_genre;
        this.genre_nom = genre_nom;
    }
    public void set_id_genre(int genre)
    {
        this.id_genre = genre;
    }
    public void set_genre_nom(String genre)
    {
        this.genre_nom = genre;
    }
    public int get_id_genre()
    {
        return this.id_genre;
    }
    public String get_nom_genre()
    {
        return this.genre_nom;
    }

}
