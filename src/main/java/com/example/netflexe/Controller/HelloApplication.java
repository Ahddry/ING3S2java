package com.example.netflexe.Controller;

import com.example.netflexe.Vue.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.netflexe.Model.*;
import com.example.netflexe.Model.Profil;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private CinemaCollection cinemaCollection = new CinemaCollection();
    private SceneController sceneController;
    private Connection myConn;
    private Statement myStat;
    private Profil user;
    private String tempUserId;
    private RunnableDemo thread;

    public SceneController get_sceneController()
    {
        return this.sceneController;
    }


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
    private ActorCollection collectionActor = new ActorCollection();
    public int selectedMenu ;
    private ArrayList<String> genre = new ArrayList<String>();

    


    public ActorCollection CollectionActeursMovie (String id_film) throws IOException {
        try {
            ResultSet myRes = myStat.executeQuery("SELECT person.nom, personnage.nom, prenom, pp, date_de_naissance, biographie FROM person JOIN film_person on film_person.id_person = person.id_person JOIN film \n" +
                    "ON (film_person.id_film = film.id_film) JOIN personnage ON personnage.id_film = film_person.id_film\n" +
                    " AND personnage.id_person = person.id_person WHERE film.id_film =" + id_film + ";");

            while (myRes.next()) {
                String nom = myRes.getString("person.nom");
                if(nom == null)
                    nom = "Donatien";
                String prenom = myRes.getString("prenom");
                if(prenom == null)
                    prenom = "Chevillard";
                String pp = myRes.getString("pp");
                if(pp == null)
                    pp = "https://tse2.mm.bing.net/th?id=OIP.X-25juJ5xWiHhacWDz8vnwHaLH&pid=Api";
                String date_de_naissance = myRes.getString("date_de_naissance");
                if(date_de_naissance == null)
                    date_de_naissance = "2001-09-11";
                String biographie = myRes.getString("biographie");
                if(biographie == null)
                    biographie = "Il/Elle est né(e), a vécu et tourné dans ce film, et va mourir un jour.";
                String role = myRes.getString("personnage.nom");
                if(role == null)
                    role = "Figurant";
                collectionActor.addActor(new Actor(prenom, nom, pp, date_de_naissance, biographie, role));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return collectionActor;
    }
    @Override
    public void start(Stage stage) throws IOException {



        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");
        sceneController = new SceneController(primaryStage, user, collection, this);
    }

    public void load_bdd_movie()
    {
        try
        {
            if(user != null)
            {
                ResultSet myres = myStat.executeQuery("SELECT genre.nom from utilisateur_genre JOIN genre ON utilisateur_genre.id_genre = genre.id_genre where id_user ='"+ String.valueOf(user.get_id()) +"' LIMIT 5;");
                while(myres.next())
                {
                    String tempgenre = myres.getString("nom");
                    genre.add(tempgenre);
                    user.add_genre(tempgenre);
                }
            }
            else
            {
                genre.add("Action");
                genre.add("Science-Fiction");
                genre.add("Aventure");
                genre.add("Animation");
                genre.add("Comédie");
            }
            for(int i = 0; i<5;i++)
            {
                ResultSet myRes = myStat.executeQuery("SELECT f.id_film,nom_film, poster, date_de_sortie, duree, synopsis, slogan FROM film as f JOIN film_genre ON (f.id_film = film_genre.id_film) JOIN genre ON (genre.id_genre = film_genre.id_genre) WHERE genre.nom = '" + genre.get(i) + "';");
                while(myRes.next())
                {
                    String poster = myRes.getString("poster");
                    String dateDeSortie = myRes.getString("date_de_sortie");
                    String duree = myRes.getString("duree");
                    String synopsis = myRes.getString("synopsis");
                    String slogan = myRes.getString("slogan");
                    String id_film = myRes.getString("id_film");
                    //System.out.println(poster);
                    collection[i].addMovie(new Movie(myRes.getString("nom_film"),"MOI",poster, dateDeSortie, dateDeSortie, duree, synopsis, slogan, id_film) );
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

            if(user != null)
            {
                ResultSet myRes2 = myStat.executeQuery("SELECT f.id_film,nom_film, poster, date_de_sortie, duree, synopsis, slogan FROM film as f JOIN liked ON (f.id_film = liked.id_film) WHERE liked.id_user = '" + String.valueOf(user.get_id()) +"';");
                while(myRes2.next())
                {
                    String tempDatedesortie = myRes2.getString("date_de_sortie");
                    user.ajouterLike(new Movie(myRes2.getString("nom_film"), "MOI", myRes2.getString("poster"), tempDatedesortie,tempDatedesortie,myRes2.getString("duree"), myRes2.getString("synopsis"), myRes2.getString("slogan"), myRes2.getString("id_film")));
                }
                user.set_image();
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        thread = new RunnableDemo("Mon thread");
        thread.setMainApp(this);

        sceneController.set_collection(collection);
        sceneController.create_cine();
        sceneController.setProfil(user);

        //initRootLayout();
        //showMainMenu();
    }

    public void add_like(int id_user, String id_film)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO liked (id_user, id_film) SELECT * FROM (SELECT '" + String.valueOf(id_user) +"' AS id_user, '" + id_film +"' AS id_film) AS tmp WHERE NOT EXISTS ( SELECT id_user FROM liked WHERE (id_user='" + String.valueOf(id_user) + "' AND id_film='" + id_film +"')) LIMIT 1;");    
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public ArrayList<Genre> get_genre_from_bdd()
    {
        ArrayList<Genre> genres = new ArrayList<Genre>();
        String tempnom;
        int id_genre;
        String tempLien = "";
        try
        {
            Statement myStat2 = myConn.createStatement();
            ResultSet myRes = myStat.executeQuery("SELECT nom, id_genre from genre");
            while(myRes.next())
            {
                tempnom = myRes.getString("nom");
                id_genre  = myRes.getInt("id_genre");
                ResultSet myRes2 = myStat2.executeQuery("SELECT poster FROM film JOIN film_genre ON film_genre.id_film = film.id_film JOIN genre ON genre.id_genre = film_genre.id_genre WHERE genre.id_genre = '" + String.valueOf(id_genre) + "' ORDER BY RAND() LIMIT 1;");
                while(myRes2.next())
                {
                    tempLien = myRes2.getString("poster");
                }
                genres.add(new Genre(tempnom,id_genre, tempLien));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return genres;
    }

    public void threadStarter()
    {
        thread.start();
    }

    public MovieCollection[] getMovieCollection(int i)
    {
        return collection;
    }

    public ActorCollection getActorCollection()
    {
        return collectionActor;
    }

    public Profil getProfil()
    {
        return user;
    }

    public Stage get_stage()
    {
        return primaryStage;
    }

    public void modify_user(String field, String value)
    {
        try
        {
            String query = "UPDATE utilisateur SET " + field + " = '"+ value + "' WHERE id_user = ' " + String.valueOf(user.get_id())+ "';";
            PreparedStatement pstmt = myConn.prepareStatement(query);
            pstmt.executeUpdate();
            ResultSet myRes = myStat.executeQuery("SELECT id_user, prenom, nom, date_de_naissance, genre, email, pp, admin FROM utilisateur where id_user = '" + user.get_id() + "'");
            while(myRes.next())
            {
                this.user.set_id(myRes.getInt("id_user"));
                this.user.set_prenom(myRes.getString("prenom"));
                this.user.set_nom(myRes.getString("nom"));
                this.user.set_mail(myRes.getString("email"));
                this.user.set_genre(myRes.getString("genre"));
                this.user.set_age(myRes.getString("date_de_naissance"));
                this.user.set_pp(myRes.getBinaryStream("pp"));
            }
                sceneController.setProfil(this.user);
                sceneController.updateProfil();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void genre_like(String genre)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO utilisateur_genre (id_user, id_genre) SELECT * FROM (SELECT '" + tempUserId +"' AS id_user, '" + genre +"' AS id_genre) AS tmp WHERE NOT EXISTS ( SELECT id_genre FROM utilisateur_genre WHERE (id_user='" + tempUserId + "' AND id_genre='" + genre +"')) LIMIT 1;");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void modify_user(String field, FileInputStream value)
    {
        try
        {
            String query = "UPDATE utilisateur SET " + field + " = ? WHERE id_user = '" + String.valueOf(user.get_id()) + "';";
            PreparedStatement pstmt = myConn.prepareStatement(query);
            pstmt.setBinaryStream(1, value);
            pstmt.executeUpdate();
            ResultSet myRes = myStat.executeQuery("SELECT id_user, prenom, nom, date_de_naissance, genre, email, pp, admin FROM utilisateur where id_user = '" + user.get_id() + "'");
            while(myRes.next())
            {
                this.user.set_id(myRes.getInt("id_user"));
                this.user.set_prenom(myRes.getString("prenom"));
                this.user.set_nom(myRes.getString("nom"));
                this.user.set_mail(myRes.getString("email"));
                this.user.set_genre(myRes.getString("genre"));
                this.user.set_age(myRes.getString("date_de_naissance"));
                this.user.set_pp(myRes.getBinaryStream("pp"));
            }
            sceneController.setProfil(this.user);
            sceneController.updateProfil();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }


    public int create_acct(String prenom, String nom, String genre, int year, int month, int day, String login, String mdp, boolean admin, String filePath)
    {
        String naissance = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
        try
        {
            String query = "INSERT INTO utilisateur (prenom, nom, date_de_naissance, genre, email, pwd, admin, pp) SELECT * FROM (SELECT '" + prenom +"' AS prenom, '" + nom + "' AS nom, '" + naissance + "' AS date_de_naissance, '" + genre + "' AS genre, '" + login + "' AS email, '" + DigestUtils.sha256Hex(mdp) + "' AS mdp, FALSE, ? AS pp) AS tmp WHERE NOT EXISTS (SELECT id_user FROM utilisateur WHERE email='" + login +"') LIMIT 1;";
            PreparedStatement pstmt = myConn.prepareStatement(query);
            FileInputStream ppfile = new FileInputStream(filePath);
            pstmt.setBinaryStream(1, ppfile);
            if(pstmt.executeUpdate() != 0)
            {
                ResultSet myRes = myStat.executeQuery("SELECT id_user FROM utilisateur WHERE email = '" + login + "';");
                while(myRes.next())
                {
                    tempUserId = myRes.getString("id_user");
                    if(tempUserId != "")
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
            ResultSet myRes = myStat.executeQuery("SELECT id_user, prenom, nom, date_de_naissance, genre, email, pp, admin FROM utilisateur where email='" + login +"'" + " AND pwd='" + DigestUtils.sha256Hex(mdp) +"'");
            while(myRes.next())
            {
                if(myRes.getString("id_user") != "")
                {
                    this.user = new Profil(myRes.getInt("id_user"), myRes.getString("prenom"), myRes.getString("nom"), myRes.getString("email"), myRes.getString("genre"), myRes.getString("date_de_naissance"), myRes.getBinaryStream("pp"));
                    //System.out.println(this.user.get_prenom());
                    sceneController.setProfil(this.user);
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