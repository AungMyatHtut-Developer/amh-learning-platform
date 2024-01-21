package com.soft.amh.controllers;

import com.soft.amh.service.CourseService;
import com.soft.amh.util.SharedBorderPane;
import com.soft.amh.util.Tile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private CourseService courseService;

    @FXML
    private Label accountBtn;

    @FXML
    private Label announcementBtn;

    @FXML
    private Label articleBtn;

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane centerPane;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private Label helpBtn;

    @FXML
    private Label homeBtn;

    @FXML
    private FlowPane iconFlowPane;

    @FXML
    private ImageView iconImage;

    @FXML
    private Label internetBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label messageBtn;

    @FXML
    private Label minimizeBtn;

    @FXML
    private Label noteBtn;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label showMenuBtn;

    @FXML
    private AnchorPane sideMenuPanel;

    @FXML
    private Label tutorialBtn;

    @FXML
    void onclickAccountBtn(MouseEvent event) {

    }

    @FXML
    void onclickAnnouncementBtn(MouseEvent event) {

    }

    @FXML
    void onclickHomeBtn(MouseEvent event) {

    }

    @FXML
    void onclickInternetBtn(MouseEvent event) {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        getProgressBarForWebView(webEngine);
        webEngine.load("https://www.youtube.com");

        borderPane.setCenter(webView);
    }


    @FXML
    void onclickLogoutBtn(MouseEvent event) {

    }

    @FXML
    void onclickMessageBtn(MouseEvent event) {

    }



    @FXML
    void onclickNoteBtn(MouseEvent event) {

    }


    @FXML
    void onclickArticleBtn(MouseEvent event) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        getProgressBarForWebView(webEngine);
        webEngine.load("https://www.newsinlevels.com/");

        borderPane.setCenter(webView);
    }

    @FXML
    void onclickHelpBtn(MouseEvent event) {
        TextArea textArea = new TextArea();

        Font centuryBurma = Font.loadFont(getClass().getResourceAsStream("/fonts/CenturyBurma.ttf"), 20);

        textArea.setFont(centuryBurma);
        textArea.setPrefWidth(500);
        textArea.setPrefHeight(500);

        borderPane.setCenter(textArea);
    }

    @FXML
    void onclickMinimizeBtn(MouseEvent event) {

    borderPane.getChildren().remove(sideMenuPanel);
    addHideMenuButton();
    }


    @FXML
    void onclickTutorialBtn(MouseEvent event) {
        borderPane.setCenter(courseService.getCourseView());
    }


    @FXML
    void onclickShowMenuBtn(MouseEvent event){
        borderPane.setLeft(sideMenuPanel);
        removeHideMenuButton();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupClock();
        removeHideMenuButton();

        progressBar.setVisible(false);
        profileImage.setImage(new Image(getClass().getResourceAsStream("/img/MyProfile.jpeg")));

        SharedBorderPane.setSharedBorderPane(borderPane);
        courseService  = new CourseService();
    }

    private void setupClock() {
        // Update the clock every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyy hh:mm:ss aaa");
        String formattedDate = dateFormat.format(new Date());
        dateTimeLabel.setText(formattedDate);
    }


    private void removeHideMenuButton(){
        iconFlowPane.getChildren().removeAll(showMenuBtn, iconImage);
        iconFlowPane.getChildren().add(iconImage);
    }

    private void addHideMenuButton(){
        iconFlowPane.getChildren().removeAll(showMenuBtn, iconImage);

        iconFlowPane.getChildren().add(0, showMenuBtn);
        iconFlowPane.getChildren().add(1, iconImage);
    }

    private void getProgressBarForWebView(WebEngine webEngine){

        progressBar.progressProperty().bind(webEngine.getLoadWorker().progressProperty());

        webEngine.getLoadWorker().stateProperty().addListener((observable,oldValue,newValue) -> {
            if(newValue == Worker.State.SUCCEEDED){
                progressBar.setVisible(false);
            }else{
                progressBar.setVisible(true);
            }
        });
    }

}
