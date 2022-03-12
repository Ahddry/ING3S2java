package com.example.netflexe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
import java.sql.*;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private MovieCollection collection[] = {new MovieCollection(),new MovieCollection(),new MovieCollection(),new MovieCollection(),new MovieCollection()};

    public int selectedMenu ;
    private String[] genre = {"Action","Science-Fiction","Aventure","Animation","Comédie"};

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");
        try
        {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://fournierfamily.ovh:3306/Netflece", "jps", "poojava");
            Statement myStat = myConn.createStatement();

            for(int i = 0; i<5;i++)
            {
                ResultSet myRes = myStat.executeQuery("SELECT nom_film, poster FROM film JOIN film_genre ON (film.id_film = film_genre.id_film) JOIN genre ON (genre.id_genre = film_genre.id_genre) WHERE genre.nom = '" + genre[i] + "';");
                while(myRes.next())
                {
                    String poster = myRes.getString("poster");
                    System.out.println(poster);
                    collection[i].addMovie(new Movie(myRes.getString("nom_film"),"MOI",poster) );
                }
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }




        initRootLayout();
        showMainMenu();

    }

    public void initRootLayout() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("MyScene.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            MySceneController controller = loader.getController();
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

    public void showBiblio() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(HelloApplication.class.getResource("Biblio.fxml"));
            AnchorPane biblio = (AnchorPane) loader.load();

            BiblioController bcontroller = loader.getController();
            bcontroller.setMainApp(this);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(biblio);





            bcontroller.initializeBis();

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

    public MovieCollection[] getMovieCollection(int i)
    {
        return collection;
    }

    public static void main(String[] args) {
        launch();
    }
}