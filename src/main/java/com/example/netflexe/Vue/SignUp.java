package com.example.netflexe.Vue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.File;
import java.io.FileInputStream;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.time.LocalDate;

import com.example.netflexe.Controller.HelloApplication;
import com.example.netflexe.Controller.SceneController;

public class SignUp{
    private SceneController mainApp;
    private HelloApplication controller;
    private String linkFile;
    @FXML
    private AnchorPane root;
    @FXML
    private ImageView logo;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private ChoiceBox<String> genre;
    @FXML
    private TextField autres;
    @FXML
    private DatePicker naissance;
    @FXML
    private CheckBox admin;
    @FXML
    private ImageView warning;
    @FXML
    private ImageView warning2;
    @FXML
    private TextField login;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button signup_btn;
    @FXML
    private ImageView return_arrow;
    @FXML
    private Button pp;
    @FXML
    private ImageView pp_file;

    @FXML
    private void initialize() {


        root.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1  ) {
                root.requestFocus();
            }
        });

        return_arrow.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                this.mainApp.Login();
            }
        });

        pp.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                fileChooser.getExtensionFilters().addAll(
                        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(this.controller.get_stage().getOwner());
                if (selectedFile != null) {
                    this.linkFile = selectedFile.getAbsolutePath();
                    pp.setOpacity(0.0);
                    try{
                        FileInputStream file = new FileInputStream(selectedFile.getAbsolutePath());
                        pp_file.setImage(new Image(file));
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                }
            }
        });


        signup_btn.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                String genrefinal = "";
                LocalDate value = naissance.getValue();
                if(login.getText().matches("^(.+)@(.+)$"))
                {
                    warning2.setVisible(false);
                    if(value != null && prenom.getText() != "" && nom.getText() != "" && ((String)genre.getValue() != "" || autres.getText() != "") && login.getText() != "" && mdp.getText() != "" && linkFile != "")
                    {
                        if((String)genre.getValue() != "" && autres.getText() == "")
                        {
                            genrefinal = (String)genre.getValue();
                        }
                        else if((String)genre.getValue() == "" && autres.getText() != "")
                        {
                            genrefinal = autres.getText();
                        }
                        if(!((String)genre.getValue() != "" && autres.getText() != ""))
                        {
                            
                            int used = this.controller.create_acct(prenom.getText().replace("'","''"), nom.getText().replace("'","''"), genrefinal.replace("'","''"), value.getYear(), value.getMonthValue(), value.getDayOfMonth(), login.getText().replace("'","''").toLowerCase(), mdp.getText(), admin.isSelected(), linkFile);
                            if(used == 1)
                            {
                                warning.setVisible(false);
                                this.mainApp.showGenre();
                                //this.mainApp.Login();
                            }
                            else if(used == -1)
                            {
                                warning.setVisible(true);
                            }
                        }
                    }
                }
                else
                {
                    warning2.setVisible(true);
                }
            }
        });

        genre.getItems().add("Homme");
        genre.getItems().add("Femme");


        
    }
    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
        root.requestFocus();
    }
}
