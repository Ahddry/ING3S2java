package com.example.netflexe.Vue;
import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Classe java gérant les contrôles et évènements de la vue ProfileCinéma.fxml
 */
public class ProfileCinema
{
    @FXML
    private Button approbAdminBouton;
    @FXML
    private ImageView imageCinema;
    @FXML
    private TextField nomField;
    @FXML
    private TextField adresseImageField;
    @FXML
    private Label nbSalles;
    @FXML
    private Label nbFilms;
    @FXML
    private Label nomCine;

    private SceneController mainApp;
    private Cinema cinema;

    /**
     * Attribution du cinéma dont il faut consulter ou modifier les attributs
     * @param cinema Cinéma à attribuer
     */
    public void setCinema(Cinema cinema)
    {
        this.cinema = cinema;
        imageCinema.setImage(cinema.getImage());
        nomCine.setText(cinema.getName());
        int nb = cinema.getSalles().size();
        nbSalles.setText("Nombre de salles : " + nb);
        nb = cinema.getFilmP().getSize();
        nbFilms.setText("Nombre de films : " + nb);

        if (!mainApp.getHello().getAttenteAdmin().isEmpty())
        {
            approbAdminBouton.setOnAction(event -> approbAdminBoutonClick());
        }
        else
            approbAdminBouton.setVisible(false);
    }

    /**
     * Modifie le nom du cinéma pour une nouvelle valeur renseignée par l'utilisateur
     */
    public void modifierNomClick()
    {
        if(!isNullOrWhiteSpace(nomField.getText()))
        {
            cinema.setNom(nomField.getText());

            mainApp.getHello().changerNomCinema(cinema.get_id_cine(), nomField.getText());

            mainApp.setCinemaAdmin(cinema);
            mainApp.showProfileCinema();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nouveau nom incorrect.", ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Modifie l'image de profil du cinéma pour une nouvelle valeur (sous forme de lien internet renvoyant à une image) renseignée par l'utilisateur
     */
    public void modifierImageClick()
    {
        if(!isNullOrWhiteSpace(adresseImageField.getText()))
        {
            cinema.setImageString(adresseImageField.getText());
            cinema.setImage();

            mainApp.getHello().changerLienImageCinema(cinema.get_id_cine(), adresseImageField.getText());

            mainApp.setCinemaAdmin(cinema);
            mainApp.showProfileCinema();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nouvelle image incorrecte.", ButtonType.OK);
            alert.show();
        }
    }

    /**
     * Affiche le menu de gestion des approbations de droits d'administration
     */
    public void approbAdminBoutonClick()
    {
        mainApp.showApprobationAdmin();
    }

    /**
     * Affecte un contrôleur SceneController à cette classe
     * @param mainApp contrôleur à affecter
     */
    public void setMainApp(SceneController mainApp) {
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
