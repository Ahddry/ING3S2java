package com.example.netflexe.Vue;

import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.CinemaCollection;
import com.example.netflexe.Model.Profil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.google.common.base.Strings.isNullOrEmpty;

public class CreationCinemaController
{
    @FXML
    private TextField nomCine;
    @FXML
    private TextField imageCine;

    private Profil profil;
    private SceneController mainApp;
    private CinemaCollection cinemaCollection;

    public void retour()
    {
        mainApp.showChoixCinema(profil);
    }

    public void setMainApp(SceneController mainApp, CinemaCollection cinemaCollection, Profil profil)
    {
        this.mainApp = mainApp;
        this.cinemaCollection = cinemaCollection;
        this.profil = profil;
    }

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
