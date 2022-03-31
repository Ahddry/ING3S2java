package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;

/**
 * Classe java gérant les contrôles et évènements de la vue AjoutFilm.fxml
 */
public class AjoutFilm
{
    private SceneController mainApp;
    private Cinema cinema;

    /**
     * Appel de la recherche d'un film existant à ajouter au cinéma
     */
    public void existantBoutonClick()
    {
        mainApp.showResearch(true);
    }

    /**
     * Appel du menu de création d'un nouveau film à ajouter au cinéma
     */
    public void nouveauBoutonClick()
    {
        mainApp.showAjoutFilmForm(cinema);
    }

    /**
     * Setter pour le cinéma auquel on doit ajouter un film
     * @param cinema cinéma à ajouter
     */
    public void setCinema(Cinema cinema)
    {
        this.cinema = cinema;
    }

    /**
     * Affecte un contrôleur SceneController à cette classe
     * @param mainApp contrôleur à affecter
     */
    public void setMainApp(SceneController mainApp)
    {
        this.mainApp = mainApp;
    }
}
