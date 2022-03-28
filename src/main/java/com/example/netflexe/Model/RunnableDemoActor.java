package com.example.netflexe.Model;
import com.example.netflexe.Vue.FilmInfoController;

public class RunnableDemoActor implements Runnable {
    private Thread t;
    private String threadName;

    private FilmInfoController mainApp;
    private ActorCollection collection = new ActorCollection();

    public RunnableDemoActor( String name) {
        threadName = name;

    }

    public void run() {

            collection.setImage();
            this.mainApp.refresher();


    }

    public void start () {

        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public void setActorCollection(ActorCollection actorCollection) {
        this.collection = actorCollection;
    }

    public void setMainApp(FilmInfoController mainApp) {
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