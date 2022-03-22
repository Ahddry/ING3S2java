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
    private Cinema cinema;
    private String horaireS;
    private String promo;


    @FXML
    private void initialize() {



    }

    public void initializeBis(Movie movie, Cinema cinema)
    {
        this.cinema = cinema;
        movieS = movie;
        cinemaName = cinema.getName();
        image.setImage(movie.getImage());
        //seances = cinema.getAllSeances();
        /* A MODIF */
        for(var elem:cinema.getSalles())
        {
            seances = (ArrayList<Seance>) elem.getSeances();
        }


        ArrayList<String> horaires = new ArrayList<>();
        ArrayList<String> promotion = new ArrayList<>();
        int age = 0;

        promotion.add("Pas de promotion");

        promoChoiceBox.setVisible(false);

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
                setStyle("-fx-background-color: #ffc0cb;");
                for (Seance seance : seances) {
                    if (seance.getDate().toString().equals(date.toString())) {
                        setDisable(false);
                        setStyle("-fx-background-color: #cbc0ff;");
                    }
                }
            }
        });

        datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {
            horaires.clear();
            dateS = newValue;
            for (Seance seance : seances) {
                if ((seance.getName().equals(movie.getTitle())) && (seance.getDate().toString().equals(newValue.toString()))) {
                    horaires.add(seance.getHeure());
                }
            }
            horaireBox.setItems(FXCollections.observableArrayList(horaires));
        });

        horaireBox.valueProperty().addListener((ov, oldValue, newValue) -> {
            horaireS = newValue;
            for (Seance seance : seances) {
                if ((seance.getDate().toString().equals(datePicker.valueProperty().getValue().toString())) && (seance.getHeure().equals(newValue.toString()))) {
                    seanceS = seance;
                    prixLabel.setText((String.valueOf(seanceS.getPrix())));
                    promoChoiceBox.setVisible(true);
                }
            }
        });

        promoChoiceBox.valueProperty().addListener((ov, oldValue, newValue) -> {

            promo = newValue;
            switch (newValue.toString()) {
                case "Pas de promotion" -> prixFinal = seanceS.getPrix();
                case "Promotion jeune" -> prixFinal = (seanceS.getPrix() * (0.8));
                case "Promotion senior" -> prixFinal = (seanceS.getPrix() * (0.7));
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


        Mail mail = new Mail();
        mail.sendMail(new Reservation(movieS,horaireS,cinemaName,dateS.toString()), mailInput.getText());
        if(profil != null)
        {
            profil.ajouterReservation(new Reservation(movieS,horaireS,cinemaName,dateS.toString()));
            mainApp.showBiblioRes(profil);
            this.cinema.updateStatsPromo(promo);
            this.cinema.updateStatsFilm(movieS.getTitle());
        }
        else
        {
            mainApp.showMainMenu();
        }

    }
}
