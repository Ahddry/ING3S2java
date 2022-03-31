package com.example.netflexe.Vue;

import com.example.netflexe.Controller.SceneController;
import com.example.netflexe.Model.Cinema;
import com.example.netflexe.Model.Promo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Classe java gérant les contrôles et évènements de la vue CreationPromo.fxml
 */
public class CreationPromo
{
	@FXML
	private TextField nomPromo;
	@FXML
	private TextField valeurPromo;
	@FXML
	private TextField ageMini;
	@FXML
	private TextField ageMaxi;

	private SceneController mainApp;
	private Cinema cinema;

	/**
	 * Retour à l'accueil administrateur
	 */
	public void retour()
	{
		mainApp.showAccueilAdmin();
	}

	public void setMainApp(SceneController mainApp, Cinema cinema)
	{
		this.mainApp = mainApp;
		this.cinema = cinema;
	}

	/**
	 * Création et enregistrement dans la base de données de la nouvelle promotion créée d'après les saisies utilisateur et ajout de celle-ci au cinéma.
	 */
	public void confirmerBoutonClick()
	{
		if ((!isNullOrWhiteSpace(nomPromo.getText())) && (!isNullOrWhiteSpace(valeurPromo.getText()) && (!isNullOrWhiteSpace(ageMaxi.getText()) && (!isNullOrWhiteSpace(ageMini.getText())))))
		{
			boolean exists = false;
			for (var promo : cinema.get_promos())
			{
				if (nomPromo.getText() == promo.get_nomPromo())
					exists = true;
			}

			boolean valValide = false;
			boolean minValide = false;
			boolean maxValide = false;
			double valPromo = 0;
			int min = 0;
			int max = 0;
			try
			{
				valPromo = Double.parseDouble(valeurPromo.getText());
				if (valPromo <= 0.0 || valPromo >= 100.0)
					throw new NumberFormatException("Valeur de promotion non valide.");
				else valValide = true;
			} catch (NumberFormatException ex)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
				alert.show();
			}
			try
			{
				min = Integer.parseInt(ageMini.getText());
				if (min <= 0)
					throw new NumberFormatException("Valeur non valide pour l'âge minimal.");
				else minValide = true;
			} catch (NumberFormatException ex)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
				alert.show();
			}
			try
			{
				max = Integer.parseInt(ageMaxi.getText());
				if (max <= min)
					throw new NumberFormatException("Valeur non valide pour l'âge maximal.");
				else maxValide = true;
			} catch (NumberFormatException ex)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
				alert.show();
			}

			if (!exists && valValide && maxValide && minValide)
			{
				int idPromo = mainApp.getHello().add_promotion(cinema.get_id_cine(), nomPromo.getText(), valPromo/100.0, min, max);
				if (idPromo == -1)
				{
					Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la création de la promotion.", ButtonType.OK);
					alert.show();
				}
				else
				{
					Promo promo = new Promo(idPromo, nomPromo.getText(), valPromo/100.0, min, max);
					cinema.add_promo(promo);
					mainApp.setCinemaAdmin(cinema);
					mainApp.getProfil().setCinema(cinema);
					mainApp.showAccueilAdmin();
				}
			} else if (exists)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR, "Une promotion portant ce nom existe déjà.", ButtonType.OK);
				alert.show();
			}

		} else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez renseigner tous les champs.", ButtonType.OK);
			alert.show();
		}
	}

	/**
	 * Méthode vérifiant si une chaine de caractères donnée est : nulle, vide ou remplie d'espaces blancs.
	 * @param s Chaine de caractères dont il faut vérifier le contenu
	 * @return True si la chaine de caractère est : nulle, vide ou remplie d'espaces blancs, False sinon.
	 */
	private static boolean isNullOrWhiteSpace(String s)
	{
		if (isNullOrEmpty(s))
		{
			return true;
		} else
		{
			int length = s.length();
			if (length > 0)
			{
				for (int i = 0; i < length; i++)
				{
					if (!Character.isWhitespace(s.charAt(i)))
					{
						return false;
					}
				}
				return true;
			}
			return false;
		}
	}
}
