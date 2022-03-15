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

    //Ajout donatien
    private final ObjectProperty<LocalDate> date_de_sortie_LD;
    private final StringProperty date_de_sortie_S;
    private final StringProperty duree;
    private final StringProperty synopsis;
    private final StringProperty slogan;

    private final ObjectProperty<LocalDate> releaseDate;
    private  Image image = null;
    private String imageName;

    public Movie() {
        this(null, null, null, null, null, null, null, null );
    }

    public Movie(String title, String director, String adresseImage, String date_de_sortie_LD, String date_de_sortie_S, String duree, String synopsis, String slogan) {
        this.title = new SimpleStringProperty(title);
        this.director = new SimpleStringProperty(director);
        this.produceur = new SimpleStringProperty("No produceur specified");
        this.genre = new SimpleStringProperty("No genre specified");
        this.imageName = adresseImage;
        this.releaseDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        LocalDate date = LocalDate.parse(date_de_sortie_LD);
        this.date_de_sortie_LD = new SimpleObjectProperty<LocalDate>(date);
        this.date_de_sortie_S = new SimpleStringProperty(date_de_sortie_S);
        this.duree = new SimpleStringProperty(duree);
        this.synopsis = new SimpleStringProperty(synopsis);
        this.slogan = new SimpleStringProperty(slogan);
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

    public LocalDate getDate_de_sortie_LD(){
        return date_de_sortie_LD.get();
    }
    public void setDate_de_sortie_LD(LocalDate Date_sortie){
        this.date_de_sortie_LD.set(Date_sortie);
    }

    public String getDate_de_sortie_S(){
        return date_de_sortie_S.get();
    }
    public void setDate_de_sortie_S(String Date_sortie) {
        this.date_de_sortie_S.set(Date_sortie);
    }
    public String getDuree(){
        return duree.get();
    }
    public void setDuree(String Duree_film){
        this.duree.set(Duree_film);
    }

    public String getSynopsis(){
        return synopsis.get();
    }
    public void setSynopsis(String descriptif){
        this.synopsis.set(descriptif);
    }

    public String getSlogan(){
        return slogan.get();
    }
    public void setSlogan(String phrase){
        this.slogan.set(phrase);
    }
}