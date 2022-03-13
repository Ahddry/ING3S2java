package com.example.netflexe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController
{
    private Stage primaryStage;
    private Scene scene;
    private BorderPane rootLayout;
    private final Profil profil;
    private final MovieCollection[] collections;

    public SceneController(Stage stage, Profil p, MovieCollection[] c)
    {
        primaryStage = stage;
        profil = p;
        collections = c;
        showMain();
    }


    public void showMain()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MyScene.fxml"));
            rootLayout = (BorderPane) loader.load();
            scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            MySceneController controller = loader.getController();
            controller.setMainApp(this);
            showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdmin()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Admin.fxml"));
            rootLayout = loader.load();
            scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            AdminController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("MainMenu.fxml"));
            AnchorPane mainMenu = (AnchorPane) loader.load();
            MainMenuController icontroller = loader.getController();
            icontroller.setMainApp(this);
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(mainMenu);
            icontroller.initializeBis();
            rootLayout.setCenter(scroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBiblio(Profil monProfil) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("Biblio.fxml"));
            AnchorPane biblio = (AnchorPane) loader.load();
            BiblioController bcontroller = loader.getController();
            bcontroller.setMainApp(this);
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(biblio);
            bcontroller.initializeBis(monProfil);
            rootLayout.setCenter(scroll);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfo(Movie movie) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("FilmInfo.fxml"));
            AnchorPane info = (AnchorPane) loader.load();
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(info);
            FilmInfoController controller = loader.getController();
            controller.setMainApp(this);
            controller.setMovie(movie);
            controller.setProfil(profil);
            rootLayout.setCenter(scroll);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResearch()
    {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("Research.fxml"));
            AnchorPane research = (AnchorPane) loader.load();

            ResearchController controller = loader.getController();
            controller.setMainApp(this);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(research);

            controller.initializeBis();

            rootLayout.setCenter(scroll);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReservation()
    {
        try
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Reservation.fxml"));
            AnchorPane research = (AnchorPane) loader.load();

            ReservationController controller = loader.getController();
            controller.setMainApp(this);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(research);

            controller.initializeBis();

            rootLayout.setCenter(scroll);

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showAccueilAdmin()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AccueilAdmin.fxml"));
            AnchorPane accueilAdmin = loader.load();

            AccueilAdminController controller = loader.getController();
            controller.setMainApp(this);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(accueilAdmin);

            rootLayout.setCenter(scroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Profil getProfil()
    {
        return profil;
    }

    public MovieCollection[] getMovieCollection(int truc)
    {
        return collections;
    }
}
