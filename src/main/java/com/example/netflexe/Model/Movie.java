package com.example.netflexe.Model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private final StringProperty id_film;
    private final StringProperty trailer;
    private final ObjectProperty<LocalDate> releaseDate;
    private  Image image = null;
    private String imageName;

    public Movie() {
        this(null, null, null, "1212-12-12",
                "0000-00-00", null, null, null, null ,null);
    }
    public Movie(String title, String adresseImage)
    {
        this.title = new SimpleStringProperty(title);
        this.imageName = null;
        this.image = new Image(adresseImage);
        this.director = null;
        this.produceur = null;
        this.genre = null;
        this.releaseDate = null;
        this.date_de_sortie_LD = null;
        this.date_de_sortie_S = null;
        this.duree = null;
        this.synopsis = null;
        this.slogan = null;
        this.id_film = null;
        this.trailer = null;
    }

    public Movie(String title, String director, String adresseImage, String date_de_sortie_LD,
                 String date_de_sortie_S, String duree, String synopsis, String slogan, String id_film, String trailer) {
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
        this.id_film = new SimpleStringProperty(id_film);
        if(trailer != null)
        {
            this.trailer = new SimpleStringProperty(trailer);
        }
        else
        {
            this.trailer = null;
        }
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

    public String getTrailer()
    {
        if(trailer != null)
        {
            if (trailer.toString().contains("="))
            {
                return trailer.get().split("=")[1];
            }
            else
                return trailer.get();
        }
        else
        {
            return null;
        }
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

    public String get_idFilm(){
        return id_film.get();
    }
}