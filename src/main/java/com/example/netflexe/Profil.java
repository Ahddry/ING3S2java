package com.example.netflexe;

public class Profil {

    MovieCollection filmLike = new MovieCollection();

    public Profil()
    {

    }

    public void ajouterLike(Movie movie)
    {
        filmLike.addMovie(movie);
    }

    public MovieCollection getFilmLike()
    {
        return filmLike;
    }
}
