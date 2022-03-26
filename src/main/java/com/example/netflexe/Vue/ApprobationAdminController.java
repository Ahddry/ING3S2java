package com.example.netflexe.Vue;

import com.example.netflexe.Model.Profil;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ApprobationAdminController
{
    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane grid;
    @FXML
    private Button retourBouton;

    private ArrayList<Profil> demandesEnAttente;
    private SceneController mainApp;

    public void init(SceneController mainApp)
    {
        this.mainApp = mainApp;
        grid = new GridPane();
        grid.minHeight(900.0);
        grid.minWidth(1152.0);
        grid.setStyle("-fx-background-color: #1d1d1d; ");
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(5);
        col0.setMaxWidth(84);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(5);
        col1.setPrefWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(15);
        col2.setPrefWidth(200);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(15);
        col3.setPrefWidth(200);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        col4.setPrefWidth(300);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(7.5);
        col5.setPrefWidth(100);
        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPercentWidth(7.5);
        col6.setPrefWidth(100);
        grid.getColumnConstraints().addAll(col0, col1, col2, col3, col4, col5, col6);

        demandesEnAttente = mainApp.getHello().getAttenteAdmin();

        grid.addRow(0);
        grid.add(new Label("Id user"), 1, 0);
        grid.add(new Label("Nom"), 2, 0);
        grid.add(new Label("Prénom"), 3, 0);
        grid.add(new Label("Login"), 4, 0);

        int compteur = 1;
        for (var profil : demandesEnAttente)
        {
            grid.addRow(compteur);
            grid.add(new Label(profil.get_id() + ""), 1, compteur);
            grid.add(new Label(profil.get_nom()), 2, compteur);
            grid.add(new Label(profil.get_prenom()), 3, compteur);
            grid.add(new Label(profil.get_mail()), 4, compteur);
            Button accepter = new Button("Accepter"); //Bouton pour l'ajout d'une nouvelle salle
            accepter.setTooltip(new Tooltip("Cliquez ici pour rendre l'utilisateur administrateur."));
            accepter.setOnAction(actionEvent -> accepterBoutonClick(profil));
            accepter.setAlignment(Pos.CENTER);
            Button refuser = new Button("Refuser"); //Bouton pour l'ajout d'une nouvelle salle
            refuser.setTooltip(new Tooltip("Cliquez ici pour rejeter la demande de l'utilisateur de devenir administrateur."));
            refuser.setOnAction(actionEvent -> refuserBoutonClick(profil));
            refuser.setAlignment(Pos.CENTER);
            grid.add(accepter, 5, compteur);
            grid.add(refuser, 6, compteur);
            compteur++;
        }

        VBox vBox = new VBox(retourBouton, grid);
        pane.getChildren().setAll(vBox);
    }

    public void accepterBoutonClick(Profil profil)
    {
        mainApp.getHello().SetAdmin(profil.get_id());
        mainApp.getHello().removeWaitingAdmin(profil.get_id());
        mainApp.showApprobationAdmin();
    }

    public void refuserBoutonClick(Profil profil)
    {
        mainApp.getHello().removeWaitingAdmin(profil.get_id());
        mainApp.showApprobationAdmin();
    }

    public void retourBoutonClick()
    {
        mainApp.showProfileCinema();
    }
}
