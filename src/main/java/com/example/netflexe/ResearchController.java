package com.example.netflexe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class ResearchController {

    @FXML
    private Button validerButton;

    @FXML
    private TextField barreRecherche;

    @FXML
    private ListView listview;

    private HelloApplication mainApp;

    private MovieCollection collection = new MovieCollection();
    private String ref = "";

    @FXML
    private void initialize() {

        validerButton.setText("Valider");
    }

    public void initializeBis()
    {
        collection = mainApp.getMovieCollection(0);
        initialiseListView(listview);
    }

    private void initialiseListView(ListView<String> listView1)
    {

        ObservableList<String> items = FXCollections.observableArrayList ();
        for(int i = 0 ; i < collection.getSize(); i++)
        {
            if(collection.getName(i).contains(ref))
            {
                items.add(collection.getName(i)) ;
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
                    imageView.setFitHeight(346);
                    imageView.setFitWidth(236);
                    imageView.setImage(collection.getImage(name));
                    setText(null);
                    VBox myBox = new VBox(imageView,new Label(name));
                    myBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });

        listView1.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2  ) {
                String selectedName = listView1.getSelectionModel().getSelectedItem();

                Movie movie = collection.getMovie(selectedName);

                mainApp.showInfo(movie);
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

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }

}
