package com.example.netflexe.Vue;

import com.example.netflexe.Controller.HelloApplication;
import javafx.fxml.FXML;
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
import com.example.netflexe.Model.*;

import java.util.ArrayList;


public class ValiderReservation {

    @FXML
    private ImageView image;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox horaireBox;

    private SceneController mainApp;
    private ArrayList<Seance> seances = new ArrayList<Seance>();




    @FXML
    private void initialize() {



    }

    public void initializeBis(Movie movie, Cinema cinema)
    {
        image.setImage(movie.getImage());
        seances = cinema.getAllSeances();
        ArrayList<String> horaires = new ArrayList<>();

        for(int i = 0; i< seances.size();i++)
        {
            if(seances.get(i).getName() == movie.getTitle())
            {
                horaires.add(seances.get(i).getHeure());
            }
        }

        horaireBox.setItems(FXCollections.observableArrayList(horaires));


    }


    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }
}
