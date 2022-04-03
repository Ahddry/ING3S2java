package com.example.netflexe.Controller;

import com.example.netflexe.Model.*;
import com.example.netflexe.Vue.AccueilAdmin;
import com.example.netflexe.Vue.Admin;
import com.example.netflexe.Vue.AjoutFilm;
import com.example.netflexe.Vue.AjoutFilmForm;
import com.example.netflexe.Vue.ApprobationAdmin;
import com.example.netflexe.Vue.Biblio;
import com.example.netflexe.Vue.BiblioReserv;
import com.example.netflexe.Vue.ChoixCinema;
import com.example.netflexe.Vue.CreationCinema;
import com.example.netflexe.Vue.CreationPromo;
import com.example.netflexe.Vue.CreationSeance;
import com.example.netflexe.Vue.FilmInfo;
import com.example.netflexe.Vue.GenreLike;
import com.example.netflexe.Vue.Login;
import com.example.netflexe.Vue.MainMenu;
import com.example.netflexe.Vue.MyScene;
import com.example.netflexe.Vue.ProfileCinema;
import com.example.netflexe.Vue.Profile;
import com.example.netflexe.Vue.Research;
import com.example.netflexe.Vue.ReservationVue;
import com.example.netflexe.Vue.Seances;
import com.example.netflexe.Vue.SignUp;
import com.example.netflexe.Vue.ValiderReservation;
import com.example.netflexe.Vue.statsPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Class contenant le controlleur principale de l'application permet de changer les differentes vues en fonction de l'endroit ou l'on se trouve dans l'application, permet de gerer la mémoire pour éviter un grossisement de l'application au cours du temps
 */
public class SceneController
{

    private HelloApplication controller;
    private Stage primaryStage;
    private Scene scene;
    private Scene scene_admin;
    private Scene scene_crea;
    private Scene choixCineScene;
    private BorderPane rootLayout;
    private BorderPane rootLayout_admin;
    private AnchorPane loginLayout;
    private AnchorPane showGenre;
    private AnchorPane mainMenu;
    private AnchorPane biblio;



    private Profil profil = null;
    private Cinema cinemaAdmin;
    
    
    private Profile controller_profil;
    private MainMenu icontroller;
    private Biblio bcontroller;
    private MyScene controller_main;
    private ProfileCinema cinemaController;
    private AnchorPane cinema;
    private ScrollPane scroll_cinema;

    private Admin controller_admin;

    private FilmInfo controller_infoFilm;
    private ScrollPane scroll_infoFilm;
    private AnchorPane info;

    private AnchorPane approAdmin;
    private ApprobationAdmin controller_attente_admin;
    private ScrollPane scroll_attente_admin;

    private AnchorPane creaPromo;
    private CreationPromo creationPromoController;
    private ScrollPane scrollPromo;

    private AnchorPane creaCine;
    private CreationCinema controller_creaCine;
    private ScrollPane scroll_creaCine;

    private AnchorPane choixCine;
    private ChoixCinema controller_choixCine;
    private ScrollPane scroll_choixCine;

    private AnchorPane creationSeancesView;
    private CreationSeance controller_seanceView;
    private ScrollPane scroll_seanceView;

    private AnchorPane seancesView;
    private Seances controller_seancesView;
    private ScrollPane scroll_seancesView;

    private AnchorPane ajoutFilm;
    private AjoutFilmForm controller_ajoutFilm;
    private ScrollPane scroll_ajoutFilm;

    private AnchorPane ajoutFilmc;
    private AjoutFilm controller_ajoutFilmc;
    private ScrollPane scroll_ajoutFilmc;

    private AnchorPane accueilValiderReservation;
    private ValiderReservation controller_validerResa;
    private ScrollPane scroll_validerResa;

    private AnchorPane stats;
    private statsPage controller_stats;
    private ScrollPane scroll_stats;

    private AnchorPane SaccueilAdmin;
    private AccueilAdmin controller_SacceulAdmin;
    private ScrollPane scroll_SacceulAdmin;

    private AnchorPane resa;
    private ReservationVue controller_resa;
    private ScrollPane scroll_resa;

    private AnchorPane research;
    private Research controller_research;
    private ScrollPane scroll_research;

    private AnchorPane biblio2;
    private BiblioReserv controller_biblio;
    private ScrollPane scroll_biblio;
    
    private AnchorPane signUpLayout;
    private SignUp controller_SignUp;
    private Scene sceneSignUp;

    private GenreLike controller_showGenre;
    private Scene scene_showGenre;

    private Login controller_Login;
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

    /**
     * Méthode permettant d'afficher le menu sur la gauche avec les différents action possible pour l'utilisateur.
     * Cette méthode instancie la vue du menu et ne regenere pas a chaque appel
     */
    public void showMain()
    {
        try {
            if(controller_main == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MyScene.fxml"));
                rootLayout = (BorderPane) loader.load();
                scene = new Scene(rootLayout);
                controller_main = loader.getController();
            }
            primaryStage.setScene(scene);
            primaryStage.show();
            controller_main.setMainApp(this);
            controller_main.setProfil(user);
            controller_main.initialiseBis();
            showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Méthode permettant d'afficher le profil de l'utilisateur, cette méthode n'est disponible que si l'utilisateur est login sur l'application.
     * Cette méthode permet d'instancier la vue de l'affichage du profil de l'utilisateur
     */
    public void showProfile()
    {
        try {
            if(controller_profil == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Profile.fxml"));
                loginLayout = (AnchorPane) loader.load();
                controller_profil = loader.getController();
                controller_profil.setMainApp(this.controller);
                controller_profil.setLabels(user);
            }
            rootLayout.setCenter(loginLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode d'affichage du Profil du cinéma qui n'est accessible que du côté admnistrateur
     */
    public void showProfileCinema()
    {
        try {
            if(cinemaController == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("ProfileCinema.fxml"));
                cinema = loader.load();
                cinemaController = loader.getController();
                cinemaController.setMainApp(this);
                cinemaController.setCinema(cinemaAdmin);
                scroll_cinema = new ScrollPane();
            }
            scroll_cinema.setContent(cinema);
            rootLayout_admin.setCenter(scroll_cinema);
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

    /**
     * Méthode permettant d'afficher la page de login de l'application elle est la première méthode appellé au lancement de l'application pour que l'utilsateur puisse se login
     */
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
    /**
     * Méthode permettant d'instancier la vue de création du compte utilisateur 
     */
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
            primaryStage.setScene(sceneSignUp);
            primaryStage.show();
            controller_SignUp.setMainApp(this, this.controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'afficher la partie admnistrateur de l'application
     */
    public void showAdmin()
    {
        try {

            if(controller_admin == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Admin.fxml"));
                rootLayout_admin = loader.load();
                scene_admin = new Scene(rootLayout_admin);
                controller_admin = loader.getController();
            }
            primaryStage.setScene(scene_admin);
            primaryStage.show();
            controller_admin.setMainApp(this);
            showAccueilAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'afficher l'écran d'accueil de l'application et d'appeller la méthode qui va récupérer les différentes collections de film dans l'application
     */
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
                //this.cinemaAdmin = cinemaCollection.getCinema(0);
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

    /**
     * Instancie le liker de genre de l'application cette méthode est appelé par signUp au moment ou le compte de l'utilisateur est bien crée
     */
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
            primaryStage.setScene(scene_showGenre);
            primaryStage.show();
            controller_showGenre.setMainApp(this, this.controller);
            controller_showGenre.initializeBis();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'instancier la vue de la bibiliothéque
     * @param monProfil profil de l'utilsateur afin d'afficher les films likés les film déjà vu et les films liké qui sortiront dans le futur
     */
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
                bcontroller.initializeBis(monProfil);
            }
            bcontroller.updateBiblio(monProfil);
            scrollBiblio.setContent(biblio);
            bcontroller.setCinemaC(cinemaCollection);
            rootLayout.setCenter(scrollBiblio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Méthode permettant d'afficher les réservations d'un utilisateur
     * @param monProfil profil de l'utilsateur contenant les réservations
     */
    public void showBiblioRes(Profil monProfil) {
        try {

            if(controller_biblio == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("BiblioReserv.fxml"));
                biblio2 = (AnchorPane) loader.load();
                controller_biblio = loader.getController();
                scroll_biblio = new ScrollPane();
            }
            scroll_biblio.setContent(biblio2);
            controller_biblio.initializeBis(monProfil);
            rootLayout.setCenter(scroll_biblio);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche les informations du film ainsi que les différents acteur présent dans le casting du film
     * @param movie film a afficher
     * @param collectionActor collection d'acteur a afficher
     * @param admin boolean permettant de changer l'affichage en fonction de si l'utilisateur est dans la partie utilisateur de l'application ou dans la partie admnistration de l'application
     */
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
            controller_infoFilm.showActors(this.controller.CollectionActeursMovie(movie.get_idFilm()));
            controller_infoFilm.setProfil(user);
            controller_infoFilm.setAdminAccess(admin);
            controller_infoFilm.setCinema(cinemaAdmin);
            scroll_infoFilm.setContent(info);
            if(admin)
            {
                rootLayout_admin.setCenter(scroll_infoFilm);
            }
            else
            {
                rootLayout.setCenter(scroll_infoFilm);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode permettant de set la liste d'acteur a afficher pour un film en particulier
     * @param acteur acteur a afficher
     * @throws IOException
     */
    public void showInfoActor(Actor acteur) throws IOException {
        controller_infoFilm.setActor(acteur);
    }

    /**
     * Méthode permettant de chercher dans la collection de film de la base de donnée
     * @param admin boolean qui change l'affichage en fonction de ou se trouve l'utilisateur, (admin ou user)
     */
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
                scroll_research.setContent(research);
                controller_research.initializeBis();
            }
            controller_research.setAdminSelect(admin);
            if(admin)
            {
                rootLayout_admin.setCenter(scroll_research);
            }
            else
            {
                rootLayout.setCenter(scroll_research);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'afficher l'écran de réservation pour un utilisateur
     * @param movie film sur le quel on veut reserver une seance
     */
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

    /**
     * Méthode permettant l'affichage du menu admin dans la partie admnistration de l'application
     */
    public void showAccueilAdmin()
    {
        try {
            if(controller_SacceulAdmin == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("AccueilAdmin.fxml"));
                SaccueilAdmin = loader.load();
                this.cinemaAdmin = this.user.getCinema();
                controller_SacceulAdmin = loader.getController();
                scroll_SacceulAdmin = new ScrollPane();
                controller_SacceulAdmin.setMainApp(this);
                if(controller_seancesView == null)
                    showSeances();
            }
            scroll_SacceulAdmin.setContent(SaccueilAdmin);
            rootLayout_admin.setCenter(scroll_SacceulAdmin);
            controller_SacceulAdmin.init(cinemaAdmin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'afficher les statistiques du cinéma que l'on manage
     */
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
                //this.cinemaAdmin = this.user.getCinema();
                controller_stats.init(cinemaAdmin, getHello().get_nombreReserv(cinemaAdmin.get_id_cine()));
            }
            rootLayout_admin.setCenter(scroll_stats);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'afficher une confirmation de la reservation après commande
     * @param movie film a afficher
     * @param cinema cinéma dans lequel on a pris la séance
     * @param profil profil de l'utilisateur
     */
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

    /**
     * Méthode permettant d'instancier la vue qui ajoute un film dans le cinéma, cette méthode est offline
     * @param c cinéma dans le quel on doit ajouter le film
     */
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
            rootLayout_admin.setCenter(scroll_ajoutFilmc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'instancier la vue qui permet de spécifier les informations du film que l'on veut ajouter dans le cinéma
     * @param c cinéma dans lequel on ajoute le film
     */
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
            rootLayout_admin.setCenter(scroll_ajoutFilm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'afficher les séances présente dans un cinéma
     */
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
            rootLayout_admin.setCenter(scroll_seancesView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui permet d'instancier la vue qui permet d'ajouter une séance pour un certains film
     * @param movie film a projeter
     */
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
            rootLayout_admin.setCenter(scroll_seanceView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant a un admin qui ne possede pas encore de cinéma de pouvoir choisir son cinéma
     * @param profil Profil de l'admnistrateur qui va se voir attribuer un cinema
     */
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
                scene_admin = new Scene(choixCine);
            }
            scroll_choixCine.setContent(choixCine);
            primaryStage.setScene(scene_admin);
            primaryStage.show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant de creer un nouveau cinéma
     * @param profil profil de l'admnistrateur qui va gérer le cinéma
     */
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
                scene_crea = new Scene(creaCine);
            }
            controller_creaCine.setMainApp(this, cinemaCollection, profil);
            primaryStage.setScene(scene_crea);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'instancier la vue qui permet d'afficher les admins en attente
     */
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
            rootLayout_admin.setCenter(scroll_attente_admin);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Méthode permettant d'instancier la vue qui permet d'ajouter des promos dans un cinéma
     * @param c cinéma dans lequel on veut ajouter une promotion
     */
    public void showCreationPromo(Cinema c)
    {
        try
        {
            if(creationPromoController == null)
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CreationPromo.fxml"));
                creaPromo = loader.load();
                creationPromoController = loader.getController();
                scrollPromo = new ScrollPane();
            }
            creationPromoController.setMainApp(this, c);
            scrollPromo.setContent(creaPromo);
            rootLayout_admin.setCenter(scrollPromo);
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
    public MovieCollection getMovieCollection2()
    {
        return collections[5];
    }
    public Seances getSeanceController()
    {
        return this.controller_seancesView;
    }

    public Scene getScene()
    {
        return scene;
    }
    public Scene getSceneAdmin()
    {
        return scene_admin;
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
