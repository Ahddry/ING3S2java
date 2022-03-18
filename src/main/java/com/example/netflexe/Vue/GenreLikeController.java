package com.example.netflexe.Vue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;



import com.example.netflexe.Controller.HelloApplication;

public class GenreLikeController{
    private SceneController mainApp;
    private HelloApplication controller;
    @FXML
    private AnchorPane root;
    @FXML
    private Button like_btn;
    @FXML
    private AnchorPane anchorGenre;

    @FXML
    private void initialize() {
        GridPane gridPaneMain = new GridPane();
        anchorGenre.getChildren().add(gridPaneMain);
    }
    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
        root.requestFocus();
    }
}

