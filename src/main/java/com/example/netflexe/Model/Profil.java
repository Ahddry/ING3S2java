package com.example.netflexe.Model;

public class Profil {

    MovieCollection filmLike = new MovieCollection();

    public Profil()
    {

    }

    public void ajouterLike(Movie movie)
    {
        if(!filmLike.checkBoolean(movie.getTitle()))
        {
            filmLike.addMovie(movie);
        }

    }

    public MovieCollection getFilmLike()
    {
        return filmLike;
    }
}
