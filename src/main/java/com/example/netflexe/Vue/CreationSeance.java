package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Movie;
import com.example.netflexe.Model.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Classe java gérant les contrôles et évènements de la vue CreationSeance.fxml
 */
public class CreationSeance
{
    @FXML
    private Label Title;
    @FXML
    private ImageView Poster;
    @FXML
    private Label DateDeSortie;
    @FXML
    private Label Duree;
    @FXML
    private Label Slogan;
    @FXML
    private Label nomCine;
    @FXML
    private ChoiceBox<String> choixSalle;
    @FXML
    private DatePicker dateSeance;
    @FXML
    private TextField heureSeance;
    @FXML
    private TextField prix;

    private SceneController mainApp;
    private Movie movieS;
    private Cinema monCinema;

    /**
     * Retour à l'accueil administrateur
     */
    public void retourMenu()
    {
        mainApp.showAccueilAdmin();
    }

    /**
     * Affectation d'un film pour lequel créer une séance
     * @param movie Film pour lequel créer une séance
     */
    public void setMovie(Movie movie)
    {
        movieS = movie;
        Title.setText(movie.getTitle());
        Poster.setImage(movie.getImage());
        DateDeSortie.setText(movie.getDate_de_sortie_S());
        Duree.setText(movie.getDuree());
        Slogan.setText(movie.getSlogan());
        movieS = movie;
    }

    /**
     * Affectation d'un cinéma dans lequel créer une séance
     * @param cinema Cinéma dans lequel créer une séance
     */
    public void setCinema(Cinema cinema)
    {
        monCinema = cinema;
        nomCine.setText(cinema.getName());
        ArrayList<String> liste = new ArrayList<>();
        for (var salle : monCinema.getSalles())
        {
            liste.add("Salle " + salle.getNumero());
        }
        ObservableList<String> listeAffichage = FXCollections.observableArrayList();
        listeAffichage.addAll(liste);
        choixSalle.setItems(listeAffichage);
    }

    /**
     * Création et enregistrement dans la base de données de la nouvelle séance créée d'après les saisies utilisateur et affectation de celle-ci au cinéma et à la salle indiquée.
     */
    public void confirmerBoutonClick()
    {
        if ((!isNullOrWhiteSpace(prix.getText())) && (!isNullOrWhiteSpace(heureSeance.getText())) && (!Objects.equals(choixSalle.getValue(), null)))
        {
            double prixFinal = 0;
            boolean bonPrix = false;
            try
            {
                prixFinal = Double.parseDouble(prix.getText());
                if (prixFinal <= 0)
                    throw new NumberFormatException("Erreur : le prix doit être positif");
                else bonPrix = true;
            } catch (NumberFormatException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                alert.show();
            }
            if (bonPrix)
            {
                LocalDate dateDeSeance = dateSeance.getValue();
                if (dateDeSeance == null)
                {
                    dateDeSeance = LocalDate.now();
                }
                int numSalle = Integer.parseInt(choixSalle.getValue().split(" ")[1]);
                Seance seance = new Seance(movieS.getTitle(), movieS, dateDeSeance, heureSeance.getText(), numSalle, prixFinal,-1);
                monCinema.addSeance(numSalle, seance);
                mainApp.getHello().CreateSeance_into_bdd(Integer.parseInt(movieS.get_idFilm()), monCinema.get_id_cine(), monCinema.getIdSalle(numSalle), seance.getDate().toString(), seance.getHeure(), seance.getPrix());
                mainApp.getSeanceController().setTestCharge(true);
                mainApp.setCinemaAdmin(monCinema);
                mainApp.showAccueilAdmin();
            }
        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez renseigner tous les champs.", ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Affecte un contrôleur SceneController à cette classe
     * @param mainApp contrôleur à affecter
     */
    public void setMainApp(SceneController mainApp)
    {
        this.mainApp = mainApp;
    }

    /**
     * Méthode vérifiant si une chaine de caractères donnée est : nulle, vide ou remplie d'espaces blancs.
     * @param s Chaine de caractères dont il faut vérifier le contenu
     * @return True si la chaine de caractère est : nulle, vide ou remplie d'espaces blancs, False sinon.
     */
    private static boolean isNullOrWhiteSpace(String s)
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
}
