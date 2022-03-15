package com.example.netflexe.Controller;

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
import com.example.netflexe.Model.*;
import com.example.netflexe.Model.Profil;
import com.example.netflexe.Vue.SceneController;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Profil monProfil = new Profil();
    private CinemaCollection cinemaCollection = new CinemaCollection();
    private SceneController sceneController;
    private Connection myConn;
    private Statement myStat;


    public HelloApplication()
    {
        try
        {
            this.myConn = DriverManager.getConnection("jdbc:mysql://fournierfamily.ovh:3306/Netflece", "jps", "poojava");
            this.myStat = myConn.createStatement();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private MovieCollection[] collection = {new MovieCollection(),new MovieCollection(),new MovieCollection(),new MovieCollection(),new MovieCollection(),new MovieCollection()};

    public int selectedMenu ;
    private String[] genre = {"Action","Science-Fiction","Aventure","Animation","Comédie"};

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");
        try
        {

            for(int i = 0; i<5;i++)
            {
                ResultSet myRes = myStat.executeQuery("SELECT nom_film, poster, date_de_sortie, duree, synopsis, slogan FROM film JOIN film_genre ON (film.id_film = film_genre.id_film) JOIN genre ON (genre.id_genre = film_genre.id_genre) WHERE genre.nom = '" + genre[i] + "';");
                while(myRes.next())
                {
                    String poster = myRes.getString("poster");
                    String dateDeSortie = myRes.getString("date_de_sortie");
                    String duree = myRes.getString("duree");
                    String synopsis = myRes.getString("synopsis");
                    String slogan = myRes.getString("slogan");
                    //System.out.println(poster);
                    collection[i].addMovie(new Movie(myRes.getString("nom_film"),"MOI",poster, dateDeSortie, dateDeSortie, duree, synopsis, slogan) );
                }
            }

            for(int i = 0; i<5;i++)
            {
                for(int j = 0; j<collection[i].getSize();j++)
                {
                    if(!collection[5].checkBoolean(collection[i].getMovie(j).getTitle()))
                    {
                        collection[5].addMovie(collection[i].getMovie(j));
                    }

                }
            }

            cinemaCollection.addCinema(new Cinema("Cinema Gaumont","https://www.sortiraparis.com/images/80/89810/538658-le-cinema-gaumont-parnasse.jpg"));
            cinemaCollection.addCinema(new Cinema("Cinema UGC","https://www.pagesjaunes.fr/media/resto/ugc_cine_cite_la_defense_OSD52406032-78652.jpeg"));
            cinemaCollection.addCinema(new Cinema("Cinema Le Village","https://salles-cinema.com/wp-content/uploads/2009/07/cinema-neuilly-sur-seine.jpg"));
            cinemaCollection.addMovie(collection[0].getMovie(0),"Cinema Gaumont");
            cinemaCollection.addMovie(collection[0].getMovie(1),"Cinema Gaumont");
            cinemaCollection.addMovie(collection[0].getMovie(2),"Cinema Gaumont");
            cinemaCollection.addMovie(collection[0].getMovie(0),"Cinema UGC");
            cinemaCollection.addMovie(collection[0].getMovie(0),"Cinema Le Village");
            cinemaCollection.setImage();




        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        RunnableDemo thread = new RunnableDemo("Mon thread");
        thread.setMainApp(this);
        thread.start();

        sceneController = new SceneController(primaryStage, monProfil, collection, this);

        //initRootLayout();
        //showMainMenu();

    }

    public MovieCollection[] getMovieCollection(int i)
    {
        return collection;
    }

    public Profil getProfil()
    {
        return monProfil;
    }
    public int create_acct(String prenom, String nom, String genre, int year, int month, int day, String login, String mdp, boolean admin)
    {
        String naissance = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
        try
        {
            if(myStat.executeUpdate("INSERT INTO utilisateur (prenom, nom, date_de_naissance, genre, email, pwd, admin) SELECT * FROM (SELECT '" + prenom +"' AS prenom, '" + nom + "' AS nom, '" + naissance + "' AS date_de_naissance, '" + genre + "' AS genre, '" + login + "' AS email, '" + DigestUtils.sha256Hex(mdp) + "' AS mdp, FALSE) AS tmp WHERE NOT EXISTS (SELECT id_user FROM utilisateur WHERE email='" + login +"') LIMIT 1;") != 0)
            {
                ResultSet myRes = myStat.executeQuery("SELECT id_user FROM utilisateur WHERE email = '" + login + "';");
                while(myRes.next())
                {
                    if(myRes.getString("id_user") != "")
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
            else
            {
                return -1;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }
    public int login_acct(String login, String mdp)
    {
        try
        {
            ResultSet myRes = myStat.executeQuery("SELECT id_user FROM utilisateur where email='" + login +"'" + " AND pwd='" + DigestUtils.sha256Hex(mdp) +"'");
            while(myRes.next())
            {
                if(myRes.getString("id_user") != "")
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        launch();
    }
}