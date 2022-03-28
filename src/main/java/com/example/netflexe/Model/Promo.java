package com.example.netflexe.Model;

public class Promo {
    private int id_promo;
    private String nom_promo;
    private double pourcentage;
    public Promo(int id_promo, String nom_promo, double pourcentage)
    {
        this.id_promo = id_promo;
        this.nom_promo = nom_promo;
        this.pourcentage = pourcentage;
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
}
