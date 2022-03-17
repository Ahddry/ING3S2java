package com.example.netflexe.Vue;

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
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import com.example.netflexe.Model.Movie;
public class ResearchController {

    @FXML
    private Button validerButton;

    @FXML
    private TextField barreRecherche;

    @FXML
    private ListView listview;

    private SceneController mainApp;

    private boolean adminSelect = false;

    private MovieCollection[] collection = { new MovieCollection()};
    private String ref = "";

    @FXML
    private void initialize() {

        validerButton.setText("Valider");
    }

    public void initializeBis()
    {
        collection = mainApp.getMovieCollection(0);
        initialiseListView(listview);

        barreRecherche.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ENTER)) {
                    ref =barreRecherche.getText();
                    initialiseListView(listview);
                }
            }
        });
    }

    private void initialiseListView(ListView<String> listView1)
    {

        ObservableList<String> items = FXCollections.observableArrayList ();
        for(int i = 0 ; i < collection[5].getSize(); i++)
        {
            if(collection[5].getName(i).contains(ref))
            {
                items.add(collection[5].getName(i)) ;
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
                    imageView.setFitHeight(432);
                    imageView.setFitWidth(295);
                    imageView.setImage(collection[5].getImage(name));
                    setText(null);
                    VBox myBox = new VBox(imageView,new Label(name));

                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });

        listView1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2  ) {
                String selectedName = listView1.getSelectionModel().getSelectedItem();

                Movie movie = collection[5].getMovie(selectedName);

                mainApp.showInfo(movie, adminSelect);

            }
        });

        listView1.setOrientation(Orientation.HORIZONTAL);
    }

    @FXML
    public void validationBarre()
    {
        ref =barreRecherche.getText();
        this.initialiseListView(listview);
    }


    public void setAdminSelect(boolean adminSelect)
    {
        this.adminSelect = adminSelect;
    }

    public void setMainApp(SceneController mainApp) {
        this.mainApp = mainApp;
    }

}
