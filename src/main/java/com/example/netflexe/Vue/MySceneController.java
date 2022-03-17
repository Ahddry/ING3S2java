package com.example.netflexe.Vue;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.io.File;
import java.io.FileInputStream;
import com.example.netflexe.Model.*;


public class MySceneController {

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

    private Profil profil;

    @FXML
    private void initialize() {

        ImageView image = new ImageView(new Image("https://i.imgur.com/i78tBup.png"));
        ImageView image2 = new ImageView(new Image("https://i.imgur.com/uYi3mUm.png"));
        ImageView image3 = new ImageView(new Image("https://i.imgur.com/cZORJ4u.png"));
        ImageView image4 = new ImageView(new Image("https://i.imgur.com/UY2Te25.png"));
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



        ToggleButton1.setText("User");
    }

    public void setProfil(Profil profil)
    {
        this.profil = profil;
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void setMenu1() {
        mainApp.showMainMenu();
    }
    @FXML
    private void setMenu2() {
        if(profil!=null)
        {
            mainApp.showBiblioRes(mainApp.getProfil());
        }


    }
    @FXML
    private void setMenu3() {
        if(profil!=null) {
            mainApp.showBiblio(mainApp.getProfil());
        }


    }
    @FXML
    private void setMenu4() {
        if(profil!=null) {
            mainApp.showProfile();
        }



    }

    @FXML
    private void switchAdmin()
    {
        mainApp.showAdmin();
    }

}
