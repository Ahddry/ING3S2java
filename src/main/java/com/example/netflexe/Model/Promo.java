package com.example.netflexe.Model;

public class Promo {
    private int id_promo;
    private String nom_promo;
    private double pourcentage;
    private int max_age;
    private int min_age;
    public Promo(String nom_promo)
    {
        this.nom_promo = nom_promo;
    }
    public Promo(int id_promo, String nom_promo, double pourcentage)
    {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.pourcentage = pourcentage;
    }
    public Promo(int id_promo, String nom_promo, double pourcentage, int min_age, int max_age)
    {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.pourcentage = pourcentage;
        this.min_age = min_age;
        this.max_age = max_age;
    }
    public double get_pourcentage()
    {
        return this.pourcentage;
    }
    public void set_pourcentage(double pourcentage)
    {
        this.pourcentage = pourcentage;
    }
    public String get_nomPromo()
    {
        return this.nom_promo;
    }
    public void set_Promo(String nom_Promo)
    {
        this.nom_promo =  nom_Promo;
    }
    public int get_idPromo()
    {
        return this.id_promo;
    }
    public void set_idPromo(int id_promo)
    {
        this.id_promo = id_promo;
    }
    public void set_minAge(int min_age)
    {
        this.min_age = min_age;
    }
    public void set_maxAge(int max_age)
    {
        this.max_age = max_age;
    }
    public int get_minAge()
    {
        return this.min_age;
    }
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
