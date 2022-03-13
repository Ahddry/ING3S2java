package com.example.netflexe.Model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.Image;


public class Movie {

    private final StringProperty title;
    private final StringProperty director;
    private final StringProperty produceur;
    private final StringProperty genre;

    private final ObjectProperty<LocalDate> releaseDate;
    private  Image image = null;
    private String imageName;

    public Movie() {
        this(null, null, null);
    }

    public Movie(String title, String director, String adresseImage) {
        this.title = new SimpleStringProperty(title);
        this.director = new SimpleStringProperty(director);
        this.produceur = new SimpleStringProperty("No produceur specified");
        this.genre = new SimpleStringProperty("No genre specified");
        this.imageName = adresseImage;
        this.releaseDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getTitle() {
        return title.get();
    }
    public void setTitle(String firstName) {
        this.title.set(firstName);
    }
    public StringProperty titleProperty() {
        return title;
    }

    public String getDirector() {
        return director.get();
    }
    public void setDirector(String lastName) {
        this.director.set(lastName);
    }
    public StringProperty directorProperty() {
        return director;
    }

    public String getProduceur() {
        return produceur.get();
    }
    public void setProduceur(String street) {
        this.produceur.set(street);
    }
    public StringProperty produceurProperty() {
        return produceur;
    }


    public String getGenre() {
        return genre.get();
    }
    public void setGenre(String city) {
        this.genre.set(city);
    }
    public StringProperty genreProperty() {
        return genre;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    //public StringProperty imageProperty() {
       // return image;
    //}
    public String getImageString(){return imageName;}

    public LocalDate getReleaseDate() {
        return releaseDate.get();
    }
    public void setReleaseDate(LocalDate birthday) {
        this.releaseDate.set(birthday);
    }
    public ObjectProperty<LocalDate> releaseDateProperty() {
        return releaseDate;
    }
}