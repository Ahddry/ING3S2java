package com.example.netflexe.Vue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import com.example.netflexe.Controller.HelloApplication;
import com.example.netflexe.Model.Genre;

public class GenreLikeController{
    private SceneController mainApp;
    private HelloApplication controller;
    private int genreLiker = 0;
    private GridPane gridPane;
    @FXML
    private AnchorPane root;
    @FXML
    private Button enregistrer;
    @FXML
    private AnchorPane anchorGenre;
    @FXML ScrollPane pane;


    @FXML
    private void initialize() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        pane.setStyle("-fx-background-color:transparent;");
        pane.setPannable(true);
        gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        pane.setVbarPolicy(ScrollBarPolicy.NEVER);
        pane.setHbarPolicy(ScrollBarPolicy.NEVER);
        anchorGenre.getChildren().add(gridPane);
        
        enregistrer.setOnMouseClicked(event -> {
            if(genreLiker >= 5)
            {
                this.mainApp.Login();
            }
        });
        
        

    }
    public void initializeBis()
    {

        ArrayList<Genre> genre = this.controller.get_genre_from_bdd();
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        while(count < genre.size())
        {
            Button temp  = new Button(genre.get(count).get_nom_genre());
            temp.setId(String.valueOf(genre.get(count).get_id_genre()));
            temp.setOnMouseClicked(event -> {
                this.controller.genre_like(temp.getId());
                this.genreLiker++;
            });
            temp.setStyle("-fx-background-radius : 100;");
            temp.setMinSize(130, 130);
            gridPane.add(temp, count2,count3,1,1);
            count++;
            count2++;
            if(count2 == 3)
            {
                count2 = 0;
                count3++;
            }
        }
    }
    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
        root.requestFocus();
    }
}

