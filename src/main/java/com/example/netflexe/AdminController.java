package com.example.netflexe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

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
