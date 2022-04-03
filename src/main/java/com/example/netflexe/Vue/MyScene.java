package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Profil;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class MyScene {
    @FXML
    BorderPane borderPane;

    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;

    private SceneController mainApp;

    private Profil profil;

    /**
     * initialise chacun des boutons du menu contextuel de droite
     */
    public void initialiseBis()
    {
        ImageView image = new ImageView(new Image("https://i.imgur.com/i78tBup.png"));
        ImageView image2 = new ImageView(new Image("https://i.imgur.com/uYi3mUm.png"));
        ImageView image3 = new ImageView(new Image("https://i.imgur.com/cZORJ4u.png"));
        ImageView image4 = new ImageView(new Image("https://i.imgur.com/UY2Te25.png"));

        if(profil == null)
        {
            image.setEffect(new SepiaTone());
            image2.setEffect(new SepiaTone());
            image3.setEffect(new SepiaTone());
        }
        image.setFitWidth(40);
        image.setFitHeight(50);
        image2.setFitWidth(40);
        image2.setFitHeight(50);
        image3.setFitWidth(40);
        image3.setFitHeight(50);
        image4.setFitWidth(40);
        image4.setFitHeight(50);
        Button1.setText(null);
        Button2.setText(null);
        Button3.setText(null);
        Button4.setText(null);
        Button1.setGraphic(image4);
        Button2.setGraphic(image3);
        Button3.setGraphic(image2);
        Button4.setGraphic(image);

        if (profil != null)
        {
            if (profil.isAdmin())
            {
                mainApp.setProfil(profil);
                ToggleButton adminButton = new ToggleButton("User");
                adminButton.setOnAction(event -> switchAdmin());
                adminButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#407070"), new CornerRadii(6), Insets.EMPTY)));
                adminButton.setTextFill(Color.WHITE);
                borderPane.setTop(adminButton);
                BorderPane.setAlignment(adminButton, Pos.CENTER_RIGHT);
                BorderPane.setMargin(adminButton, new Insets(5, 15, 5, 5));
                borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#1d1d1d"), new CornerRadii(0), Insets.EMPTY)));
            }
        }
    }

    /**
     * lien entre unprofil et le bouton show my profil
     * @param profil profil associé
     */
    public void setProfil(Profil profil)
    {
        this.profil = profil;
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * associe le bouton 1 au main menu
     */
    @FXML
    private void setMenu1() {
        mainApp.showMainMenu();
    }
    /**
     * associe le bouton 2 aux réservations
     */

    @FXML
    private void setMenu2() {
        if(profil!=null)
        {
            mainApp.showBiblioRes(mainApp.getProfil());
        }


    }
    /**
     * associe le bouton 3 à la bibliothèque
     */

    @FXML
    private void setMenu3() {
        if(profil!=null) {
            mainApp.showBiblio(mainApp.getProfil());
        }


    }
    @FXML
    /**
     * associe le bouton 4 à showProfile
     */
    private void setMenu4() {
        if(profil!=null) {
            mainApp.showProfile();
        }



    }

    @FXML
    /**
     * permet d'accéder à la page Admin
     */
    private void switchAdmin()
    {
        if (profil.getCinema() != null)
        {
            //mainApp.setCinemaAdmin(profil.getCinema());
            mainApp.showAdmin();
        }
        else
            mainApp.showChoixCinema(profil);
    }

}
