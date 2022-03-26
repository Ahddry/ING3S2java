package com.example.netflexe.Vue;

import com.example.netflexe.Controller.HelloApplication;
import com.example.netflexe.Model.Cinema;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import static com.google.common.base.Strings.isNullOrEmpty;

public class ProfileCinemaController
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
    private HelloApplication controller;
    private Cinema cinema;

    public void setCinema(Cinema cinema)
    {
        this.cinema = cinema;
        imageCinema.setImage(cinema.getImage());
        nomCine.setText(cinema.getName());
        int nb = cinema.getSalles().size();
        nbSalles.setText("Nombre de salles : " + nb);
        nb = cinema.getFilmP().getSize() - 1;
        nbFilms.setText("Nombre de films : " + nb);

        if (!mainApp.getHello().getAttenteAdmin().isEmpty())
        {
            approbAdminBouton.setOnAction(event -> approbAdminBoutonClick());
        }
        else
            approbAdminBouton.setVisible(false);
    }

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

    public void approbAdminBoutonClick()
    {
        mainApp.showApprobationAdmin();
    }

    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
    }

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
