package com.example.netflexe.Vue;


import com.example.netflexe.Model.MovieCollection;
import com.example.netflexe.Model.Profil;

import com.example.netflexe.Model.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import com.example.netflexe.Model.Movie;

import java.util.ArrayList;

public class BiblioReserv {

    @FXML
    private ListView listView1;
    @FXML
    private ListView listView2;
    @FXML
    private ListView listView3;
    @FXML
    private ListView listView4;
    @FXML
    private ListView listView5;
    @FXML
    private Label mainTitle;


    private SceneController mainApp;


    private ArrayList<Reservation> collection = new ArrayList<Reservation> ();

    @FXML
    private void initialize() {

        mainTitle.setText("Réservations :");
    }

    public void initializeBis(Profil monProfil)
    {
        collection = monProfil.getFilmRes();
        initialiseListView(listView1);

    }

    private void initialiseListView(ListView<String> listView1)
    {
        ObservableList<String> items = FXCollections.observableArrayList ();
        for(int i = 0 ; i < collection.size(); i++)
        {
            items.add(collection.get(i).getMovie().getTitle()) ;
        }
        listView1.setItems(items);
        listView1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        listView1.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {

                super.updateItem(name, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String tempName = "";
                    String tempHoraire = "";
                    String tempDate = "";
                    String tempNom = "";

                    for(int i = 0; i< collection.size();i++)
                    {
                        if(collection.get(i).getMovie().getTitle() == name)
                        {
                            imageView.setImage(collection.get(i).getMovie().getImage());
                            tempName = name;
                            tempDate = collection.get(i).getDate();
                            tempHoraire =    collection.get(i).getHoraire() ;
                            tempNom =  collection.get(i).getNomCinema();
                            System.out.print(tempName);
                        }
                    }
                    imageView.setFitHeight(400);
                    imageView.setFitWidth(250);
                    setText(null);

                    VBox myBox = new VBox(imageView,new Label(tempName), new Label(tempDate), new Label(tempHoraire), new Label(tempNom));
                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });



        listView1.setOrientation(Orientation.HORIZONTAL);
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }


}
