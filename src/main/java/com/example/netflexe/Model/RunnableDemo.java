package com.example.netflexe.Model;

import com.example.netflexe.Controller.HelloApplication;

public class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    private HelloApplication mainApp;
    private MovieCollection[] collection = {new MovieCollection()};

    public RunnableDemo( String name) {
        threadName = name;

    }

    public void run() {
        collection = mainApp.getMovieCollection(0);
        for(int i = 0; i<5; i ++)
        {
            collection[i].setImage();
            this.mainApp.get_sceneController().refreshMovie(i);
        }
        collection[0].setImage();

    }

    public void start () {

        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public void setMainApp(HelloApplication mainApp) {
        this.mainApp = mainApp;
    }


}

/*public class TestThread {

    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }
}*/