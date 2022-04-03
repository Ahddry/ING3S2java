package com.example.netflexe.Model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.Period;

/**
 * Représente un acteur avec son nom, son prénom, son âge, une petite description, et son rôle dans le film, ainsi qu'une photo portrait
 */
public class Actor {

    private final StringProperty first_name;
    private final StringProperty last_name;
    private final StringProperty age;
    private final StringProperty description;
    private final StringProperty role;
    private  Image imageActeur = null;
    private String imageNameActeur;

    /**
     * Création d'un acteur vide
     */
    public Actor() {
        this(null, null, null, null, null, null);
    }

    /**
     *
     * @param prenom Prénom de l'acteur
     * @param nom   Nom de l'acteur
     * @param adresseImage  adresse URL de l'image de l'acteur
     * @param date_de_naissance Date permettant de déterminer l'âge de l'acteur
     * @param description   Description rapide de l'acteur
     * @param role  Rôle joué par l'acteur dans le film
     */
    public Actor(String prenom, String nom, String adresseImage, String date_de_naissance, String description, String role) {
        this.first_name = new SimpleStringProperty(prenom);
        this.last_name = new SimpleStringProperty(nom);
        LocalDate date = LocalDate.parse(date_de_naissance);
        String birthday = Integer.toString(Period.between(date, LocalDate.now()).getYears());
        this.age = new SimpleStringProperty(birthday);
        this.imageNameActeur = adresseImage;
        this.description = new SimpleStringProperty(description);
        this.role = new SimpleStringProperty(role);
    }

    /**
     * Ensemble des getter et setter de la classe Acteur
     * avec une spécification sur le getName qui retourne nom+prénom de l'acteur
     * et l'âge avec l'ajout "ans" : 'xx ans'
     * @return nom complet, âge, rôle, image, description
     */
    public String getFirstName() {
        return first_name.get();
    }

    public String getLastName() {
        return last_name.get();
    }
    public String getName(){ return first_name.get() + " " + last_name.get();}
    public String getAge() {
        return age.get() + " ans";
    }

    public String getDescription() {return description.get();}

    public String getRole() { return role.get();}
    public String getImageNameActeur() { return imageNameActeur;}

    public Image getImageActeur() {
        return imageActeur;
    }
    public void setImageActeur(Image image) {
        this.imageActeur = image;
    }

}
