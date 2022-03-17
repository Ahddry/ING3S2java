package com.example.netflexe.Vue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;



import com.example.netflexe.Controller.HelloApplication;
import com.example.netflexe.Model.Profil;

public class ProfileController{
    private SceneController mainApp;
    private HelloApplication controller;
    private String linkFile = "";
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView sizewarning;
    @FXML
    private Button pp_change;
    @FXML
    private ImageView PP;
    @FXML
    private ImageView pp_change_display;
    @FXML
    private Button submit_pp;
    
    @FXML
    private Label nom_prenom;
    @FXML
    private TextField prenom_field;
    @FXML
    private Button submit_prenom;

    @FXML
    private TextField nom_field;
    @FXML
    private Button submit_nom;

    @FXML
    private TextField mail_edit;
    @FXML
    private Label mail;
    @FXML
    private Button submit_mail;

    @FXML
    private PasswordField pwd_field;
    @FXML
    private Button submit_pwd;

    @FXML
    private DatePicker naissance;
    @FXML
    private Label age;
    @FXML
    private Button submit_naissance;

    @FXML
    private Label genre;
    @FXML
    private ChoiceBox<String> genre_field;
    @FXML
    private TextField autres_field;
    @FXML
    private Button submit_genre;

    @FXML
    private void initialize() {
        genre_field.getItems().add("Homme");
        genre_field.getItems().add("Femme");
        root.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1  ) {
                root.requestFocus();
            }
        });


        submit_pp.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                if(linkFile != "")
                {
                    try {
                        this.controller.modify_user("pp", new FileInputStream(linkFile));
                        //this.mainApp.showProfile();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        submit_prenom.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                if(prenom_field.getText() != "")
                {
                    this.controller.modify_user("prenom",prenom_field.getText());
                    //this.mainApp.showProfile();
                }
            }
        });
        submit_nom.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1)
            {
                if(nom_field.getText() != "")
                {
                    this.controller.modify_user("nom",nom_field.getText());
                }
            }
        });

        submit_mail.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1)
            {
                if(mail_edit.getText() != "")
                {
                    this.controller.modify_user("email",mail_edit.getText());
                }
            }
        });
        submit_pwd.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1)
            {
                if(pwd_field.getText() != "")
                {
                    this.controller.modify_user("mdp",pwd_field.getText());
                }
            }
        });
        submit_naissance.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1)
            {
                LocalDate value = naissance.getValue();
                if(value != null)
                {
                    this.controller.modify_user("date_de_naissance", String.valueOf(value.getYear()) + "-" + String.valueOf(value.getMonthValue()) + "-" + String.valueOf(value.getDayOfMonth()));
                }
            }
        });
        submit_genre.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1)
            {
                String final_genre = "";
                if(genre_field.getValue() != "" && autres_field.getText() == "")
                {
                    final_genre = genre_field.getValue();
                }
                else if(genre_field.getValue() == "" && autres_field.getText() != "")
                {
                    final_genre = autres_field.getText();
                }
                this.controller.modify_user("genre", final_genre);
            }
        });


        pp_change.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new ExtensionFilter("Image Files", "*.png", "*.jpg","*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(this.controller.get_stage().getOwner());
                if (selectedFile != null && selectedFile.length() < 5000000) {
                    this.linkFile = selectedFile.getAbsolutePath();
                    sizewarning.setVisible(false);
                    pp_change.setOpacity(0.0);
                    try{
                        FileInputStream file = new FileInputStream(selectedFile.getAbsolutePath());
                        pp_change_display.setImage(new Image(file));
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
                else
                {
                    //System.out.println("erreur fichier trop gros");
                    sizewarning.setVisible(true);
                }
            }
        });        
    }

    public void setLabels(Profil user)
    {
        nom_prenom.setText(user.get_prenom() + " " +user.get_nom());
        mail.setText(user.get_mail());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(user.get_age());
            Calendar bday = new GregorianCalendar();
            Calendar today = new GregorianCalendar();
            bday.setTime(date);
            today.setTime(new Date());
            age.setText(String.valueOf(today.get(Calendar.YEAR) 
            - bday.get(Calendar.YEAR) + " ans"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        genre.setText(user.get_genre());
        PP.setImage(null);
        PP.setImage(new Image(user.get_pp()));
    }
    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
        root.requestFocus();
    }
}
