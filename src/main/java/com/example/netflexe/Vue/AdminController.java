package com.example.netflexe.Vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminController
{
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;
    @FXML
    private ToggleButton ToggleButton1;

    private SceneController mainApp;


    @FXML
    private void initialize() {
        Button1.setText("Accueil");
        Button2.setText("Mon cinéma");
        Button3.setText("Statistiques");
        Button4.setText("Profil");


        ImageView image = new ImageView(new Image("https://i.imgur.com/plCNG92.png"));
        ImageView image2 = new ImageView(new Image("https://i.imgur.com/XsOIrxx.png"));
        ImageView image3 = new ImageView(new Image("https://i.imgur.com/XN3tAzf.png"));
        ImageView image4 = new ImageView(new Image("https://i.imgur.com/i78tBup.png"));
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
        Button1.setGraphic(image);
        Button2.setGraphic(image2);
        Button3.setGraphic(image3);
        Button4.setGraphic(image4);
        ToggleButton1.setText("Admin");
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void setMenu1() {
        mainApp.showAccueilAdmin();
    }
    @FXML
    private void setMenu2() {
        //mainApp.showSeances();
        mainApp.testSeances(); //TEMPORAIRE
    }
    @FXML
    private void setMenu3() {

    }
    @FXML
    private void setMenu4() {

    }

    @FXML
    private void switchUser()
    {
        mainApp.showMain();
    }

}
