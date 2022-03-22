package com.example.netflexe.Vue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

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
    @FXML 
    ScrollPane pane;
    @FXML
    private Label film_selec;


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
        film_selec.setText(String.valueOf(genreLiker) + "/5");
    }

    private Image getRoundImage(Image image, int radius) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
    
        WritableImage wImage = new WritableImage(radius * 2, radius * 2);
        PixelWriter pixelWriter = wImage.getPixelWriter();
        PixelReader pixelReader = image.getPixelReader();
        Color c1 = new Color(0, 0, 0, 0);
    
        int w = (width / 2);
        int h = (height / 2);
        int r = radius * radius;
    
        for (int i = (width / 2) - radius, k = 0; i < w + radius; i++, k++)
            for (int j = (height / 2) - radius, b = 0; j < h + radius; j++, b++) {
                if ((i - w) * (i - w) + (j - h) * (j - h) > r)
                    pixelWriter.setColor(k, b, c1);
                else
                    pixelWriter.setColor(k, b, pixelReader.getColor(i, j));
            }
        return wImage;
    }

    public void initializeBis()
    {

        ArrayList<Genre> genre = this.controller.get_genre_from_bdd();
        int count = 0;
        int count2 = 0;
        int count3 = 0;
        while(count < genre.size())
        {
            Button temp  = new Button();
            temp.setId(String.valueOf(genre.get(count).get_id_genre()));
            ImageView tempimage = new ImageView(getRoundImage(new Image(genre.get(count).get_poster()), 65));
            tempimage.setEffect(new GaussianBlur(15));
            Label tempLabel = new Label(genre.get(count).get_nom_genre());
            tempLabel.setPrefHeight(15);
            tempLabel.getStylesheets().addAll(getClass().getResource("genre.css").toExternalForm());
            StackPane tempPane = new StackPane();
            tempPane.getChildren().addAll(tempimage, tempLabel);
            temp.setGraphic(tempPane);
            temp.setOnMouseClicked(event -> {
                this.controller.genre_like(temp.getId());
                this.genreLiker++;
                film_selec.setText(String.valueOf(genreLiker) + "/5");
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



//https://stackoverflow.com/questions/68631386/javafx-crop-image-as-a-circle?rq=1