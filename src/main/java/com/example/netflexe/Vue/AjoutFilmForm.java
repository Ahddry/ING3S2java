package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Movie;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;

import static com.google.common.base.Strings.isNullOrEmpty;

public class AjoutFilmForm
{
    @FXML
    private TextField director;
    @FXML
    private TextField title;
    @FXML
    private TextField producer;
    @FXML
    private TextField dureeFilm;
    @FXML
    private TextField affiche;
    @FXML
    private ChoiceBox<String> genre;
    @FXML
    private DatePicker dateSortie;
    @FXML
    private TextArea synopsis;
    @FXML
    private TextField slogan;
    @FXML
    private Button enregistrerBouton;

    private SceneController mainApp;
    private Cinema monCinema;

    public void init()
    {
        enregistrerBouton.setText("Ajouter le film\nau cinéma");
        enregistrerBouton.textAlignmentProperty().set(TextAlignment.CENTER);
        genre.setItems(FXCollections.observableArrayList());
        genre.getItems().add("Action");
        genre.getItems().add("Animation");
        genre.getItems().add("Aventure");
        genre.getItems().add("Comédie");
        genre.getItems().add("Crime");
        genre.getItems().add("Documentaire");
        genre.getItems().add("Drame");
        genre.getItems().add("Familial");
        genre.getItems().add("Fantastique");
        genre.getItems().add("Guerre");
        genre.getItems().add("Histoire");
        genre.getItems().add("Horreur");
        genre.getItems().add("Musique");
        genre.getItems().add("Mystère");
        genre.getItems().add("Romance");
        genre.getItems().add("Science-Fiction");
        genre.getItems().add("Thriller");
        genre.getItems().add("Western");
    }

    public void enregistrerBoutonClick()
    {
        if ((!isNullOrWhiteSpace(title.getText())) && (!isNullOrWhiteSpace(dureeFilm.getText())))
        {
            String titre = title.getText();
            String dureeDuFilm = dureeFilm.getText();
            String realisateur;
            if (isNullOrWhiteSpace(director.getText()))
                realisateur = "Inconnu";
            else
                realisateur = director.getText();
            String producteur;
            if (isNullOrWhiteSpace(producer.getText()))
                producteur = "Inconnu";
            else
                producteur = producer.getText();
            String leSlogan;
            if (isNullOrWhiteSpace(slogan.getText()))
                leSlogan = "";
            else
                leSlogan = slogan.getText();
            LocalDate dateDeSortie = dateSortie.getValue();
            if (dateDeSortie == null)
            {
                dateDeSortie = LocalDate.now();
            }
            String leSynopsis;
            if (isNullOrWhiteSpace(synopsis.getText()))
                leSynopsis = "";
            else
                leSynopsis = synopsis.getText();
            String urlImage;
            if (isNullOrWhiteSpace(affiche.getText()))
                urlImage = "";
            else
                urlImage = affiche.getText();

            int id = mainApp.getHello().insertMovie_into_bdd(urlImage, titre, dateDeSortie.toString(), dureeDuFilm, leSynopsis, leSlogan, "");
            if (id != -1)
            {
                Movie movie = new Movie(titre, realisateur, urlImage, dateDeSortie.toString(), dateDeSortie.toString(), dureeDuFilm, leSynopsis, leSlogan, id+"", null);
                movie.setImage(new Image(movie.getImageString()));
                monCinema.ajoutFilm(movie);
                mainApp.setCinemaAdmin(monCinema);
                mainApp.showAccueilAdmin();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la création du film", ButtonType.OK);
                alert.show();
            }

        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez renseigner un titre et une durée pour le film.", ButtonType.OK);
            alert.show();
        }
    }

    private boolean isNullOrWhiteSpace(String s)
    {
        if (isNullOrEmpty(s))
        {
            return true;
        } else
        {
            int length = s.length();
            if (length > 0)
            {
                for (int i = 0; i < length; i++)
                {
                    if (!Character.isWhitespace(s.charAt(i)))
                    {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    }

    public void setMainApp(SceneController sceneController)
    {
        mainApp = sceneController;
    }

    public void setCinema(Cinema cinema)
    {
        monCinema = cinema;
    }
}
