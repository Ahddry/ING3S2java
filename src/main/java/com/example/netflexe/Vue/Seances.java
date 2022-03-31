package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Salle;
import com.example.netflexe.Model.Seance;
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

import java.util.List;
import java.util.Objects;

public class Seances
{
    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane grid;

    private Cinema cinema;
    private SceneController mainApp;
    private ComboBox<String> comboBox = new ComboBox<>();
    private boolean testCharge;

    /**
     * Méthode d'initialisation de la vue
     *
     * @param c Cinéma à afficher
     */
    public void init(Cinema c)
    {
        if (testCharge)
        {
            grid = new GridPane();
            grid.minHeight(900.0);
            grid.minWidth(1152.0);
            grid.setStyle("-fx-background-color: #1d1d1d; ");
            ColumnConstraints col1 = new ColumnConstraints();
            col1.setPercentWidth(5);
            col1.setMaxWidth(84);
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setPercentWidth(90);
            col2.setPrefWidth(1000);
            ColumnConstraints col3 = new ColumnConstraints();
            col3.setPercentWidth(5);
            col3.setMaxWidth(200);
            grid.getColumnConstraints().addAll(col1, col2, col3);
            cinema = c;

            Label nomCine = new Label(cinema.getName()); //Nom du cinéma affiché en haut de la page
            nomCine.setStyle("-fx-font-size: 32pt; -fx-font-family: \"Segoe UI Light\"; -fx-text-fill: white; -fx-opacity: 1;");
            grid.addRow(0);
            grid.add(nomCine, 1, 0);

            int compteur = 1;
            List<Salle> salles = c.getSalles(); //Salles du cinéma
            for (var salle : salles) //Pour chaque salle
            {
                Label numSalle = new Label("Salle " + salle.getNumero() + " :"); //Nom de la salle
                grid.addRow(compteur);
                grid.add(numSalle, 1, compteur);
                compteur++;
                List<Seance> collection = salle.getSeances();
                ObservableList<String> items = FXCollections.observableArrayList();
                for (var elem : collection)
                {
                    elem.setImage();
                    String nomSeance = elem.getName();
                    items.add(nomSeance);
                }
                ListView<String> listView = new ListView<>();
                listView.setItems(items);
                listView.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                listView.setCellFactory(param -> new ListCell<String>()
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
                            String tempName = "", tempDate = "", tempHeure = "";
                            imageView.setImage(salle.getImage(name));
                            imageView.setFitHeight(250);
                            imageView.setFitWidth(171);
                            setText(null);
                            if (name.length() > 25)
                            {
                                tempName = (name.substring(0, 25) + "...");
                            } else
                            {
                                tempName = name;
                            }
                            for (var seance : salle.getSeances())
                            {
                                if (Objects.equals(seance.getName(), name))
                                {
                                    tempDate = seance.getDate().toString();
                                    tempHeure = seance.getHeure().split("\\.")[0];
                                }
                            }
                            VBox myBox = new VBox(imageView, new Label(tempName), new Label(tempDate), new Label(tempHeure));
                            myBox.setAlignment(Pos.BASELINE_CENTER);
                            setGraphic(myBox);
                        }
                    }
                });
                ContextMenu contextMenu = new ContextMenu();
                MenuItem suppr = new MenuItem("Supprimer le film");
                suppr.setOnAction(event ->
                {
                    /* https://stackoverflow.com/questions/11662857/javafx-2-1-messagebox */
                    String selectedName = listView.getSelectionModel().getSelectedItem();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer ce film ?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait().ifPresent(rs ->
                    {
                        if (rs == ButtonType.YES)
                        {
                            Seance s = salle.getSeance(selectedName);
                            if (salle.deleteSeance(selectedName))
                            {
                                cinema.setSalles(salles);
                                mainApp.getHello().SupprimerUnseSeanceBDD(s.get_idSeance());
                                mainApp.showSeances();
                            } else
                            {
                                Alert alert2 = new Alert(Alert.AlertType.ERROR, "La séance n'a pas pu être supprimé.");
                                alert2.show();
                            }
                        }
                    });
                });
                contextMenu.getItems().add(suppr);

                listView.setOnMouseClicked(event ->
                {
                    if (event.getButton() == MouseButton.SECONDARY)
                    {
                        contextMenu.show(mainApp.getSceneAdmin().getWindow(), event.getScreenX(), event.getScreenY());
                    }
                });

                listView.setOrientation(Orientation.HORIZONTAL);
                listView.setStyle("-fx-base: #1d1d1d; -fx-control-inner-background: #1d1d1d; -fx-background-color: #1d1d1d; -fx-table-cell-border-color: transparent;\n" +
                        " -fx-table-header-border-color: transparent; -fx-padding: 5;");
                listView.setPrefSize(300.0, 340.0);
                grid.addRow(compteur);
                grid.add(listView, 1, compteur);
                compteur++;
            }

            Button nouvelleSalle = new Button("Ajouter une salle"); //Bouton pour l'ajout d'une nouvelle salle
            nouvelleSalle.setTooltip(new Tooltip("Cliquez ici pour ajouter une salle au cinéma."));
            nouvelleSalle.setOnAction(actionEvent -> ajoutSalleBoutonClick());
            nouvelleSalle.setAlignment(Pos.CENTER);
            Button retirerSalle = new Button("Supprimer une salle"); //Bouton pour l'ajout d'une nouvelle salle
            retirerSalle.setTooltip(new Tooltip("Cliquez ici pour supprimer une salle au cinéma."));
            retirerSalle.setOnAction(actionEvent -> retirerSalleBoutonClick());
            retirerSalle.setAlignment(Pos.CENTER);
            comboBox = new ComboBox<>();
            comboBox.setStyle("-fx-background-color: BLACK; -fx-border-color: GREY; -fx-border-radius: 6; -fx-text-color: WHITE; -fx-control-inner-background-fx-control-inner-background: WHITE;");
            for (var salle : salles)
                comboBox.getItems().add("Salle " + salle.getNumero());
            FlowPane flowPane = new FlowPane(nouvelleSalle, new Label("  "), retirerSalle, comboBox);
            flowPane.setOrientation(Orientation.HORIZONTAL);
            grid.addRow(compteur);
            grid.add(flowPane, 1, compteur);
            RowConstraints last = new RowConstraints();
            last.setPrefHeight(50);
            grid.getRowConstraints().add(last);
            Label labelVide = new Label("");
            grid.add(labelVide, 0, compteur + 1);
            pane.getChildren().setAll(grid);
            testCharge = false;
        }

    }

    public void ajoutSalleBoutonClick()
    {
        int numSalle = 1;
        while (cinema.contains(numSalle))
            numSalle++;

        int id = mainApp.getHello().AjouterSalleCinema_into_bdd(250, numSalle, cinema.get_id_cine());
        cinema.addSalles(new Salle(id, numSalle, 50));
        mainApp.setCinemaAdmin(this.cinema);
        mainApp.showSeances();
    }

    public void retirerSalleBoutonClick()
    {
        if (comboBox.getValue() != null)
        {
            int numSalle = Integer.parseInt(comboBox.getValue().split(" ")[1]);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Voulez-vous vraiment supprimer la salle " + numSalle + " et toutes ces séances ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(rs ->
            {
                if (rs == ButtonType.YES)
                {
                    Salle s = new Salle();
                    for (var salle : cinema.getSalles())
                    {
                        if (salle.getNumero() == numSalle)
                        {
                            s = salle;
                            break;
                        }
                    }
                    if (cinema.deleteSalles(numSalle))
                    {
                        System.out.println("Salle : " + s.getNumero() + " id : " + s.get_id_bdd());

                        mainApp.getHello().SupprimerUneSalleBDD(s.get_id_bdd());
                        mainApp.setCinemaAdmin(this.cinema);
                        mainApp.showSeances();
                    } else
                    {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR, "La salle n'a pas pu être supprimé.");
                        alert2.show();
                    }
                }
            });
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous n'avez pas sélectionné de salle à supprimer", ButtonType.OK);
            alert.show();
        }

    }

    public Seances()
    {
        testCharge = true;
    }

    public void setTestCharge(boolean testCharge)
    {
        this.testCharge = testCharge;
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
