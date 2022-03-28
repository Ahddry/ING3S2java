package com.example.netflexe.Vue;

import com.example.netflexe.Model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import com.example.netflexe.Controller.HelloApplication;

import java.io.IOException;



public class SceneController
{

    private HelloApplication controller;
    private Stage primaryStage;
    private Scene scene;
    
    private BorderPane rootLayout;
    private AnchorPane loginLayout;
    private AnchorPane showGenre;
    private AnchorPane mainMenu;
    private AnchorPane biblio;



    private Profil profil = null;
    private Cinema cinemaAdmin;
    
    
    private ProfileController controller_profil;
    private MainMenuController icontroller;
    private BiblioController bcontroller;
    private MySceneController controller_main;
    private ProfileCinemaController cinemaController;
    private AnchorPane cinema;
    private ScrollPane scroll_cinema;

    private AdminController controller_admin;

    private FilmInfoController controller_infoFilm;
    private ScrollPane scroll_infoFilm;
    private AnchorPane info;

    private AnchorPane approAdmin;
    private ApprobationAdminController controller_attente_admin;
    private ScrollPane scroll_attente_admin;

    private AnchorPane creaCine;
    private CreationCinemaController controller_creaCine;
    private ScrollPane scroll_creaCine;

    private AnchorPane choixCine;
    private ChoixCinemaController controller_choixCine;
    private ScrollPane scroll_choixCine;

    private AnchorPane creationSeancesView;
    private CreationSeanceController controller_seanceView;
    private ScrollPane scroll_seanceView;

    private AnchorPane seancesView;
    private SeancesController controller_seancesView;
    private ScrollPane scroll_seancesView;

    private AnchorPane ajoutFilm;
    private AjoutFilmFormController controller_ajoutFilm;
    private ScrollPane scroll_ajoutFilm;

    private AnchorPane ajoutFilmc;
    private AjoutFilmController controller_ajoutFilmc;
    private ScrollPane scroll_ajoutFilmc;

    private AnchorPane accueilValiderReservation;
    private ValiderReservation controller_validerResa;
    private ScrollPane scroll_validerResa;

    private AnchorPane stats;
    private statsPageController controller_stats;
    private ScrollPane scroll_stats;

    private AnchorPane SaccueilAdmin;
    private AccueilAdminController controller_SacceulAdmin;
    private ScrollPane scroll_SacceulAdmin;

    private AnchorPane resa;
    private ReservationController controller_resa;
    private ScrollPane scroll_resa;

    private AnchorPane research;
    private ResearchController controller_research;
    private ScrollPane scroll_research;

    private AnchorPane Acteur;
    private FilmInfoController controller_acteur;
    
    private AnchorPane info2;
    private FilmInfoController controller_info2;
    private ScrollPane scroll_info2;

    private AnchorPane biblio2;
    private BiblioReserv controller_biblio;
    private ScrollPane scroll_biblio;
    
    private AnchorPane signUpLayout;
    private SignUpController controller_SignUp;
    private Scene sceneSignUp;

    private GenreLikeController controller_showGenre;
    private Scene scene_showGenre;

    private LoginController controller_Login;
    private Scene scene_Login;

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

    public void showMain()
    {
        try {
            if(controller_main == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MyScene.fxml"));
                rootLayout = (BorderPane) loader.load();
                scene = new Scene(rootLayout);
                primaryStage.setScene(scene);
                primaryStage.show();
                controller_main = loader.getController();
            }
                controller_main.setMainApp(this);
                controller_main.setProfil(user);
                controller_main.initialiseBis();
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

    public void showProfileCinema()
    {
        try {
            if(cinemaController == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ProfileCinema.fxml"));
                cinema = loader.load();
                cinemaController = loader.getController();
                cinemaController.setMainApp(this, controller);
                cinemaController.setCinema(cinemaAdmin);
                scroll_cinema = new ScrollPane();
            }
            scroll_cinema.setContent(cinema);
            rootLayout.setCenter(scroll_cinema);
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
            if(controller_Login == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("login.fxml"));
                loginLayout = (AnchorPane) loader.load();
                controller_Login = loader.getController();
                scene_Login = new Scene(loginLayout);
            }
            primaryStage.setScene(scene_Login);
            primaryStage.show();
            controller_Login.setMainApp(this, this.controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SignUp() {
        try {
            if(controller_SignUp == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("sign_up.fxml"));
                signUpLayout = (AnchorPane) loader.load();
                controller_SignUp = loader.getController();
                sceneSignUp = new Scene(signUpLayout);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
            controller_SignUp.setMainApp(this, this.controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAdmin()
    {
        try {

            if(controller_admin == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Admin.fxml"));
                rootLayout = loader.load();
                scene = new Scene(rootLayout);
                primaryStage.setScene(scene);
                primaryStage.show();
                controller_admin = loader.getController();
            }
            controller_admin.setMainApp(this);
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
                scrollmainMenu = new ScrollPane();
                icontroller.setMainApp(this, this.controller);
                scrollmainMenu.setContent(mainMenu);
                this.controller.load_bdd_movie();
                this.cinemaAdmin = cinemaCollection.getCinema(0);
                icontroller.initializeBis();
                controller.threadStarter();
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
            if(controller_showGenre == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("genreliker.fxml"));
                showGenre = (AnchorPane) loader.load();
                controller_showGenre = loader.getController();
                scene_showGenre = new Scene(showGenre);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
            controller_showGenre.setMainApp(this, this.controller);
            controller_showGenre.initializeBis();
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
                scrollBiblio = new ScrollPane();
                bcontroller.setMainApp(this);
                scrollBiblio.setContent(biblio);
                bcontroller.setCinemaC(cinemaCollection);
            }
            bcontroller.initializeBis(monProfil);
            rootLayout.setCenter(scrollBiblio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBiblioRes(Profil monProfil) {
        try {

            if(controller_biblio == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("BiblioReserv.fxml"));
                biblio2 = (AnchorPane) loader.load();
                controller_biblio = loader.getController();
                scroll_biblio = new ScrollPane();
                controller_biblio.setMainApp(this);
                scroll_biblio.setContent(biblio2);
            }
            controller_biblio.initializeBis(monProfil);
            rootLayout.setCenter(scroll_biblio);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfo(Movie movie,ActorCollection collectionActor, boolean admin) {
        try {

            if(controller_infoFilm == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FilmInfo.fxml"));
                info = (AnchorPane) loader.load();
                controller_infoFilm = loader.getController();
                scroll_infoFilm = new ScrollPane();
            }
            controller_infoFilm.setMainApp(this);
            controller_infoFilm.setMovie(movie);
            controller_infoFilm.showActors(collectionActor);
            controller_infoFilm.setProfil(user);
            controller_infoFilm.setAdminAccess(admin);
            controller_infoFilm.setCinema(cinemaAdmin);
            scroll_infoFilm.setContent(info);
            rootLayout.setCenter(scroll_infoFilm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
///Pas oublier de changer pour Biblio etc (ajouter aussi l'affichage des acteurs)
    public void showInfo(Movie movie, boolean admin) {
        try {

            if(controller_info2 == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("FilmInfo.fxml"));
                info2 = (AnchorPane) loader.load();
                controller_info2 = loader.getController();
                scroll_info2 = new ScrollPane();
            }
            controller_info2.setMainApp(this);
            controller_info2.setMovie(movie);
            controller_info2.setProfil(user);
            controller_info2.setAdminAccess(admin);
            controller_info2.setCinema(cinemaAdmin);
            controller_info2.setBiblioController(bcontroller);
            scroll_info2.setContent(info);
            rootLayout.setCenter(scroll_info2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showInfoActor(Actor acteur) throws IOException {
        
        if(controller_acteur == null)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("FilmInfo.fxml"));
            Acteur = (AnchorPane) loader.load();
            controller_acteur = loader.getController();
        }

        controller_acteur.setMainApp(this);
        controller_acteur.setActor(acteur);
    }

    public void showResearch(boolean admin)
    {
        try {

            if(controller_research == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Research.fxml"));
                research = (AnchorPane) loader.load();
                controller_research = loader.getController();
                scroll_research = new ScrollPane();
                controller_research.setMainApp(this);
                controller_research.setAdminSelect(admin);
                scroll_research.setContent(research);
                controller_research.initializeBis();
            }
            rootLayout.setCenter(scroll_research);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showReservation(Movie movie)
    {

        try
        {
            if(controller_resa == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Reservation.fxml"));
                resa = (AnchorPane) loader.load();
                controller_resa = loader.getController();
                scroll_resa = new ScrollPane();
                controller_resa.setMainApp(this);
            scroll_resa.setContent(resa);
            }
            controller_resa.initializeBis(cinemaCollection, movie);
            rootLayout.setCenter(scroll_resa);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showAccueilAdmin()
    {
        try {
            if(controller_SacceulAdmin == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AccueilAdmin.fxml"));
                SaccueilAdmin = loader.load();
                controller_SacceulAdmin = loader.getController();
                scroll_SacceulAdmin = new ScrollPane();
                controller_SacceulAdmin.setMainApp(this);
                scroll_SacceulAdmin.setContent(SaccueilAdmin);
            }
            rootLayout.setCenter(scroll_SacceulAdmin);
            controller_SacceulAdmin.init(cinemaAdmin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStats()
    {
        try {
            if(controller_stats == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("statsPage.fxml"));
                stats = loader.load();
                controller_stats = loader.getController();
                scroll_stats = new ScrollPane();
                scroll_stats.setContent(stats);
            }
            rootLayout.setCenter(scroll_stats);
            controller_stats.init(cinemaAdmin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showValiderReseravtion(Movie movie, Cinema cinema, Profil profil)
    {
        try {
            if(controller_validerResa == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ValiderReservation.fxml"));
                accueilValiderReservation = loader.load();
                controller_validerResa = loader.getController();
                scroll_validerResa = new ScrollPane();
            }
            controller_validerResa.setMainApp(this);
            controller_validerResa.setProfil(profil);
            controller_validerResa.initializeBis(movie, cinema);
            scroll_validerResa.setContent(accueilValiderReservation);
            rootLayout.setCenter(scroll_validerResa);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAjouterFilm(Cinema c)
    {
        try {
            if(controller_ajoutFilmc == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AjoutFilm.fxml"));
                ajoutFilmc = loader.load();
                controller_ajoutFilmc = loader.getController();
                scroll_ajoutFilmc = new ScrollPane();
            }
            controller_ajoutFilmc.setMainApp(this);
            controller_ajoutFilmc.setCinema(c);
            scroll_ajoutFilmc.setContent(ajoutFilmc);
            rootLayout.setCenter(scroll_ajoutFilmc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAjoutFilmForm(Cinema c)
    {
        try {
            if(controller_ajoutFilm == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AjoutFilmForm.fxml"));
                ajoutFilm = loader.load();
                controller_ajoutFilm = loader.getController();
                scroll_ajoutFilm = new ScrollPane();
            }
            controller_ajoutFilm.setMainApp(this);
            controller_ajoutFilm.setCinema(c);
            controller_ajoutFilm.init();
            scroll_ajoutFilm.setContent(ajoutFilm);
            rootLayout.setCenter(scroll_ajoutFilm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSeances()
    {
        try {
            if(controller_seancesView == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Seances.fxml"));
                seancesView = loader.load();
                controller_seancesView = loader.getController();
                scroll_seancesView = new ScrollPane();
            }
            controller_seancesView.setMainApp(this);
            controller_seancesView.init(cinemaAdmin);
            scroll_seancesView.setContent(seancesView);
            rootLayout.setCenter(scroll_seancesView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreationSeance(Movie movie)
    {
        try {
            if(controller_seanceView == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CreationSeance.fxml"));
                creationSeancesView = loader.load();
                controller_seanceView = loader.getController();
                scroll_seanceView = new ScrollPane();
            }
            controller_seanceView.setMainApp(this);
            controller_seanceView.setMovie(movie);
            controller_seanceView.setCinema(cinemaAdmin);
            scroll_seanceView.setContent(creationSeancesView);
            rootLayout.setCenter(scroll_seanceView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChoixCinema(Profil profil)
    {
        try
        {
            if(controller_choixCine == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ChoixCinema.fxml"));
                choixCine = loader.load();
                controller_choixCine = loader.getController();
                scroll_choixCine = new ScrollPane();
                controller_choixCine.setMainApp(this);
                controller_choixCine.init(cinemaCollection, profil);
            }
            scroll_choixCine.setContent(choixCine);
            rootLayout.setCenter(scroll_choixCine);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showCreationCinema(Profil profil)
    {
        try
        {
            if(controller_creaCine == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CreationCinema.fxml"));
                creaCine = loader.load();
                controller_creaCine = loader.getController();
                scroll_creaCine = new ScrollPane();
            }
            controller_creaCine.setMainApp(this, cinemaCollection, profil);
            scroll_creaCine.setContent(creaCine);
            rootLayout.setCenter(scroll_creaCine);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showApprobationAdmin()
    {
        try
        {
            if(controller_attente_admin == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ApprobationAdmin.fxml"));
                approAdmin = loader.load();
                controller_attente_admin = loader.getController();
                scroll_attente_admin = new ScrollPane();
            } 
            controller_attente_admin.init(this);
            scroll_attente_admin.setContent(approAdmin);
            rootLayout.setCenter(scroll_attente_admin);
        } catch (IOException e)
        {
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
    public CinemaCollection getCinemaCollection()
    {
        return cinemaCollection;
    }

    public void setCinemaAdmin(Cinema cinemaAdmin)
    {
        this.cinemaAdmin = cinemaAdmin;
    }
    public HelloApplication getHello()
    {
        return this.controller;
    }

}
