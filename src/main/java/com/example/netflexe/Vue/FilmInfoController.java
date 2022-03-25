package com.example.netflexe.Vue;

import com.example.netflexe.Model.ActorCollection;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Movie;
import com.example.netflexe.Model.Profil;
import com.example.netflexe.Model.Actor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;


public class FilmInfoController {
    private WebEngine engine;
    @FXML
    private Button Retour;
    @FXML
    private Button LikeButton;

    @FXML
    private Label Title;

    @FXML
    private ImageView Poster;

    @FXML
    private Button ReserverButton;

    @FXML
    private Label DateDeSortie;

    @FXML
    private Label Duree;

    @FXML
    private Label Synopsis;

    @FXML
    private Label Slogan;

    @FXML
    private  Label Nom;

    @FXML
    private Label Age;

    @FXML
    private Label Description;

    @FXML Label Role;

    @FXML ImageView ImageActeur;

    @FXML
    private ListView<String> listView;
    @FXML
    private Label Label;
    @FXML
    private AnchorPane trailer_page;
    @FXML
    private Button trailer_button;
    @FXML
    private Button trailer_close;
    @FXML
    private WebView youtube;
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane Acteur;

    @FXML
    private Button ActeurClose;


    private SceneController mainApp;
    private BiblioController biblioController;
    private Movie movieS;
    private Actor ActorS;
    private Profil monProfil;
    private boolean adminAccess;
    private Cinema monCinema;
    private ActorCollection collectionActor;

    private ActorCollection collection = new ActorCollection();

    @FXML
    private void initialize() {
        trailer_button.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                this.trailer_page.setVisible(true);
                this.root.setVisible(false);
            }
        });
        trailer_close.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                this.trailer_page.setVisible(false);
                this.root.setVisible(true);
            }
        });
        this.engine = this.youtube.getEngine();
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }
    public void setBiblioController(BiblioController biblioController) {
        this.biblioController = biblioController;
    }

    @FXML
    private void retourMenu() {
        if (adminAccess)
            mainApp.showAccueilAdmin();
        else
            mainApp.showMainMenu();
    }

    public void setMovie(Movie movie)
    {

        //System.out.print(movie.getTitle());
        Title.setText(movie.getTitle());
        Poster.setImage(movie.getImage());
        DateDeSortie.setText(movie.getDate_de_sortie_S());
        Duree.setText(movie.getDuree());
        Synopsis.setText(movie.getSynopsis());
        Slogan.setText(movie.getSlogan());
        if(movie.getTrailer() != null)
        {
            this.engine.load("https://www.youtube.com/embed/" + movie.getTrailer());
        }
        else
        {
            this.trailer_button.setVisible(false);
        }
        movieS = movie;
    }

    public void setActor(Actor actor)
    {
        Nom.setText(actor.getName());
        Age.setText(actor.getAge());
        Description.setText(actor.getDescription());
        Role.setText(actor.getRole());
        ImageActeur.setImage(actor.getImageActeur());
    }

    public void setProfil(Profil profil)
    {
        monProfil = profil;
        if(monProfil == null)
        {
            LikeButton.setVisible(false);
        }
    }

    @FXML
    public void addLike()
    {
        if (!adminAccess)
            monProfil.ajouterLike(movieS);
        if(biblioController!= null)
        {
            biblioController.initializeBis(monProfil);
        }

    }

    @FXML
    public void startReservation()
    {
        if (!adminAccess)
            mainApp.showReservation(movieS);
        else
        {
            if (!monCinema.checkMovie(movieS.getTitle()))
            {
                monCinema.ajoutFilm(movieS);
                mainApp.setCinemaAdmin(monCinema);
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Ce film est déjà à l'affiche dans votre cinéma.", ButtonType.OK);
                alert.show();
            }

        }
    }

    public void setCinema(Cinema cinema)
    {
        monCinema = cinema;
    }

    public void setAdminAccess(boolean admin)
    {
        adminAccess = admin;
        if (adminAccess)
        {
            ReserverButton.setText("Ajouter le fim au cinéma");
            LikeButton.setVisible(false);
        }
    }

    public void showActors(ActorCollection collectionActor)
    {
        this.collectionActor = collectionActor;
        collectionActor.setImage();
        initializeListView(listView);
    }

    public void initializeListView(ListView<String> listView1){

        ObservableList<String> items = FXCollections.observableArrayList ();
        for(int i = 0 ; i < collectionActor.getSize(); i++)
        {
            items.add(collectionActor.getName(i)) ;

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

                    imageView.setImage(collectionActor.getImage(name));
                    imageView.setFitHeight(160);
                    imageView.setFitWidth(120);
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
                this.Acteur.setVisible(true);
                this.root.setVisible(false);
                String selectedName = listView1.getSelectionModel().getSelectedItem();
                System.out.println(selectedName);
                Actor acteur = collectionActor.getActor(selectedName);
                try {
                    mainApp.showInfoActor(acteur);
                    ImageActeur.setImage(acteur.getImageActeur());
                    Age.setText(acteur.getAge());
                    Nom.setText(acteur.getName());
                    Description.setText(acteur.getDescription());
                    Role.setText(acteur.getRole());
                    ActeurClose.setOnMouseClicked(event2 -> {
                        if(event2.getClickCount() == 1)
                        {
                            this.Acteur.setVisible(false);
                            this.root.setVisible(true);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        listView1.setOrientation(Orientation.HORIZONTAL);
    }
}
