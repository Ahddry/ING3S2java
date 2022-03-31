package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.CinemaCollection;
import com.example.netflexe.Model.Profil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Classe java gérant les contrôles et évènements de la vue CreationCinema.fxml
 */
public class CreationCinema
{
    @FXML
    private TextField nomCine;
    @FXML
    private TextField imageCine;

    private Profil profil;
    private SceneController mainApp;
    private CinemaCollection cinemaCollection;

    /**
     * Retour au menu de sélection de cinéma
     */
    public void retour()
    {
        mainApp.showChoixCinema(profil);
    }

    /**
     * Affecte un contrôleur SceneController à cette classe ainsi qu'un ensemble de cinémas auquel s'ajoutera le cinéma à créer et affecte aussi un utilisateur auquel affecter l'administration du cinéma nouvellement créé
     * @param mainApp contrôleur à affecter
     * @param cinemaCollection ensemble de cinémas auquel ajouter le cinéma à créer
     * @param profil profil qui va gérer le nouveau cinéma
     */
    public void setMainApp(SceneController mainApp, CinemaCollection cinemaCollection, Profil profil)
    {
        this.mainApp = mainApp;
        this.cinemaCollection = cinemaCollection;
        this.profil = profil;
    }

    /**
     * Création et enregistrement dans la base de données du nouveau cinéma créé d'après les saisies utilisateur et affectation de l'administration de celui-ci à l'utilisateur l'ayant créé.
     */
    public void confirmerBoutonClick()
    {
        if (!isNullOrWhiteSpace(nomCine.getText()))
        {
            boolean exists = false;
            for (int i = 0; i < cinemaCollection.getSize(); i++)
            {
                if (nomCine.getText() == cinemaCollection.getCinema(i).getName())
                    exists = true;
            }

            if (!exists)
            {
                String urlImage = "https://img.freepik.com/vecteurs-libre/facade-du-batiment-du-cinema-exterieur-maison-du-cinema_313242-617.jpg";
                if (!isNullOrWhiteSpace(imageCine.getText()))
                    urlImage = imageCine.getText();
                Cinema cinema = new Cinema(cinemaCollection.getSize() + 1, nomCine.getText(), urlImage);
                cinema.setImage();
                cinemaCollection.addCinema(cinema);

                mainApp.getHello().insertCinema_into_bdd(cinema.getName(), profil.get_id(), cinema.getImageString());

                profil.setCinema(cinema);
                mainApp.setCinemaAdmin(cinema);
                mainApp.setProfil(profil);
                mainApp.showAdmin();
            } else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Un cinéma portant ce nom existe déjà.", ButtonType.OK);
                alert.show();
            }

        } else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez renseigner le nom du cinéma.", ButtonType.OK);
            alert.show();
        }
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
