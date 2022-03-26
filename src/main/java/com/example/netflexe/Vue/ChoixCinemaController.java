package com.example.netflexe.Vue;

import com.example.netflexe.Model.CinemaCollection;
import com.example.netflexe.Model.Movie;
import com.example.netflexe.Model.Profil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChoixCinemaController
{

    @FXML
    private ListView<String> listView;

    private SceneController mainApp;
    private Profil profil;

    public void init(CinemaCollection collection, Profil profil)
    {
        this.profil = profil;
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < collection.getSize(); i++)
        {
            items.add(collection.getName(i));

        }
        listView.setItems(items);
        listView.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        listView.setCellFactory(param -> new ListCell<String>()
        {
            private ImageView imageView = new ImageView();

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

        listView.setOnMouseClicked(event ->
        {
            if (event.getClickCount() == 2)
            {
                String selectedName = (String) listView.getSelectionModel().getSelectedItem();
                profil.setCinema(collection.getCinema(selectedName));
                mainApp.setProfil(profil);
                mainApp.setCinemaAdmin(collection.getCinema(selectedName));

                mainApp.getHello().AssignCinema(profil.get_id(), collection.getCinema(selectedName).get_id_cine());

                mainApp.showAccueilAdmin();
            }
        });
        listView.setOrientation(Orientation.HORIZONTAL);
    }

    public void nouveauCinemaBoutonClick()
    {
        mainApp.showCreationCinema(profil);
    }

    @FXML
    private void retourMenu() {
        mainApp.showMainMenu();
    }

    public void setMainApp(SceneController mainApp)
    {
        this.mainApp = mainApp;
    }
}
