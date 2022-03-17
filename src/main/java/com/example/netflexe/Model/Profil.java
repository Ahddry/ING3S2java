package com.example.netflexe.Model;

import java.io.InputStream;



public class Profil {

    MovieCollection filmLike = new MovieCollection();
    private InputStream pp;
    private String prenom;
    private String nom;
    private String mail;
    private String genre;
    private String age;
    private int id_user;

    public Profil()
    {

    }

    public Profil(int id_user, String prenom, String nom, String mail, String genre, String age, InputStream pp)
    {
        this.id_user = id_user;
        this.pp = pp;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.genre = genre;
        this.age = age;
    }
    public Profil(Profil a)
    {
        this.id_user = a.id_user;
        this.pp = a.pp;
        this.prenom = a.prenom;
        this.nom = a.nom;
        this.mail = a.mail;
        this.genre = a.genre;
        this.age = a.age;
    }

    public int get_id()
    {
        return this.id_user;
    }
    public void set_id(int id)
    {
        this.id_user = id;
    }

    public InputStream get_pp()
    {
        return this.pp;
    }
    public void set_pp(InputStream pp)
    {
        this.pp = pp;
    }

    public String get_prenom()
    {
        return this.prenom;
    }
    public void set_prenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String get_nom()
    {
        return this.nom;
    }
    public void set_nom(String nom)
    {
        this.nom = nom;
    }

    public String get_mail()
    {
        return this.mail;
    }
    public void set_mail(String mail)
    {
        this.mail = mail;
    }

    public String get_genre()
    {
        return this.genre;
    }
    public void set_genre(String genre)
    {
        this.genre = genre;
    }
    
    public String get_age()
    {
        return this.age;
    }
    public void set_age(String age)
    {
        this.age = age;
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
