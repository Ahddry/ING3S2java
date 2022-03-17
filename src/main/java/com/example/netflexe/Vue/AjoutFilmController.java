package com.example.netflexe.Vue;

import com.example.netflexe.Model.Cinema;

public class AjoutFilmController
{
    private SceneController mainApp;
    private Cinema cinema;

    public void existantBoutonClick()
    {
        mainApp.showResearch(true);
    }

    public void nouveauBoutonClick()
    {
        mainApp.showAjoutFilmForm(cinema);
    }

    public void setCinema(Cinema cinema)
    {
        this.cinema = cinema;
    }

    public void setMainApp(SceneController mainApp)
    {
        this.mainApp = mainApp;
    }
}
