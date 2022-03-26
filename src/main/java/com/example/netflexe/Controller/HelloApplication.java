package com.example.netflexe.Controller;

import com.example.netflexe.Vue.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
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
    private SceneController sceneController;
    private Connection myConn;
    private Statement myStat;
    private Statement myStat2;
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
            this.myStat2 = myConn.createStatement();
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
        collectionActor.erase();
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
                    pp = "https://scontent-cdg2-1.xx.fbcdn.net/v/t31.18172-8/28335920_10155374977864352_2211314611360502777_o.jpg?_nc_cat=108&ccb=1-5&_nc_sid=174925&_nc_ohc=uX32zEDgc6YAX_BmANe&_nc_ht=scontent-cdg2-1.xx&oh=00_AT-ZbRNI9ccH3cfJIXite7GdJ_WqxwvrfQQs8hmqWsH9Cg&oe=6262AF32";
                    //pp = "https://tse2.mm.bing.net/th?id=OIP.X-25juJ5xWiHhacWDz8vnwHaLH&pid=Api";
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
                ResultSet myRes = myStat.executeQuery("SELECT f.id_film,nom_film, poster, date_de_sortie, duree, synopsis, slogan, trailer, person.nom, person.prenom FROM film as f JOIN film_genre ON (f.id_film = film_genre.id_film) JOIN genre ON (genre.id_genre = film_genre.id_genre) JOIN realisateur ON realisateur.id_film = f.id_film JOIN person ON person.id_person = realisateur.id_person WHERE genre.nom = '" + genre.get(i) + "';");
                while(myRes.next())
                {
                    String poster = myRes.getString("poster");
                    String dateDeSortie = myRes.getString("date_de_sortie");
                    String duree = myRes.getString("duree");
                    String synopsis = myRes.getString("synopsis");
                    String slogan = myRes.getString("slogan");
                    String id_film = myRes.getString("id_film");
                    String trailer;
                    if(myRes.getString("trailer") != "" && myRes.getString("trailer") != null)
                    {
                        trailer = myRes.getString("trailer").split("=")[1];
                    }
                    else
                    {
                        trailer = null;
                    }
                    //System.out.println(poster);
                    String realisateur = "";
                    if(myRes.getString("person.prenom") != null && myRes.getString("person.prenom") != "")
                    {
                        realisateur += myRes.getString("person.prenom");
                    }
                    if(myRes.getString("person.nom") != null && myRes.getString("person.nom") != "")
                    {
                        if(!realisateur.equals(""))
                        {
                            realisateur += " " + myRes.getString("person.nom");
                        }
                        else
                        {
                            realisateur += myRes.getString("person.nom");
                        }
                    }
                    collection[i].addMovie(new Movie(myRes.getString("nom_film"),realisateur,poster, dateDeSortie, dateDeSortie, duree, synopsis, slogan, id_film, trailer));
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

            ResultSet myRes = myStat.executeQuery("SELECT id_cine, nom, lien_image FROM cinema");
            while(myRes.next())
            {
                sceneController.getCinemaCollection().addCinema(new Cinema(myRes.getInt("id_cine"), myRes.getString("nom"), myRes.getString("lien_image")));
            }
            sceneController.getCinemaCollection().setImage();
            for(int i = 0 ; i < sceneController.getCinemaCollection().getSize(); i++)
            {
                ResultSet myRes2 = myStat.executeQuery("SELECT salle.id_salle, capacite, num_salle FROM salle JOIN cinema_salle ON cinema_salle.id_salle = salle.id_salle WHERE cinema_salle.id_cine = '" + String.valueOf(sceneController.getCinemaCollection().getCinema(i).get_id_cine()) +"';");
                while(myRes2.next())
                {
                    sceneController.getCinemaCollection().getCinema(i).addSalles(new Salle(myRes2.getInt("salle.id_salle"), myRes2.getInt("num_salle"), myRes2.getInt("capacite")));
                }

            }
            for(int i = 0 ; i < sceneController.getCinemaCollection().getSize(); i++)
            {
                for(int j = 0 ; j < sceneController.getCinemaCollection().getCinema(i).getSalles().size();j++)
                {
                    ResultSet myRes3 = myStat.executeQuery("SELECT seance.id_seance, seance.id_film, seance.date_horraire, seance.prix, film.id_film, film.poster, film.nom_film, film.date_de_sortie, film.duree, film.synopsis, film.slogan, film.trailer, salle.num_salle, person.prenom, person.nom FROM seance JOIN film on film.id_film = seance.id_film JOIN salle ON salle.id_salle = seance.id_salle  JOIN realisateur ON realisateur.id_film = film.id_film JOIN person ON person.id_person = realisateur.id_person WHERE seance.id_cine = '" + String.valueOf(sceneController.getCinemaCollection().getCinema(i).get_id_cine()) + "' AND seance.id_salle = '"+ String.valueOf(sceneController.getCinemaCollection().getCinema(i).getSalles().get(j).get_id_bdd())+"';");
                    while(myRes3.next())
                    {
                        String trailer;
                        if(myRes3.getString("film.trailer") != "" && myRes3.getString("film.trailer") != null)
                        {
                            trailer = myRes3.getString("film.trailer").split("=")[1];
                        }
                        else
                        {
                            trailer = null;
                        }
                        String realisateur = "";
                        if(myRes3.getString("person.prenom") != null && myRes3.getString("person.prenom") != "")
                        {
                            realisateur += myRes3.getString("person.prenom");
                        }
                        if(myRes3.getString("person.nom") != null && myRes3.getString("person.nom") != "")
                        {
                            if(!realisateur.equals(""))
                            {
                                realisateur += " " + myRes3.getString("person.nom");
                            }
                            else
                            {
                                realisateur += myRes3.getString("person.nom");
                            }
                        }
                        Movie movie = new Movie(myRes3.getString("film.nom_film"), realisateur, myRes3.getString("film.poster"), myRes3.getString("film.date_de_sortie"), myRes3.getString("film.date_de_sortie"), myRes3.getString("film.duree"), myRes3.getString("film.synopsis"), myRes3.getString("film.slogan"), myRes3.getString("film.id_film"),trailer);
                        sceneController.getCinemaCollection().getCinema(i).getSalles().get(j).addSeance(new Seance(myRes3.getString("film.nom_film"), movie, LocalDate.parse(myRes3.getString("seance.date_horraire").split(" ")[0]), myRes3.getString("seance.date_horraire").split(" ")[1], myRes3.getInt("salle.num_salle"), myRes3.getDouble("seance.prix"), myRes3.getInt("seance.id_seance")));
                        sceneController.getCinemaCollection().getCinema(i).ajoutFilm(movie);
                    }
                }
                sceneController.getCinemaCollection().getCinema(i).setImage();
            }

            if(user != null)
            {
                ResultSet myRes2 = myStat.executeQuery("SELECT film.id_film,nom_film, poster, date_de_sortie, duree, synopsis, slogan, trailer, person.prenom, person.nom FROM film JOIN liked ON film.id_film = liked.id_film JOIN realisateur ON realisateur.id_film = film.id_film JOIN person ON person.id_person = realisateur.id_person WHERE liked.id_user = '" + String.valueOf(user.get_id()) +"';");
                while(myRes2.next())
                {
                    String tempDatedesortie = myRes2.getString("date_de_sortie");
                    String trailer;
                    if(myRes2.getString("trailer") != "" && myRes2.getString("trailer") != null)
                    {
                        trailer = myRes2.getString("trailer").split("=")[1];
                    }
                    else
                    {
                        trailer = null;
                    }
                    String realisateur = "";
                    if(myRes2.getString("person.prenom") != null && myRes2.getString("person.prenom") != "")
                    {
                        realisateur += myRes2.getString("person.prenom");
                    }
                    if(myRes2.getString("person.nom") != null && myRes2.getString("person.nom") != "")
                    {
                        if(!realisateur.equals(""))
                        {
                            realisateur += " " + myRes2.getString("person.nom");
                        }
                        else
                        {
                            realisateur += myRes.getString("person.nom");
                        }
                    }
                    user.ajouterLike(new Movie(myRes2.getString("nom_film"), realisateur, myRes2.getString("poster"), tempDatedesortie,tempDatedesortie,myRes2.getString("duree"), myRes2.getString("synopsis"), myRes2.getString("slogan"), myRes2.getString("id_film"), trailer));
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
        sceneController.setProfil(user);

        //initRootLayout();
        //showMainMenu();
    }
    public void set_deja_vu(int id_user, int id_film)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO deja_vu (id_user, id_film) SELECT * FROM (SELECT '" + String.valueOf(id_user) +"' AS id_user, '" + String.valueOf(id_film) +"' AS id_film) AS tmp WHERE NOT EXISTS ( SELECT id_user FROM deja_vu WHERE (id_user='" + String.valueOf(id_user) + "' AND id_film='" + String.valueOf(id_film) +"')) LIMIT 1;");    
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
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
    public void add_reservation(int id_user, int id_seance, double tarif)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO reservation (id_user, id_seance, tarif) VALUES ('"+ String.valueOf(id_user) +"','" + String.valueOf(id_seance) + "','" + String.valueOf(tarif) + "');");
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
                        if(admin)
                        {
                            setDemandeAdmin(Integer.valueOf(tempUserId));
                        }
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
    public void insertCinema_into_bdd(String nom, int id_admin, String lien_image)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO cinema (nom,id_user,lien_image) SELECT * FROM (SELECT '" + nom +"' AS nom, '" + id_admin + "' AS id_user, '" + lien_image + "' AS lien_image) AS tmp WHERE NOT EXISTS ( SELECT id_cine FROM cinema WHERE (nom = '"+ nom + "' AND id_user = '" + id_admin + "' AND lien_image = '" + lien_image + "')) LIMIT 1;");    
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void SupprimerCinemaBDD(int id_cine)
    {
        try
        {
            myStat.executeUpdate("DELETE FROM cinema WHERE id_cine = '" + id_cine + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void insertMovie_into_bdd(String lien_poster, String nom_film, String date_de_sortie, String duree, String synopsis, String slogan, String trailer)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO film (poster,nom_film,date_de_sortie,duree,synopsis,slogan,trailer) SELECT * FROM (SELECT '" + lien_poster +"' AS poster, '" + nom_film +"' AS nom_film, '"+ date_de_sortie +"' AS date_de_sortie, '" + duree + "' AS duree, '" + synopsis + "' AS synopsis, '" + slogan + "' AS slogan, '" + trailer + "' AS trailer) AS tmp WHERE NOT EXISTS ( SELECT id_film FROM film WHERE (nom_film='" + nom_film  + "' AND poster='" + lien_poster +"' AND date_de_sortie = '" + date_de_sortie + "' AND duree= '"+ duree +"' AND synopsis = '" + synopsis + "' AND slogan = '" + slogan + "' AND trailer = '" + trailer + "')) LIMIT 1;");    
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void AjouterSalleCinema_into_bdd(int capacite, int num_salle, int id_cine_bdd)
    {
        try
        {
            int id_salle = -1;
            myStat.executeUpdate("INSERT INTO salle (capacite, num_salle) VALUES ('"+ String.valueOf(capacite) +"','" + String.valueOf(num_salle) + "');");
            ResultSet myRes = myStat.executeQuery("SELECT @@IDENTITY AS [Last-Inserted Identity Value];");
            while(myRes.next())
            {
                id_salle = myRes.getInt("Last-Inserted Identity Value");
            }
            if(id_salle != -1)
            {
                myStat.executeUpdate("INSERT INTO cinema_salle (id_cine, id_salle) VALUES ('" + String.valueOf(id_cine_bdd) + "','" + String.valueOf(id_salle) + "');");
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void SupprimerUneSalleBDD(int id_salle_bdd)
    {
        try
        {
            myStat.executeUpdate("DELETE FROM salle WHERE id_salle = '" + String.valueOf(id_salle_bdd) + "';");
            myStat.executeUpdate("DELETE FROM cinema_salle WHERE id_salle = '" + String.valueOf(id_salle_bdd) + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void SupprimerUnseSeanceBDD(int id_seance)
    {
        try
        {
            myStat.executeUpdate("DELETE FROM seance WHERE id_salle = '" + String.valueOf(id_seance) + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void changerNomCinema(int id_cine_bdd, String nomCine)
    {
        try
        {
            myStat.executeUpdate("UPDATE cinema SET nom = '" + nomCine + "' WHERE id_salle = '" + String.valueOf(id_cine_bdd) + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void changerLienImageCinema(int id_cine_bdd, String lienImage)
    {
        try
        {
            myStat.executeUpdate("UPDATE cinema SET lien_image = '" + lienImage + "' WHERE id_salle = '" + String.valueOf(id_cine_bdd) + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public int CreateSeance_into_bdd(int id_film,int id_cine_bdd, int id_salle_bdd, String dateSeance,String heureSeance, double prixSeance)
    {
        try
        {
            String tempDateHorraire = "";
            tempDateHorraire += dateSeance;
            if(heureSeance.length() == 5 && Integer.valueOf(heureSeance.substring(0,2)) < 24 && Integer.valueOf(heureSeance.substring(0,2)) > 0 && Integer.valueOf(heureSeance.substring(3)) < 60 && Integer.valueOf(heureSeance.substring(3)) >= 0)
            {
                if(heureSeance.contains("h"))
                {
                    tempDateHorraire += " " + heureSeance.split("h")[0] + ":" + heureSeance.split("h")[1];
                }
                else if(heureSeance.contains(":"))
                {
                    tempDateHorraire += " " + heureSeance.split(":")[0] + ":" + heureSeance.split(":")[1];
                }
                myStat.executeUpdate("INSERT INTO seance (id_salle, id_film, id_cine, date_horraire, prix) SELECT * FROM (SELECT '"+ id_salle_bdd + "' AS id_salle, '" + id_film + "' AS id_film, '" + id_cine_bdd + "' AS id_cine, '" + tempDateHorraire + "' AS date_horraire, '"+ prixSeance +"' AS prix) AS tmp WHERE NOT EXISTS ( SELECT id_seance FROM seance WHERE (id_cine = '" + id_cine_bdd + "' AND id_film = '" + id_film + "' AND id_salle = '" + id_salle_bdd + "' AND date_horraire = '" + tempDateHorraire + "')) LIMIT 1;");
                return 0;
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
        return 1;
    }
    public void SetAdmin(int id_user)
    {
        try
        {
            myStat.executeUpdate("UPDATE utilisateur SET admin = '1' WHERE id_user = '" + String.valueOf(id_user) + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void setDemandeAdmin(int id_user)
    {
        try
        {
            myStat.executeUpdate("INSERT INTO attente_admin (id_user) SELECT * FROM (SELECT '"+ id_user + "' AS id_user) AS tmp WHERE NOT EXISTS ( SELECT id_user FROM attente_admin WHERE (id_user = '" + id_user + "') LIMIT 1;");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public void removeWaitingAdmin(int id_user)
    {
        try
        {
            myStat.executeUpdate("DELETE FROM attente_admin WHERE id_user = '" + id_user + "';");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    public ArrayList<Profil> getAttenteAdmin()
    {
        ArrayList<Profil> temp = new ArrayList<Profil>();
        try
        {
            ResultSet myRes = myStat.executeQuery("SELECT utilisateur.id_user, utilisateur.prenom, utilisateur.nom, utilisateur.date_de_naissance, utilisateur.genre, utilisateur.email FROM attente_admin JOIN utilisateur ON utilisateur.id_user = attente_admin.id_user;");
            while(myRes.next())
            {
                temp.add(new Profil(myRes.getInt("utilisateur.id_user"), myRes.getString("utilisateur.prenom"), myRes.getString("utilisateur.nom"), myRes.getString("utilisateur.email"), myRes.getString("utilisateur.genre"), myRes.getString("utilisateur.date_de_naissance"), null, false,null));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return temp;
    }

    public int login_acct(String login, String mdp)
    {
        try
        {
            ResultSet myRes = myStat.executeQuery("SELECT utilisateur.id_user, prenom, utilisateur.nom, date_de_naissance, genre, email, pp, admin, cinema.id_cine,cinema.nom, cinema.lien_image FROM utilisateur LEFT JOIN cinema ON cinema.id_user = utilisateur.id_user WHERE email='" + login +"'" + " AND pwd='" + DigestUtils.sha256Hex(mdp) +"'");
            while(myRes.next())
            {
                if(myRes.getString("utilisateur.id_user") != "")
                {
                    Cinema tempCine = null;
                    if(myRes.getString("cinema.nom") != null && myRes.getString("cinema.lien_image") != null && myRes.getString("cinema.id_cine") != null)
                    {
                        tempCine = new Cinema(myRes.getInt("cinema.id_cine"),myRes.getString("cinema.nom"), myRes.getString("cinema.lien_image"));
                    }
                    this.user = new Profil(myRes.getInt("utilisateur.id_user"), myRes.getString("prenom"), myRes.getString("nom"), myRes.getString("email"), myRes.getString("genre"), myRes.getString("date_de_naissance"), myRes.getBinaryStream("pp"), myRes.getBoolean("admin"), tempCine);
                    ResultSet myRes2 = myStat2.executeQuery("SELECT film.poster, film.nom_film, seance.date_horraire, cinema.nom, COUNT(*) FROM reservation JOIN seance on reservation.id_seance = seance.id_seance JOIN film ON seance.id_film = film.id_film JOIN cinema ON cinema.id_cine = seance.id_cine WHERE reservation.id_user ='" + myRes.getInt("utilisateur.id_user") +"'GROUP BY reservation.id_user, reservation.id_seance;");
                    while(myRes2.next())
                    {
                        this.user.ajouterReservation(new Reservation(new Movie(myRes2.getString("film.nom_film"), myRes2.getString("film.poster")), myRes2.getString("seance.date_horraire").split(" ")[1],myRes2.getString("cinema.nom") , myRes2.getString("seance.date_horraire").split(" ")[0], myRes2.getInt("COUNT(*)")));
                        //this.user.getFilmRes().get(this.user.getFilmRes().size()-1).getMovie()
                        
                    }
                    //this.user.ajouterReservation(new Reservation());
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