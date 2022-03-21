package com.example.netflexe.Vue;

import com.example.netflexe.Model.*;
import com.example.netflexe.Vue.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.example.netflexe.Controller.HelloApplication;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SceneController
{
    private HelloApplication controller;
    private Stage primaryStage;
    private Scene scene;
    
    private BorderPane rootLayout;
    private AnchorPane loginLayout;
    private AnchorPane mainMenu;
    private AnchorPane biblio;



    private Profil profil = null;
    private Cinema cinemaAdmin;
    
    
    private ProfileController controller_profil;
    private MainMenuController icontroller;
    private BiblioController bcontroller;

    private ScrollPane scrollmainMenu;
    private ScrollPane scrollBiblio;

    private MovieCollection[] collections;
    private CinemaCollection cinemaCollection = new CinemaCollection();
    private Profil user;

    public SceneController(Stage stage, Profil p, MovieCollection[] c, HelloApplication controller)
    {
        primaryStage = stage;
        this.controller = controller;
        profil = p;
        collections = c;
        //showMain();
        Login();
        //showGenre();
    }
    public void set_collection(MovieCollection[] c)
    {
        this.collections = c;
    }
    public void create_cine()
    {
        cinemaAdmin = new Cinema("Cinema Gaumont","https://www.sortiraparis.com/images/80/89810/538658-le-cinema-gaumont-parnasse.jpg");
        cinemaAdmin.ajoutFilm(collections[0].getMovie(0));
        cinemaAdmin.ajoutFilm(collections[0].getMovie(5));
        cinemaAdmin.ajoutFilm(collections[0].getMovie(12));
        cinemaAdmin.ajoutFilm(collections[0].getMovie(8));
        cinemaAdmin.ajoutFilm(collections[0].getMovie(22));
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
            controller.setProfil(user);
            controller.initialiseBis();
            showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showProfile()
    {
        try {
            if(controller_profil == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Profile.fxml"));
                loginLayout = (AnchorPane) loader.load();
                controller_profil = loader.getController();
                controller_profil.setMainApp(this, this.controller);
                controller_profil.setLabels(user);
            }
            rootLayout.setCenter(loginLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProfil(Profil user)
    {
        this.user = this.controller.getProfil();
    }
    public void updateProfil()
    {
        this.controller_profil.setLabels(this.user);
    }

    public void Login() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            loginLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            LoginController controller = loader.getController();
            controller.setMainApp(this, this.controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SignUp() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sign_up.fxml"));
            loginLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            SignUpController controller = loader.getController();
            controller.setMainApp(this, this.controller);
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
            showAccueilAdmin();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainMenu() {
        try {
            if(icontroller == null)
            {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainMenu.fxml"));
                mainMenu = (AnchorPane) loader.load();
                icontroller = loader.getController();
                icontroller.setMainApp(this, this.controller);
                scrollmainMenu = new ScrollPane();
                scrollmainMenu.setContent(mainMenu);
                this.controller.load_bdd_movie();
                icontroller.initializeBis();
                controller.threadStarter();

                cinemaCollection.addCinema(new Cinema("Cinema Gaumont","https://www.sortiraparis.com/images/80/89810/538658-le-cinema-gaumont-parnasse.jpg"));
                cinemaCollection.addCinema(new Cinema("Cinema UGC","https://www.pagesjaunes.fr/media/resto/ugc_cine_cite_la_defense_OSD52406032-78652.jpeg"));
                cinemaCollection.addCinema(new Cinema("Cinema Le Village","https://salles-cinema.com/wp-content/uploads/2009/07/cinema-neuilly-sur-seine.jpg"));
                cinemaCollection.addMovie(collections[0].getMovie(0),"Cinema Gaumont");
        /*cinemaCollection.addMovie(collections[0].getMovie(1),"Cinema Gaumont");
        cinemaCollection.addMovie(collections[0].getMovie(2),"Cinema Gaumont");*/
                cinemaCollection.addMovie(collections[0].getMovie(0),"Cinema UGC");
                cinemaCollection.addMovie(collections[0].getMovie(0),"Cinema Le Village");
                cinemaCollection.setImage();
            }
            rootLayout.setCenter(scrollmainMenu);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshMovie(int i)
    {
        icontroller.refresher(i);
    }

    public void showGenre()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genreliker.fxml"));
            loginLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            GenreLikeController controller = loader.getController();
            controller.setMainApp(this, this.controller);
            controller.initializeBis();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void showBiblio(Profil monProfil) {
        try {

            if(bcontroller == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Biblio.fxml"));
                biblio = (AnchorPane) loader.load();
                bcontroller = loader.getController();
                bcontroller.setMainApp(this);
                 scrollBiblio = new ScrollPane();
                scrollBiblio.setContent(biblio);
                bcontroller.setCinemaC(cinemaCollection);
                bcontroller.initializeBis(monProfil);

            }

            rootLayout.setCenter(scrollBiblio);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBiblioRes(Profil monProfil) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("BiblioReserv.fxml"));
            AnchorPane biblio = (AnchorPane) loader.load();
            BiblioReserv controller = loader.getController();
            controller.setMainApp(this);
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(biblio);
            controller.initializeBis(monProfil);
            rootLayout.setCenter(scroll);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfo(Movie movie, boolean admin) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FilmInfo.fxml"));
            AnchorPane info = (AnchorPane) loader.load();
            FilmInfoController controller = loader.getController();
            controller.setMainApp(this);
            controller.setMovie(movie);
            controller.setProfil(user);
            controller.setAdminAccess(admin);
            controller.setCinema(cinemaAdmin);
            controller.setBiblioController(bcontroller);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(info);
            rootLayout.setCenter(scroll);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showResearch(boolean admin)
    {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Research.fxml"));
            AnchorPane research = (AnchorPane) loader.load();

            ResearchController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAdminSelect(admin);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(research);

            controller.initializeBis();

            rootLayout.setCenter(scroll);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReservation(Movie movie)
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

            controller.initializeBis(cinemaCollection, movie);

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
            controller.init(cinemaAdmin);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStats()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("statsPage.fxml"));
            AnchorPane stats = loader.load();

            statsPageController controller = loader.getController();
            //controller.setMainApp(this);
            ScrollPane scroll = new ScrollPane();
            scroll.setContent(stats);

            rootLayout.setCenter(scroll);
            controller.init(cinemaAdmin);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showValiderReseravtion(Movie movie, Cinema cinema, Profil profil)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ValiderReservation.fxml"));
            AnchorPane accueilAdmin = loader.load();

            cinema.addSalles(new Salle(1, new ArrayList<>(),53));
            cinema.addSeance(1, new Seance(movie.getTitle(),movie,LocalDate.of(2022,3,18),"14H30",1,45));
            cinema.addSeance(1, new Seance(movie.getTitle(),movie,LocalDate.of(2022,3,18),"16H30",1,45));
            cinema.addSeance(1, new Seance(movie.getTitle(),movie,LocalDate.of(2022,3,19),"14H30",1,45));
            cinema.addSeance(1, new Seance(movie.getTitle(),movie,LocalDate.of(2022,3,19),"16H30",1,45));

            ValiderReservation controller = loader.getController();
            controller.setMainApp(this);
            controller.setProfil(profil);
            controller.initializeBis(movie, cinema);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(accueilAdmin);



            rootLayout.setCenter(scroll);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAjouterFilm(Cinema c)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AjoutFilm.fxml"));
            AnchorPane ajoutFilm = loader.load();

            AjoutFilmController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCinema(c);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(ajoutFilm);

            rootLayout.setCenter(scroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAjoutFilmForm(Cinema c)
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AjoutFilmForm.fxml"));
            AnchorPane ajoutFilm = loader.load();

            AjoutFilmFormController controller = loader.getController();
            controller.setMainApp(this);
            controller.setCinema(c);
            controller.init();

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(ajoutFilm);

            rootLayout.setCenter(scroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSeances()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Seances.fxml"));
            AnchorPane seancesView = loader.load();


            SeancesController controller = loader.getController();
            controller.setMainApp(this);
            controller.init(cinemaAdmin);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(seancesView);

            rootLayout.setCenter(scroll);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Profil getProfil()
    {
        return this.controller.getProfil();
    }

    public MovieCollection[] getMovieCollection(int truc)
    {
        return collections;
    }

    public Scene getScene()
    {
        return scene;
    }

    public void setCinemaAdmin(Cinema cinemaAdmin)
    {
        this.cinemaAdmin = cinemaAdmin;
    }


    public void testSeances()
    {
        var test = collections[1];
        cinemaAdmin.addSalles(new Salle(1, new ArrayList<>(),53));
        cinemaAdmin.addSeance(1, new Seance(test.getMovie(6).getTitle(),test.getMovie(6),LocalDate.of(2022,3,18),"14H30",1,45));
        cinemaAdmin.addSeance(1, new Seance(test.getMovie(16).getTitle(),test.getMovie(16),LocalDate.of(2022,3,18),"16H30",1,45));
        cinemaAdmin.addSeance(1, new Seance(test.getMovie(6).getTitle(),test.getMovie(6),LocalDate.of(2022,3,19),"14H30",1,45));
        cinemaAdmin.addSeance(1, new Seance(test.getMovie(16).getTitle(),test.getMovie(16),LocalDate.of(2022,3,19),"16H30",1,45));
        cinemaAdmin.addSalles(new Salle(2, new ArrayList<>(),53));
        cinemaAdmin.addSeance(2, new Seance(test.getMovie(8).getTitle(),test.getMovie(8),LocalDate.of(2022,3,18),"14H30",1,45));
        cinemaAdmin.addSeance(2, new Seance(test.getMovie(18).getTitle(),test.getMovie(18),LocalDate.of(2022,3,18),"16H30",1,45));
        cinemaAdmin.addSeance(2, new Seance(test.getMovie(8).getTitle(),test.getMovie(8),LocalDate.of(2022,3,19),"14H30",1,45));
        cinemaAdmin.addSeance(2, new Seance(test.getMovie(18).getTitle(),test.getMovie(18),LocalDate.of(2022,3,19),"16H30",1,45));
        showSeances();
    }

}
