package com.example.netflexe.Model;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class profil qui contient toutes les informations spécifique concernant un utilisateur
 */
public class Profil {

    MovieCollection filmLike = new MovieCollection();
    MovieCollection filmDejaVu = new MovieCollection();
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

    /**
     * Methode permettant d'ajouter un genre liker au profile de l'utilisateur
     * @param genre genre aimé par l'utilisateur
     */
    public void add_genre(String genre)
    {
        this.genreLiker.add(genre);
    }

    /**
     * Méthode permettant de récuperer le genre pour un index donnée
     * @param i index du teableau
     * @return le genre en string
     */
    public String get_genre(int i)
    {
        return this.genreLiker.get(i);
    }
    /**
     * méthode récuperer l'id de l'utilisateur dans la base de donnée
     * @return
     */
    public int get_id()
    {
        return this.id_user;
    }
    /**
     * méthode qui set l'id de l'utilisateur
     * @param id id de l'utilisateur dans la BDD
     */
    public void set_id(int id)
    {
        this.id_user = id;
    }

    /**
     * Méthode qui recupere la PP de l'utilisateur en inputstream
     * @return return la pp de l'utilisateur en binaire
     */
    public InputStream get_pp()
    {
        return this.pp;
    }
    /**
     * méthode qui permet de set la pp de l'utilisateur le paramètres est en binaire
     * @param pp photo de profil en binaire
     */
    public void set_pp(InputStream pp)
    {
        this.pp = pp;
    }

    /**
     * méthode qui recupere le prenom de l'utilisateur
     * @return retourne le prenom de l'utilisateur en string
     */
    public String get_prenom()
    {
        return this.prenom;
    }
    /**
     * méthode qui set le prenom de l'utilisateur
     * @param prenom prenom de l'utilisateur
     */
    public void set_prenom(String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * Méthode qui recupere le nomd de l'utilisateur
     * @return
     */
    public String get_nom()
    {
        return this.nom;
    }
    /**
     * méthode qui set le nom de l'utilisateur
     * @param nom nom de l'utilisateur 
     */
    public void set_nom(String nom)
    {
        this.nom = nom;
    }

    /**
     * recupere le mail de l'utilisateur
     * @return retourne le mail de l'utilisateur en string
     */
    public String get_mail()
    {
        return this.mail;
    }
    /**
     * méthode qui set l'email de l'utilisateur 
     * @param mail email de l'utilisateur en string
     */
    public void set_mail(String mail)
    {
        this.mail = mail;
    }

    /**
     * recupere le sexe de l'utilisateur
     * @return sexe de l'utilisateur en string
     */
    public String get_genre()
    {
        return this.genre;
    }
    /**
     * Méthode qui permet de set le genre de l'utilisateur
     * @param genre sexe de l'utilisateur
     */
    public void set_genre(String genre)
    {
        this.genre = genre;
    }
    
    /**
     * Méthode qui recupere l'age de l'utilisateur
     * @return recupere l'age de l'utilisateur en string
     */
    public String get_age()
    {
        return this.age;
    }
    /**
     * méthode qui set l'age de l'utilisateur dans le profil
     * @param age age en string de l'utilisateur
     */
    public void set_age(String age)
    {
        this.age = age;
    }
    /**
     * permet de faire charger les images des collections de l'utilisateur
     */
    public void set_image()
    {
        this.filmLike.setImage();
        this.filmDejaVu.setImage();
    }

    /**
     * méthode qui permet d'ajouter un film a la collection de film liker
     * @param movie filma a ajouter dans la collection
     */
    public void ajouterLike(Movie movie)
    {
        
        if(!filmLike.checkBoolean(movie.getTitle()))
        {
            filmLike.addMovie(movie);
        }

    }

    /**
     * méthode qui permet d'ajouter un film a la collection de film deja vu
     * @param movie film a ajouter dans la collection
     */
    public void ajouterDejaVu (Movie movie)
    {

        if(!filmDejaVu.checkBoolean(movie.getTitle()))
        {
            filmDejaVu.addMovie(movie);
        }

    }

    public boolean isAdmin()
    {
        return admin;
    }

    /**
     * Méthode qui ajoute une reservation pour un utilisateur
     * @param reservation reservation a ajouter a la collection de reservation de l'utilisateur
     */
    public void ajouterReservation(Reservation reservation)
    {
        mesReservations.add(reservation);
    }

    /**
     * Méthode qui recupere le cinema de l'utilisateur dans le cas ou celui ci est admnistrateur
     * @return
     */
    public Cinema getCinema()
    {
        return cinema;
    }

    /**
     * méthode qui permet de set le cinema d'un utilisateur
     * @param cinema cinéma a set
     */
    public void setCinema(Cinema cinema)
    {
        this.cinema = cinema;
    }

    /**
     * Méthode qui recupere la collection de film liké par l'utilisateur
     * @return
     */
    public MovieCollection getFilmLike()
    {
        return filmLike;
    }
    /**
     * méthode qui recupere les films deja vu par l'utilisateur
     * @return retourne une collection de film pour les films deja vu
     */
    public MovieCollection getFilmDejaVu()
    {
        return filmDejaVu;
    }
    /**
     * Méthode qui retourne les filmreserver par l'utilisateur
     * @return une arraylist de reservation qui contient toutes les reservations de l'utilisateur
     */
    public ArrayList<Reservation> getFilmRes()
    {
        return mesReservations;
    }
}
