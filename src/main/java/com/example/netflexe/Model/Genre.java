package com.example.netflexe.Model;

public class Genre {
    String genre_nom;
    int id_genre;
    String lien_poster;
    public Genre()
    {

    }
    public Genre(String genre_nom, int id_genre, String lien_poster)
    {
        this.id_genre = id_genre;
        this.genre_nom = genre_nom;
        this.lien_poster = lien_poster;
    }
    public String get_poster()
    {
        return this.lien_poster;
    }
    public void set_poster(String poster)
    {
        this.lien_poster = poster;
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
