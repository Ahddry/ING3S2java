package com.example.netflexe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

public class ReservationController {

    @FXML
    private Button Retour;

    @FXML
    private Label Title;

    private HelloApplication mainApp;
    private CinemaCollection collection = new CinemaCollection();

    @FXML
    private void retourMenu() {
        mainApp.showMainMenu();
    }

    private void initialize() {
        Title.setText("Disponible dans les cinémas suivant");

    }

    private void initialiseListView(ListView<String> listView1) {
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < collection.getSize(); i++) {
            items.add(collection.getName(i));
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
                    imageView.setFitHeight(173);
                    imageView.setFitWidth(118);
                    setText(null);

                    VBox myBox = new VBox(imageView, new Label(name));

                    myBox.setAlignment(Pos.BASELINE_CENTER);
                    setGraphic(myBox);
                }
            }
        });

    }



    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }
}
