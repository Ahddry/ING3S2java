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

public class GenreLikeController{
    private SceneController mainApp;
    private HelloApplication controller;
    private GridPane gridPane;
    @FXML
    private AnchorPane root;
    @FXML
    private Button like_btn;
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
        
        
        

    }
    public void initializeBis()
    {

        ArrayList<String> genre = this.controller.get_genre_from_bdd();
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        while(count < genre.size())
        {
            Button temp  = new Button(genre.get(count));
            temp.setOnMouseClicked(event -> {
                System.out.println(temp.getText());
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

