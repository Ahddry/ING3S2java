package com.example.netflexe.Model;

import com.example.netflexe.Controller.HelloApplication;

public class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    private HelloApplication mainApp;
    private MovieCollection[] collection = {new MovieCollection()};

    /**
     * constructeur on l'on spécifie uniquement le nom du nouveau thread
     * @param name
     */
    public RunnableDemo( String name) {
        threadName = name;

    }

    /**
     * thread lance cette méthode lorsqu'il démarre
     * on charge les images pour chaque éléments de la movie collection
     */
    public void run() {
        collection = mainApp.getMovieCollection(0);
        for(int i = 0; i<6; i ++)
        {
            collection[i].setImage();
            this.mainApp.get_sceneController().refreshMovie(i);
        }


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

    /**
     * fait une référence à l'application principale
     * @param mainApp
     */
    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }


}

