package com.example.netflexe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BiblioController {

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


    private HelloApplication mainApp;


    private MovieCollection collection = new MovieCollection();

    @FXML
    private void initialize() {

        mainTitle.setText("Bibliothèque");
    }

    public void initializeBis(Profil monProfil)
    {
        collection = monProfil.getFilmLike();
        initialiseListView(listView1);



    }

    private void initialiseListView(ListView<String> listView1)
    {
        ObservableList<String> items = FXCollections.observableArrayList ();
        for(int i = 0 ; i < collection.getSize(); i++)
        {
            items.add(collection.getName(i)) ;
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
                    imageView.setImage(collection.getImage(name));
                    imageView.setFitHeight(173);
                    imageView.setFitWidth(118);
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

                Movie movie = collection.getMovie(selectedName);

                mainApp.showInfo(movie);
            }
        });

        listView1.setOrientation(Orientation.HORIZONTAL);
    }

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }


}







