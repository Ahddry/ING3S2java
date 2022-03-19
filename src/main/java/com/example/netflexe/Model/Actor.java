package com.example.netflexe.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.Period;

public class Actor {

    private final StringProperty first_name;
    private final StringProperty last_name;
    private final StringProperty age;
    private final StringProperty description;
    private final StringProperty role;
    private  Image imageActeur = null;
    private String imageNameActeur;

    public Actor() {
        this(null, null, null, null, null, null);
    }

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

    public String getFirstName() {
        return first_name.get();
    }

    public String getLastName() {
        return last_name.get();
    }
    public String getName(){ return first_name.get() + " " + last_name.get();}
    public String getAge() {
        return age.get();
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
