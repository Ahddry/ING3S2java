package com.example.netflexe.Model;

/**
 * classe Genre qui contient toutes les informations concernant un genre
 */
public class Genre {
    private String genre_nom;
    private int id_genre;
    private String lien_poster;
    public Genre()
    {

    }
    /**
     * Constructeur surchagé de Genre
     * @param genre_nom nom du genre dans la BDD
     * @param id_genre id du genre dans la BDD
     * @param lien_poster lien du poster en string
     */
    public Genre(String genre_nom, int id_genre, String lien_poster)
    {
        this.id_genre = id_genre;
        this.genre_nom = genre_nom;
        this.lien_poster = lien_poster;
    }
    /**
     * méthode qui retourne le poster du genre utilisé pour l'affichage de genreLiker pour les nouveau utilisateur
     * @return retourne le lien de l'affiche en string
     */
    public String get_poster()
    {
        return this.lien_poster;
    }
    /**
     * Méthode qui set le poster du genre
     * @param poster genre en string
     */
    public void set_poster(String poster)
    {
        this.lien_poster = poster;
    }
    /**
     * Méthode qui set l'id du genre 
     * @param genre genre de la BDD en int
     */
    public void set_id_genre(int genre)
    {
        this.id_genre = genre;
    }
    /**
     * Méthode qui permet de set le nom du genre
     * @param genre nom du genre dans la BDD en string
     */
    public void set_genre_nom(String genre)
    {
        this.genre_nom = genre;
    }
    /**
     * Méthode qui permet de recuperer l'id du genre depuis la BDD
     * @return l'id du genre depuis la BDD
     */
    public int get_id_genre()
    {
        return this.id_genre;
    }
    /**
     * Méthode qui permet de recuperer le nom du genre
     * @return nom du genre dans la BDD
     */
    public String get_nom_genre()
    {
        return this.genre_nom;
    }

}
