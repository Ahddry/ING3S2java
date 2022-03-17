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
    private ChoiceBox<String> horaireBox;

    @FXML
    private ChoiceBox<String> promoChoiceBox;

    @FXML
    private Label prixLabel;

    @FXML
    private Label prixFLabel;

    @FXML
    private TextField mailInput;

    private SceneController mainApp;
    private ArrayList<Seance> seances = new ArrayList<Seance>();
    private Seance seanceS = null;
    private double prixFinal = 0;
    private Profil profil;
    private Movie movieS;
    private LocalDate dateS;
    private String cinemaName;
    private String horaireS;


    @FXML
    private void initialize() {



    }

    public void initializeBis(Movie movie, Cinema cinema)
    {
        movieS = movie;
        cinemaName = cinema.getName();
        image.setImage(movie.getImage());
        seances = cinema.getAllSeances();
        ArrayList<String> horaires = new ArrayList<>();
        ArrayList<String> promotion = new ArrayList<>();
        int age = 0;

        promotion.add("Pas de promotion");



        if(profil != null) {
            LocalDate date1 = LocalDate.parse(profil.get_age());
            LocalDate date2 = LocalDate.now();
            age = date2.getYear() - date1.getYear();
            if (age <= 25) {
                promotion.add("Promotion jeune");
            } else if (age >= 65) {
                promotion.add("Promotion senior");
            }
            mailInput.setText(profil.get_mail());
        }
        promoChoiceBox.setItems(FXCollections.observableArrayList(promotion));


        horaireBox.setBackground(new Background(new BackgroundFill(Color.rgb(29,29,31), CornerRadii.EMPTY, Insets.EMPTY)));
        horaireBox.setStyle("-fx-background-color: #1d1d1d; -fx-border-color: #3f3f3f; -fx-border-width: 5px;");
        promoChoiceBox.setBackground(new Background(new BackgroundFill(Color.rgb(29,29,31), CornerRadii.EMPTY, Insets.EMPTY)));
        promoChoiceBox.setStyle("-fx-background-color: #1d1d1d; -fx-border-color: #3f3f3f; -fx-border-width: 5px;");


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
            dateS = newValue;
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
            horaireS = newValue;
            for(int i = 0; i< seances.size();i++)
            {
                if((seances.get(i).getDate().toString().equals(datePicker.valueProperty().getValue().toString())) && (seances.get(i).getHeure().equals(newValue.toString())))
                {
                    seanceS = seances.get(i);
                    prixLabel.setText((String.valueOf(seanceS.getPrix())));
                }
            }
        });

        promoChoiceBox.valueProperty().addListener((ov, oldValue, newValue) -> {

            if(newValue.toString().equals("Pas de promotion"))
            {
                prixFinal = seanceS.getPrix();
            }
            else if(newValue.toString().equals("Promotion jeune"))
            {
                prixFinal = (seanceS.getPrix()*(0.8));
            }
            else if(newValue.toString().equals("Promotion senior"))
            {
                prixFinal = (seanceS.getPrix()*(0.7));
            }
            prixFLabel.setText((String.valueOf(prixFinal)));
        });


    }


    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

    public void setProfil(Profil profil)
    {
        this.profil = profil;
    }

    @FXML
    public void reserver()
    {
        profil.ajouterReservation(new Reservation(movieS,horaireS,cinemaName,dateS.toString()));
    }
}
