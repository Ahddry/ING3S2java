package com.example.netflexe.Model;

/**
 * Classe Promo pour les promotions dans les cinéma
 */
public class Promo {
    private int id_promo;
    private String nom_promo;
    private double pourcentage;
    private int max_age;
    private int min_age;
    /**
     * Constructeur de la promo
     * @param nom_promo nom de la promotion en string
     */
    public Promo(String nom_promo)
    {
        this.nom_promo = nom_promo;
    }
    /**
     * Constructeur de la promo surchargé
     * @param id_promo id de la promo dans la BDD
     * @param nom_promo nom de la promo dans la BDD
     * @param pourcentage pourcentage du prix recuperer depuis la BDD
     */
    public Promo(int id_promo, String nom_promo, double pourcentage)
    {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.pourcentage = pourcentage;
    }
    /**
     * Constructeur de la promo surchargé maximum
     * @param id_promo id de la promo provenant de la BDD
     * @param nom_promo nom de la promo provenant de la BDD
     * @param pourcentage pourcentage du prix du film
     * @param min_age age minimum pour acceder a la promotion
     * @param max_age age maximum pour acceder a la promotion
     */
    public Promo(int id_promo, String nom_promo, double pourcentage, int min_age, int max_age)
    {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.pourcentage = pourcentage;
        this.min_age = min_age;
        this.max_age = max_age;
    }
    /**
     * Méthode qui recupere le pourcentage de promotion sur le film
     * @return pourcentage en double
     */
    public double get_pourcentage()
    {
        return this.pourcentage;
    }
    /**
     * Méthode permettant de set le pourcentage du prix du film
     * @param pourcentage pourcentage en double
     */
    public void set_pourcentage(double pourcentage)
    {
        this.pourcentage = pourcentage;
    }
    /**
     * Méthode qui retourne le nom de la promo
     * @return le nom de la promo en string
     */
    public String get_nomPromo()
    {
        return this.nom_promo;
    }
    /**
     * Méthode qui permet de set le nom de la promotion
     * @param nom_Promo nom de la promotion en string
     */
    public void set_Promo(String nom_Promo)
    {
        this.nom_promo =  nom_Promo;
    }

    /**
     * Méthode qui recupere l'id de la promotion de la BDD
     * @return id de la promo en int
     */
    public int get_idPromo()
    {
        return this.id_promo;
    }
    /**
     * Méthode qui set l'id de la promo
     * @param id_promo id de la promo en int
     */
    public void set_idPromo(int id_promo)
    {
        this.id_promo = id_promo;
    }
    /**
     * méthode qui set l'age minimum pour acceder a une promotion
     * @param min_age age minimum en int
     */
    public void set_minAge(int min_age)
    {
        this.min_age = min_age;
    }
    /**
     * méthode qui set l'age maximum pour acceder a une promotion
     * @param max_age age maximum en int
     */
    public void set_maxAge(int max_age)
    {
        this.max_age = max_age;
    }
    /**
     * Méthode qui recupere l'age minimum de la promotion 
     * @return age min en int
     */
    public int get_minAge()
    {
        return this.min_age;
    }
    /**
     * Méthode qui recupere l'age maximum de la promotion
     * @return age max en int
     */
    public int get_maxAge()
    {
        return this.max_age;
    }
    @Override
    public String toString()
    {
        return this.nom_promo;
    }
}
