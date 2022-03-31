package com.example.netflexe.Vue;

import com.example.netflexe.Controller.HelloApplication;
import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.ActorCollection;
import com.example.netflexe.Model.MovieCollection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import com.example.netflexe.Model.Movie;

import java.io.IOException;

public class MainMenu {

    @FXML
    private ListView<String> listView1;
    @FXML
    private ListView<String> listView2;
    @FXML
    private ListView<String> listView3;
    @FXML
    private ListView<String> listView4;
    @FXML
    private ListView<String> listView5;
    @FXML
    private Label mainTitle;
    @FXML
    private Button ResearchButton;

    @FXML
    private Label Label1;
    @FXML
    private Label Label2;
    @FXML
    private Label Label3;
    @FXML
    private Label Label4;
    @FXML
    private Label Label5;

    private SceneController mainApp;


    private MovieCollection[] collection = {new MovieCollection()};
    private ActorCollection collectionActor = new ActorCollection();
    private HelloApplication controller;

    @FXML
    private void initialize() {

        mainTitle.setText("Accueil");
        ResearchButton.setText("Rechercher");
        Label1.setText("Action");
        Label2.setText("Science-Fiction");
        Label3.setText("Aventure");
        Label4.setText("Animation");
        Label5.setText("Comédie");
    }

    public void initializeBis()
    {



        initialiseListView(listView1,0);
        initialiseListView(listView2,1);
        initialiseListView(listView3,2);
        initialiseListView(listView4,3);
        initialiseListView(listView5,4);
        if(this.mainApp.getProfil() != null)
        {
            Label1.setText(this.mainApp.getProfil().get_genre(0));
            Label2.setText(this.mainApp.getProfil().get_genre(1));
            Label3.setText(this.mainApp.getProfil().get_genre(2));
            Label4.setText(this.mainApp.getProfil().get_genre(3));
            Label5.setText(this.mainApp.getProfil().get_genre(4));
        }
    }

    public void refresher(int i)
    {
        if(i == 0)
        {
            listView1.refresh();
        }
        else if(i == 1)
        {
            listView2.refresh();
        }
        else if(i == 2)
        {
            listView3.refresh();
        }
        else if(i == 3)
        {
            listView4.refresh();
        }
        else if(i == 4)
        {
            listView5.refresh();
        }
    }
    public void refresher()
    {
        listView1.refresh();
        listView2.refresh();
        listView3.refresh();
        listView4.refresh();
        listView5.refresh();
    }

    private void initialiseListView(ListView<String> listView1, int j)
    {
        collection = mainApp.getMovieCollection(j);

        ObservableList<String> items = FXCollections.observableArrayList ();
        for(int i = 0 ; i < collection[j].getSize(); i++)
        {
            items.add(collection[j].getName(i)) ;
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
                    imageView.setImage(collection[j].getImage(name));
                    imageView.setFitHeight(160);
                    imageView.setFitWidth(110);
                    setText(null);
                    if(name.length() > 15)
                    {
                        tempName = (name.substring(0,12) + "...");
                    }
                    else{
                        tempName = name;
                    }
                    VBox myBox = new VBox(imageView,new Label(tempName));
                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });

        listView1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2  ) {
                String selectedName = listView1.getSelectionModel().getSelectedItem();

                Movie movie = collection[j].getMovie(selectedName);
                String id_film = movie.get_idFilm();
                try {
                    collectionActor = this.controller.CollectionActeursMovie(id_film);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mainApp.showInfo(movie, collectionActor, false);
            }
        });

        listView1.setOrientation(Orientation.HORIZONTAL);
    }

    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
    }

    @FXML
    private void lancerRecherche() {
        mainApp.showResearch(false);
    }

}

