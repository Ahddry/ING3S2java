package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Movie;
import com.example.netflexe.Model.MovieCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.HashSet;
import java.util.Set;

public class AccueilAdmin
{
    @FXML
    private Label nomCinema;
    @FXML
    private ListView<String> listView1 = new ListView<>();
    @FXML
    private Button ajoutFilmBouton;
    @FXML
    private GridPane promotions;
    @FXML
    private AnchorPane conteneurPromo;


    private Cinema cinema;
    private SceneController mainApp;
    private MovieCollection collection;
    private ContextMenu contextMenu = new ContextMenu();

    public void init(Cinema c)
    {
        ajoutFilmBouton.setTooltip(new Tooltip("Cliquez ici pour ajouter un film."));
        cinema = c;
        nomCinema.setText(cinema.getName());
        collection = c.getFilmP();
        Set<String> filmsAffiche = new HashSet<>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < collection.getSize(); i++)
        {
            filmsAffiche.add(collection.getName(i));
        }
        items.addAll(filmsAffiche);
        listView1.setItems(items);
        listView1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        listView1.setCellFactory(param -> new ListCell<String>()
        {
            private final ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty)
            {
                super.updateItem(name, empty);
                if (empty)
                {
                    setText(null);
                    setGraphic(null);
                } else
                {
                    String tempName = "";
                    imageView.setImage(collection.getImage(name));
                    imageView.setFitHeight(160);
                    imageView.setFitWidth(110);
                    setText(null);
                    if (name.length() > 15)
                    {
                        tempName = (name.substring(0, 12) + "...");
                    } else
                    {
                        tempName = name;
                    }
                    VBox myBox = new VBox(imageView, new Label(tempName));
                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });

        MenuItem suppr = new MenuItem("Supprimer le film");
        suppr.setOnAction(event ->
        {
            /* https://stackoverflow.com/questions/11662857/javafx-2-1-messagebox */
            String selectedName = listView1.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce film ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(resultat ->
            {
                if (resultat == ButtonType.YES)
                {
                    if (collection.deleteMovie(selectedName))
                    {
                        cinema.setFilmP(collection);
                        mainApp.showAccueilAdmin();
                    } else
                    {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR, "Le film n'a pas pu être supprimé.");
                        alert2.show();
                    }
                }
            });
        });
        //contextMenu.getItems().add(suppr);
        contextMenu = new ContextMenu(suppr);

        listView1.setOnMouseClicked(event ->
        {
            if (event.getClickCount() == 2)
            {
                String selectedName = listView1.getSelectionModel().getSelectedItem();
                Movie movie = collection.getMovie(selectedName);
                mainApp.showCreationSeance(movie);
            } else if (event.getButton() == MouseButton.SECONDARY)
            {
                contextMenu.show(mainApp.getSceneAdmin().getWindow(), event.getScreenX(), event.getScreenY());
            }
        });



        listView1.setOrientation(Orientation.HORIZONTAL);
        promotions = new GridPane();
        promotions.minHeight(300);
        promotions.minWidth(1100);
        promotions.setStyle("-fx-background-color: #1d1d1d; ");
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        col1.setPrefWidth(200);
        col1.setMinWidth(150);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);
        col2.setPrefWidth(200);
        col2.setMinWidth(150);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(15);
        col3.setPrefWidth(200);
        col3.setMinWidth(150);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(15);
        col4.setPrefWidth(200);
        col4.setMinWidth(150);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(20);
        col5.setPrefWidth(200);
        col5.setMinWidth(150);
        promotions.getColumnConstraints().addAll(col1, col2, col3, col4, col5);
        promotions.addRow(0);
        if(!cinema.get_promos().isEmpty())
        {
            promotions.add(new Label("Nom"), 0, 0);
            promotions.add(new Label("% de réduction"), 1, 0);
            promotions.add(new Label("Age minimum"), 2, 0);
            promotions.add(new Label("Age maximum"), 3, 0);
        }
        int compteur = 1;
        for (var promo : cinema.get_promos())
        {
            promotions.addRow(compteur);
            promotions.add(new Label(promo.get_nomPromo()), 0, compteur);
            promotions.add(new Label(promo.get_pourcentage() * 100 + "%"), 1, compteur);
            promotions.add(new Label(promo.get_minAge() + ""), 2, compteur);
            promotions.add(new Label(promo.get_maxAge() + ""), 3, compteur);
            Button supprimerPromo = new Button("Supprimer");
            supprimerPromo.setTooltip(new Tooltip("Supprimer la promotion."));
            supprimerPromo.setOnAction(actionEvent -> supprimerBoutonClick(promo.get_idPromo()));
            supprimerPromo.setAlignment(Pos.CENTER);
            promotions.add(supprimerPromo, 4, compteur);
            compteur++;
        }
        promotions.addRow(compteur);
        Button ajouterPromo = new Button("Ajouter");
        ajouterPromo.setTooltip(new Tooltip("Ajouter une promotion."));
        ajouterPromo.setOnAction(actionEvent -> ajouterPromoClick());
        ajouterPromo.setAlignment(Pos.CENTER);
        promotions.add(ajouterPromo, 0, compteur);
        conteneurPromo.getChildren().setAll(promotions);
    }

    public void supprimerBoutonClick(int idPromo)
    {
        cinema.deletePromo(idPromo);
        mainApp.getHello().suppr_promotion(idPromo);
        mainApp.showAccueilAdmin();
    }

    public void ajouterPromoClick()
    {
        mainApp.showCreationPromo(cinema);
    }

    public void ajoutFilmBoutonClick()
    {
        mainApp.showAjouterFilm(cinema);
    }

    public Cinema getCinema()
    {
        return cinema;
    }

    public void setMainApp(SceneController mainApp)
    {
        this.mainApp = mainApp;
    }
}
