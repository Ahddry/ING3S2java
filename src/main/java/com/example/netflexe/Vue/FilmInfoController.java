package com.example.netflexe.Vue;

import com.example.netflexe.Model.ActorCollection;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Movie;
import com.example.netflexe.Model.Profil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;


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

    @FXML
    private ListView ListView;

    @FXML
    private Label Label;

    private SceneController mainApp;
    private Movie movieS;
    private Profil monProfil;
    private boolean adminAccess;
    private Cinema monCinema;

    private ActorCollection collection = new ActorCollection();

    @FXML
    private void initialize() {
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void retourMenu() {
        if (adminAccess)
            mainApp.showAccueilAdmin();
        else
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
        if (!adminAccess)
            monProfil.ajouterLike(movieS);
    }

    @FXML
    public void startReservation()
    {
        if (!adminAccess)
            mainApp.showReservation(movieS);
        else
        {
            if (!monCinema.checkMovie(movieS.getTitle()))
            {
                monCinema.ajoutFilm(movieS);
                mainApp.setCinemaAdmin(monCinema);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ce film est déjà à l'affiche dans votre cinéma.", ButtonType.OK);
                alert.show();
            }

        }
    }

    public void setCinema(Cinema cinema)
    {
        monCinema = cinema;
    }

    public void setAdminAccess(boolean admin)
    {
        adminAccess = admin;
        if (adminAccess)
        {
            ReserverButton.setText("Ajouter le fim au cinéma");
            LikeButton.setVisible(false);
        }
    }
}
