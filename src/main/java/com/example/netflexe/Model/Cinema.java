package com.example.netflexe.Model;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un cinéma, avec son Id dans la base de données, son nom, la liste de ses films, son image de profil, la
 * liste de ses salles, la liste de ses promos, la liste des promos utilisées lors des achats et la liste des films pour
 * lesquels il a réalisé des ventes.
 */
public class Cinema {

    private int id_cine;
    private String nom;
    private MovieCollection filmP = new MovieCollection();
    private Image image = null;
    private String imageString = "";
    private List<Salle> salles = new ArrayList<>();
    private final ArrayList<Promo> promos  = new ArrayList<>();
    private final ArrayList<String> promoUtilisees = new ArrayList<>();
    private final ArrayList<String> filmVendus = new ArrayList<>();

    /**
     * Création d'un cinéma vide.
     */
    public Cinema()
    {

    }

    /**
     * Création d'un cinéma d'après les paramètres spécifiés
     * @param id_cine Id du cinéma dans la base de données
     * @param nom Nom du cinéma
     * @param image Lien internet menant vers l'image
     */
    public Cinema(int id_cine,String nom,String image)
    {
        this.id_cine = id_cine;
        this.nom = nom;
        this.imageString = image;
    }

    /**
     * Retourne vrai si le film dont le nom est précisé en paramètre existe dans le cinéma
     * @param name Film recherché
     * @return True si le film est projeté dans ce cinéma et False sinon.
     */
    public boolean checkMovie(String name)
    {
        boolean result = false;
        for (Salle salle : salles)
        {
            ArrayList<String> filmProjetes = salle.getFilmP();
            for (String filmProjete : filmProjetes)
            {
                if (filmProjete.equals(name))
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Ajout d'un film dans le cinéma
     * @param movie filma à ajouter
     */
    public void ajoutFilm(Movie movie)
    {
        filmP.addMovie(movie);
    }

    /**
     * Applique l'image d'après le lien donné
     */
    public void setImage()
    {
        if (imageString != null)
            image = new Image(imageString);
    }

    /**
     * Change l'id du cinéma dans la base de données
     * @param id_cine Nouvel id du cinéma dans la base de données
     */
    public void set_id_cine(int id_cine)
    {
        this.id_cine = id_cine;
    }

    /**
     * Retourne l'id du cinéma dans la base de données
     * @return id du cinéma dans la base de données
     */
    public int get_id_cine()
    {
        return this.id_cine;
    }

    /**
     * Retourne l'image du film
     * @return image du film
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Retourne le lien de l'image du film
     * @return lien de l'image du film
     */
    public String getImageString()
    {
        return imageString;
    }

    /**
     * Retourne le nom du film
     * @return nom du film
     */
    public String getName()
    {
        return this.nom;
    }

    /**
     * Retourne la liste des films projetés dans le cinéma
     * @return liste des films projetés dans le cinéma
     */
    public MovieCollection getFilmP()
    {
        return filmP;
    }

    /**
     * Change la liste des films projetés dans le cinéma
     * @param filmP Nouvelle liste des films projetés dans le cinéma
     */
    public void setFilmP(MovieCollection filmP)
    {
        this.filmP = filmP;
    }

    /**
     * Retourne la liste des salles du cinéma
     * @return liste des salles du cinéma
     */
    public List<Salle> getSalles()
    {
        return salles;
    }

    /**
     * Ajoute une salle au cinéma
     * @param salle Salle à ajouter
     */
    public void addSalles(Salle salle)
    {
        salles.add(salle);
    }

    /**
     * Supprime la salle dont le numéro est spécifié du cinéma
     * @param numSalle Numéro de la salle à supprimer
     * @return Vrai si la salle a été supprimée et False sinon
     */
    public boolean deleteSalles(int numSalle)
    {
        Salle s = new Salle();
        for (var salle : salles)
        {
            if (salle.getNumero() == numSalle)
                s = salle;
        }
        if (salles.contains(s))
            salles.remove(s);
        else
            return false;
        return true;
    }

    /**
     * Ajoute une séance dans le cinéma dans la salle dont le numéro est spécifié
     * @param salle Numéro de la salle où ajouter la séance
     * @param seance Séance à ajouter
     */
    public void addSeance(int salle, Seance seance)
    {
        for (var elem : salles)
        {
            if (elem.getNumero() == salle)
            {
                elem.addSeance(seance);
            }
        }
    }

    /**
     * Retourne l'id de base de données de la salle dont le numéro est spécifié
     * @param numSalle Salle dont on souhaite connaitre l'id de base de données
     * @return Id de base de donnée de la salle spécifiée et 0 si elle n'existe pas
     */
    public int getIdSalle(int numSalle)
    {
        for (var salle : salles)
        {
            if (salle.getNumero() == numSalle)
                return salle.get_id_bdd();
        }
        return 0;
    }

    /**
     * Ajoute une promotion à la liste des promotions utilisées
     * @param promo Promotion à ajouter
     */
    public void updateStatsPromo(String promo)
    {
        promoUtilisees.add(promo);
    }

    /**
     * Ajoute un nom de film à la liste des films vendus
     * @param title nom du film à ajouter
     */
    public void updateStatsFilm(String title)
    {
        filmVendus.add(title);
    }

    /**
     * Retourne la liste des promotions utilisées
     * @return liste des promotions utilisées
     */
    public ArrayList<String> getPromoUtilisees()
    {
        return promoUtilisees;
    }

    /**
     * Retourne la liste des films vendus
     * @return liste des film vendus
     */
    public ArrayList<String> getFilmVendus( ) {return filmVendus;}

    public void setSalles(List<Salle> salles)
    {
        this.salles = salles;
    }

    /**
     * Remplace le nom du cinéma
     * @param nom Nouveau nom du cinéma
     */
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    /**
     * Remplace l'image par une nouvelle via son URL
     * @param imageString Url de la nouvelle image
     */
    public void setImageString(String imageString)
    {
        this.imageString = imageString;
    }

    /**
     * Recherche si le cinéma contient la salle dont le numéro est spécifié
     * @param num Numéro de salle recherché
     * @return True si la salle existe et False sinon.
     */
    public boolean contains(int num)
    {
        for (var salle : salles)
        {
            if (salle.getNumero() == num)
                 return true;
        }
        return false;
    }

    /**
     * Ajoute une promotion au cinéma
     * @param promo Promotion à ajouter
     */
    public void add_promo(Promo promo)
    {
        this.promos.add(promo);
    }

    /**
     * Retourne la liste des promotions du cinéma
     * @return liste des promotions du cinéma
     */
    public ArrayList<Promo> get_promos()
    {
        return this.promos;
    }

    /**
     * Supprime la promotion dont l'id est spécifié
     * @param idPromo Id de base de donnée de la promotion à supprimer
     */
    public void deletePromo(int idPromo)
    {
        for (var promo : promos)
        {
            if (promo.get_idPromo() == idPromo)
            {
                promos.remove(promo);
                break;
            }
        }
    }
}
