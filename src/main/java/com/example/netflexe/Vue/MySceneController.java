package com.example.netflexe.Vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

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
    private void setMenu2() {

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
