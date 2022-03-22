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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;


public class FilmInfoController {

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

    private SceneController mainApp;
    private Movie movieS;
    private Actor ActorS;
    private Profil monProfil;
    private boolean adminAccess;
    private Cinema monCinema;
    private ActorCollection collectionActor;

    private ActorCollection collection = new ActorCollection();

    @FXML
    private void initialize() {
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
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
        for(int i = 0; i< items.size();i++)
        {
           // System.out.println(items.get(i));
        }
        listView1.setItems(items);
        listView1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        System.out.println("azerty");

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
                    System.out.println("1234567890");
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
        //listView1.setOnMouseClicked(event -> {
            /*if (event.getClickCount() == 2  ) {
                //String selectedName = listView1.getSelectionModel().getSelectedItem();

                Actor acteur = collectionActor.getActor(selectedName);
                try {
                    mainApp.showInfoActor(acteur);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        //});

        listView1.setOrientation(Orientation.HORIZONTAL);
    }
}
