package com.example.netflexe.Vue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

import com.example.netflexe.Controller.HelloApplication;

public class SignUpController {
    private SceneController mainApp;
    private HelloApplication controller;
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
    private TextField login;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button signup_btn;
    @FXML
    private ImageView return_arrow;

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

        signup_btn.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                String genrefinal = "";
                LocalDate value = naissance.getValue();
                if(value != null && prenom.getText() != "" && nom.getText() != "" && ((String)genre.getValue() != "" || autres.getText() != "") && login.getText() != "" && mdp.getText() != "")
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
                        
                        int used = this.controller.create_acct(prenom.getText(), nom.getText(), genrefinal, value.getYear(), value.getMonthValue(), value.getDayOfMonth(), login.getText(), mdp.getText(), admin.isSelected());
                        if(used == 1)
                        {
                            warning.setVisible(false);
                            this.mainApp.Login();
                        }
                        else if(used == -1)
                        {
                            warning.setVisible(true);
                        }
                    }
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
