package com.example.netflexe.Model;
import com.example.netflexe.Vue.FilmInfo;

public class RunnableDemoActor implements Runnable {
    private Thread t;
    private String threadName;

    private FilmInfo mainApp;
    private ActorCollection collection = new ActorCollection();

    /**
     * constructeur on l'on spécifie uniquement le nom du nouveau thread
     * @param name
     */
    public RunnableDemoActor( String name) {
        threadName = name;

    }

    /**
     * thread lance cette méthode lorsqu'il démarre
     * on charge les images pour chaque éléments de la actor collection
     */
    public void run() {

            collection.setImage();
            this.mainApp.refresher();


    }

    /**
     * permet de lancer le thread
     */
    public void start () {

        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public void setActorCollection(ActorCollection actorCollection) {
        this.collection = actorCollection;
    }

    /**
     * fait une référence à l'application principale
     * @param mainApp
     */
    public void setMainApp(FilmInfo mainApp) {
        this.mainApp = mainApp;
    }


}

