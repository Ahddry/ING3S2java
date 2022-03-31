package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Admin
{
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button4;

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
        ToggleButton adminButton = new ToggleButton("Admin");
        adminButton.setOnAction(event -> switchUser());
        adminButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#407070"), new CornerRadii(6), Insets.EMPTY)));
        adminButton.setTextFill(Color.WHITE);
        borderPane.setTop(adminButton);
        BorderPane.setAlignment(adminButton, Pos.CENTER_RIGHT);
        BorderPane.setMargin(adminButton, new Insets(5, 15, 5, 5));
        borderPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#1d1d1d"), new CornerRadii(0), Insets.EMPTY)));
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
        mainApp.showSeances();
    }
    @FXML
    private void setMenu3() {
        mainApp.showStats();

    }
    @FXML
    private void setMenu4() {
        mainApp.showProfileCinema();
    }

    @FXML
    private void switchUser()
    {
        mainApp.showMain();
    }

}
