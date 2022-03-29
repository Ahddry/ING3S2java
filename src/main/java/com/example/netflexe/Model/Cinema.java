package com.example.netflexe.Model;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int id_cine;
    private String nom;
    private MovieCollection filmP = new MovieCollection();
    private ArrayList<String> filmProjetes = new ArrayList<String>();
    private Image image = null;
    private String imageString = "";
    private List<Salle> salles = new ArrayList<>();
    private ArrayList<Promo> promos  = new ArrayList<Promo>();
    private ArrayList<String> promoUtilisees = new ArrayList<String>();
    private ArrayList<String> filmVendus = new ArrayList<String>();

    public Cinema()
    {

    }

    public Cinema(int id_cine,String nom,String image)
    {
        this.id_cine = id_cine;
        this.nom = nom;
        this.imageString = image;
    }

    public boolean checkMovie(String name)
    {
        boolean result = false;
        for(int i = 0;i< salles.size();i++)
        {
            filmProjetes = salles.get(i).getFilmP();
            for(int j = 0; j<filmProjetes.size(); j++)
            {
                if(filmProjetes.get(j).equals(name)){

                    result  = true;
                }
            }
        }
        return result;
    }

    public void ajoutFilm(Movie movie)
    {
        filmP.addMovie(movie);
    }

    public void setImage()
    {
        if (imageString != null)
            image = new Image(imageString);
    }
    public void set_id_cine(int id_cine)
    {
        this.id_cine = id_cine;
    }
    public int get_id_cine()
    {
        return this.id_cine;
    }

    public Image getImage()
    {
        return image;
    }

    public String getImageString()
    {
        return imageString;
    }

    public String getName()
    {
        return this.nom;
    }

    public MovieCollection getFilmP()
    {
        return filmP;
    }

    public void setFilmP(MovieCollection filmP)
    {
        this.filmP = filmP;
    }

    public List<Salle> getSalles()
    {
        return salles;
    }

    public void addSalles(Salle salle)
    {
        salles.add(salle);
    }

    public boolean deleteSalles(int numSalle)
    {
        Salle s = new Salle();
        for (var salle : salles)
        {
            if (salle.getNumero() == numSalle)
                s = salle;
        }
        if (salles.contains(s))
            salles.remove(s);
        else
            return false;
        return true;
    }

    public void addSeance(int salle, Seance seance)
    {
        for (var elem : salles)
        {
            if (elem.getNumero() == salle)
            {
                elem.addSeance(seance);
            }
        }
    }

    public int getIdSalle(int numSalle)
    {
        for (var salle : salles)
        {
            if (salle.getNumero() == numSalle)
                return salle.get_id_bdd();
        }
        return 0;
    }

    public void updateStatsPromo(String promo)
    {
        promoUtilisees.add(promo);
    }
    public void updateStatsFilm(String title)
    {
        filmVendus.add(title);
    }

    public ArrayList<String> getPromoUtilisees()
    {
        return promoUtilisees;
    }

    public ArrayList<String> getFilmVendus( ) {return filmVendus;}

    public void setSalles(List<Salle> salles)
    {
        this.salles = salles;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setImageString(String imageString)
    {
        this.imageString = imageString;
    }

    public boolean contains(int num)
    {
        for (var salle : salles)
        {
            if (salle.getNumero() == num)
                 return true;
        }
        return false;
    }
    public void add_promo(Promo promo)
    {
        this.promos.add(promo);
    }
    public ArrayList<Promo> get_promos()
    {
        return this.promos;
    }
    public void deletePromo(int idPromo)
    {
        for (var promo : promos)
        {
            if (promo.get_idPromo() == idPromo)
            {
                promos.remove(promo);
                break;
            }
        }
    }
}
