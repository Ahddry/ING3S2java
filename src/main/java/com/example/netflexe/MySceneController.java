package com.example.netflexe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private HelloApplication mainApp;

    @FXML
    private void initialize() {
        Button1.setText("Acceuil");
        Button2.setText("Réservation");
        Button3.setText("Biblio");
        Button4.setText("Profil");
    }

    public void setMainApp(HelloApplication mainApp) {
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
        mainApp.showBiblio();
    }
    @FXML
    private void setMenu4() {

    }

}
