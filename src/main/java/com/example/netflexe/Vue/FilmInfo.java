package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.*;

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


public class FilmInfo {
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
    @FXML
    private Label real;
    @FXML
    private Button dejavu;


    private SceneController mainApp;
    private Biblio biblioController;
    private Movie movieS;
    private Profil monProfil;
    private boolean adminAccess;
    private Cinema monCinema;
    private ActorCollection collectionActor;

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
    public void setBiblioController(Biblio biblioController) {
        this.biblioController = biblioController;
    }

    @FXML
    private void retourMenu() {
        if (adminAccess)
            mainApp.showAccueilAdmin();
        else
            mainApp.showMainMenu();
    }

    /**
     * Méthode qui permet de set le film a afficher et lance l'engine pour l'affichage de trailer
     * @param movie
     */
    public void setMovie(Movie movie)
    {

        //System.out.print(movie.getTitle());
        Title.setText(movie.getTitle());
        Poster.setImage(movie.getImage());
        DateDeSortie.setText(movie.getDate_de_sortie_S());
        Duree.setText(movie.getDuree());
        Synopsis.setText(movie.getSynopsis());
        Slogan.setText(movie.getSlogan());
        real.setText(movie.getDirector());
        if(movie.getTrailer() != null)
        {
            this.trailer_button.setVisible(true);
            this.engine.load("https://www.youtube.com/embed/" + movie.getTrailer());
        }
        else
        {
            this.trailer_button.setVisible(false);
        }
        movieS = movie;
    }

    /**
     * Méthode qui set l'acteur a afficher pour les informations plus détaillé
     * @param actor acteur a affiché
     */
    public void setActor(Actor actor)
    {
        Nom.setText(actor.getName());
        Age.setText(actor.getAge());
        Description.setText(actor.getDescription());
        Role.setText(actor.getRole());
        ImageActeur.setImage(actor.getImageActeur());
    }

    /**
     * Méthode qui permet de set le profil de l'utilisateur
     * @param profil
     */
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
            this.mainApp.getHello().add_like(monProfil.get_id(), movieS.get_idFilm());
        if(biblioController!= null)
        {
            biblioController.initializeBis(monProfil);
        }

    }

    @FXML
    public void addDejaVU()
    {
        if (!adminAccess)
            monProfil.ajouterDejaVu(movieS);
            this.mainApp.getHello().set_deja_vu(monProfil.get_id(), Integer.valueOf(movieS.get_idFilm()));
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

    /**
     * Méthode qui change l'affichage en fonction de si l'utilisateur est un admin ou si il est un utilisateur sur l'application
     * @param admin boolean qui indique si la page est affiché depuis user ou admin
     */
    public void setAdminAccess(boolean admin)
    {
        adminAccess = admin;
        if (adminAccess)
        {
            ReserverButton.setText("Ajouter le fim au cinéma");
            LikeButton.setVisible(false);
            dejavu.setVisible(false);
        }
        else
        {
            ReserverButton.setText("Réserver");
            LikeButton.setVisible(true);
            dejavu.setVisible(true);
        }
    }

    /**
     * Méthode qui affiche une liste view d'acteur en passant par un autre thread pour faire le chargement des images
     * @param collectionActor collection d'acteur a afficher
     */
    public void showActors(ActorCollection collectionActor)
    {
        this.collectionActor = collectionActor;
        RunnableDemoActor thread = new RunnableDemoActor("monThread");
        thread.setActorCollection(this.collectionActor);
        thread.setMainApp(this);
        thread.start();
        initializeListView(listView);
    }

    /**
     * Initialisation de la ListView comprenant la liste des acteurs d'un film affichant leur nom (complet) et leur photo
     * Evénement du double clique implémenté de telle sorte qu'une page s'affiche avec toutes les informations de l'acteur concerné
     * @param listView1 ListView de la liste des acteurs d'un film
     */
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

    public void refresher() {

            listView.refresh();

    }
}
