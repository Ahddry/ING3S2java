package com.example.netflexe.Vue;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import com.example.netflexe.Model.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.time.LocalDate;


public class ValiderReservation {

    @FXML
    private ImageView image;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ChoiceBox horaireBox;

    @FXML
    private Label prixLabel;

    @FXML
    private Label prixFLabel;

    private SceneController mainApp;
    private ArrayList<Seance> seances = new ArrayList<Seance>();
    private Seance seanceS = null;




    @FXML
    private void initialize() {



    }

    public void initializeBis(Movie movie, Cinema cinema)
    {
        image.setImage(movie.getImage());
        seances = cinema.getAllSeances();
        ArrayList<String> horaires = new ArrayList<>();



        horaireBox.setBackground(new Background(new BackgroundFill(Color.rgb(29,29,31), CornerRadii.EMPTY, Insets.EMPTY)));
        horaireBox.setStyle("-fx-background-color: #1d1d1d; -fx-border-color: #3f3f3f; -fx-border-width: 5px;");


        //datePicker.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);


                setDisable(true);
                for(int i = 0; i< seances.size(); i ++)
                {

                    if(seances.get(i).getDate().toString().equals(date.toString()) ) {
                        setDisable(false);
                    }

                }
            }
        });

        datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            horaires.clear();
            for(int i = 0; i< seances.size();i++)
            {
                if((seances.get(i).getName().equals(movie.getTitle())) && (seances.get(i).getDate().toString().equals(newValue.toString()) ))
                {
                    horaires.add(seances.get(i).getHeure());
                }
            }

            horaireBox.setItems(FXCollections.observableArrayList(horaires));
        });

        horaireBox.valueProperty().addListener((ov, oldValue, newValue) -> {

            for(int i = 0; i< seances.size();i++)
            {
                if((seances.get(i).getDate().toString().equals(datePicker.valueProperty().getValue().toString())) && (seances.get(i).getHeure().equals(newValue.toString())))
                {
                    seanceS = seances.get(i);
                    prixLabel.setText((String.valueOf(seanceS.getPrix())));
                }
            }
        });


    }





    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }
}
