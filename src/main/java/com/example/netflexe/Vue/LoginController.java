package com.example.netflexe.Vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;




import com.example.netflexe.Controller.HelloApplication;

public class LoginController {
    private SceneController mainApp;
    private HelloApplication controller;

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView logo;
    @FXML
    private Button login_btn;
    @FXML
    private TextField login;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button create_account;
    @FXML
    private Button invite;
    @FXML
    private ImageView error;

    @FXML
    private void initialize() {
        root.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1  ) {
                root.requestFocus();
            }
        });

        mdp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ENTER)) {
                    if(controller.login_acct(login.getText(), mdp.getText()) == 1)
                    {
                        error.setVisible(false);
                        mainApp.showMain();
                    }
                    else
                    {
                        error.setVisible(true);
                    }
                }
            }
        });

        login_btn.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1) {
                if(login.getText() != "" && mdp.getText() != "")
                {
                    if(this.controller.login_acct(login.getText().toLowerCase(), mdp.getText()) == 1)
                    {
                        error.setVisible(false);
                        this.mainApp.showMain();
                    }
                    else
                    {
                        error.setVisible(true);
                    }
                }
            }
        });

        create_account.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                this.mainApp.SignUp();
            }
        });

        invite.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1)
            {
                this.mainApp.showMain();
            }
        });
    }
    public void setMainApp(SceneController mainApp, HelloApplication controller) {
        this.mainApp = mainApp;
        this.controller = controller;
        root.requestFocus();
    }
}