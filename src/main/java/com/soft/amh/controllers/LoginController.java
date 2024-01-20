package com.soft.amh.controllers;

import com.soft.amh.AMHLearningPlatform;
import com.soft.amh.util.FXMLLoaderHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    void onclickLoginBtn(ActionEvent event) {

        FXMLLoaderHelper fxmlLoaderHelper = new FXMLLoaderHelper();
        Node node = fxmlLoaderHelper.getView("/views/home");

        Stage stage = new AMHLearningPlatform().getPrimaryStage();

        stage.setScene(new Scene((Parent) node));
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setResizable(true);

    }

}
