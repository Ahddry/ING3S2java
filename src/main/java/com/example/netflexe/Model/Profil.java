package com.example.netflexe.Model;

import java.io.InputStream;
import java.util.ArrayList;


public class Profil {

    MovieCollection filmLike = new MovieCollection();
    private InputStream pp;
    private String prenom;
    private String nom;
    private String mail;
    private String genre;
    private String age;
    private int id_user;
    private boolean admin;
    private Cinema cinema;
    private ArrayList<Reservation> mesReservations = new ArrayList<Reservation>();
    private ArrayList<String> genreLiker = new ArrayList<String>();

    public Profil()
    {

    }

    public Profil(int id_user, String prenom, String nom, String mail, String genre, String age, InputStream pp, boolean admin, Cinema cinema)
    {
        this.id_user = id_user;
        this.pp = pp;
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
        this.genre = genre;
        this.age = age;
        this.admin = admin;
        this.cinema = cinema;
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
        this.admin = a.admin;
        this.cinema = a.cinema;
    }

    public void add_genre(String genre)
    {
        this.genreLiker.add(genre);
    }
    public String get_genre(int i)
    {
        return this.genreLiker.get(i);
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
    public void set_image()
    {
        this.filmLike.setImage();
    }

    public void ajouterLike(Movie movie)
    {
        
        if(!filmLike.checkBoolean(movie.getTitle()))
        {
            filmLike.addMovie(movie);
        }

    }

    public void ajouterReservation(Reservation reservation)
    {
        mesReservations.add(reservation);
    }

    public MovieCollection getFilmLike()
    {
        return filmLike;
    }
    public ArrayList<Reservation> getFilmRes()
    {
        return mesReservations;
    }
}
