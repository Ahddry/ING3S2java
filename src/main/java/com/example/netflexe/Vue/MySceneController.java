package com.example.netflexe.Vue;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
    private void initialize() {
        Button1.setText("Accueil");
        Button2.setText("Réservation");
        Button3.setText("Biblio");
        Button4.setText("Profil");
        ToggleButton1.setText("User");
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void setMenu1() {
        mainApp.showMainMenu();
    }
    @FXML
    private void setMenu2() { mainApp.showBiblioRes(mainApp.getProfil());

    }
    @FXML
    private void setMenu3() {
        mainApp.showBiblio(mainApp.getProfil());
    }
    @FXML
    private void setMenu4() {
        mainApp.showProfile();
    }

    @FXML
    private void switchAdmin()
    {
        mainApp.showAdmin();
    }

}
