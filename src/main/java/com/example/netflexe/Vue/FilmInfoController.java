package com.example.netflexe.Vue;

import com.example.netflexe.Model.Movie;
import com.example.netflexe.Model.Profil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


public class FilmInfoController {

    @FXML
    private Button Retour;
    @FXML
    private Button LikeButton;

    @FXML
    private Label Title;

    @FXML
    private ImageView Poster;

    @FXML
    private Button ReserverButton;

    @FXML
    private Label DateDeSortie;

    @FXML
    private Label Duree;

    @FXML
    private Label Synopsis;

    @FXML
    private Label Slogan;

    private SceneController mainApp;
    private Movie movieS;
    private Profil monProfil;

    @FXML
    private void initialize() {
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void retourMenu() {
        mainApp.showMainMenu();
    }

    public void setMovie(Movie movie)
    {
        //System.out.print(movie.getTitle());
        Title.setText(movie.getTitle());
        Poster.setImage(movie.getImage());
        DateDeSortie.setText(movie.getDate_de_sortie_S());
        Duree.setText(movie.getDuree());
        Synopsis.setText(movie.getSynopsis());
        Slogan.setText(movie.getSlogan());
        movieS = movie;
    }

    public void setProfil(Profil profil)
    {
        monProfil = profil;
    }

    @FXML
    public void addLike()
    {
        monProfil.ajouterLike(movieS);
    }

    @FXML
    public void startReservation()
    {
        mainApp.showReservation(movieS);
    }
}
