package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.CinemaCollection;
import com.example.netflexe.Model.Movie;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ReservationVue {

    @FXML
    private Button Retour;

    @FXML
    private Label Title;

    @FXML
    private ListView<String> listView;

    private SceneController mainApp;
    private CinemaCollection collection = new CinemaCollection();
    private Movie movie = new Movie();

    @FXML
    private void retourMenu() {
        mainApp.showMainMenu();
    }

    @FXML
    private void initialize() {

        Title.setText("Disponible dans les cinémas suivant");

    }

    /**
     * crééer la liste dans laquelle les cinéms seront affichés
     * @param collection collection de tous les cinémas
     * @param movie film que l'on souhaite réserver
     */
    public void initializeBis(CinemaCollection collection,Movie movie)
    {
        this.movie = movie;
        this.collection = collection;
        initialiseListView(listView);
    }

    /**
     * on remplit la liste avec les cinémas qui ont le film à l'affiche
     * @param listView1 list que l'on va remplir
     */
    private void initialiseListView(ListView<String> listView1) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < collection.getSize(); i++) {
            if(collection.getCinema(i).checkMovie(movie.getTitle()))
            {
                items.add(collection.getName(i));
            }

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
                    imageView.setImage(collection.getImage(name));
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(300);
                    setText(null);

                    VBox myBox = new VBox(imageView, new Label(name));

                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });

        listView1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2  ) {
                String selectedName = listView1.getSelectionModel().getSelectedItem();

                mainApp.showValiderReseravtion(this.movie, collection.getCinema(selectedName),mainApp.getProfil());
            }
        });


        listView1.setOrientation(Orientation.HORIZONTAL);

    }



    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }
}
