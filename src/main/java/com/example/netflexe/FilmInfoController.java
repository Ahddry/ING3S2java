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


public class FilmInfoController {

    @FXML
    private Button Retour;

    @FXML
    private Label Title;

    @FXML
    private ImageView Poster;


    private HelloApplication mainApp;

    @FXML
    private void initialize() {
    }

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void retourMenu() {
        mainApp.showMainMenu();
    }

    public void setMovie(Movie movie)
    {
        //System.out.print(movie.getTitle());
        Title.setText(movie.getTitle());
        Poster.setImage(movie.getImage());
    }
}
