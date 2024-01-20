package com.soft.amh.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Label accountBtn;

    @FXML
    private Label announcementBtn;

    @FXML
    private Label articleBtn;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label dateTimeLabel;

    @FXML
    private Label helpBtn;

    @FXML
    private Label homeBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private Label messageBtn;

    @FXML
    private Label minimizeBtn;

    @FXML
    private Label noteBtn;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label tutorialBtn;

    @FXML
    private AnchorPane sideMenuPanel;

    @FXML
    private AnchorPane centerPane;

    @FXML
    void onclickAccountBtn(MouseEvent event) {

    }

    @FXML
    void onclickAnnouncementBtn(MouseEvent event) {

    }

    @FXML
    void onclickArticleBtn(MouseEvent event) {

    }

    @FXML
    void onclickHelpBtn(MouseEvent event) {

    }

    @FXML
    void onclickHomeBtn(MouseEvent event) {

    }

    @FXML
    void onclickLogoutBtn(MouseEvent event) {

    }

    @FXML
    void onclickMessageBtn(MouseEvent event) {

    }

    @FXML
    void onclickMinimizeBtn(MouseEvent event) {


        if (minimizeBtn.getText().contentEquals("⭕")){
            for(int i=0; i<2; i++){
                Insets currentInset = BorderPane.getMargin(centerPane);

                double newLeftMargin = 0;
                BorderPane.setMargin(centerPane, new Insets(currentInset.getTop(),newLeftMargin,currentInset.getBottom(),currentInset.getRight()));
            }



            minimizeBtn.setPrefWidth(165);
            minimizeBtn.setText("❌");
        }else{
            for(int i=0; i<2; i++){
                Insets currentInset = BorderPane.getMargin(centerPane);

                if(currentInset == null){
                    currentInset = new Insets(0);
                }


                double newLeftMargin = 0 - 132.0;
                BorderPane.setMargin(centerPane, new Insets(currentInset.getTop(),newLeftMargin,currentInset.getBottom(),currentInset.getRight()));
            }

            minimizeBtn.setPrefWidth(35);
            minimizeBtn.setText("⭕");


        }


        borderPane.setCenter(centerPane);


    }

    @FXML
    void onclickNoteBtn(MouseEvent event) {

    }

    @FXML
    void onclickTutorialBtn(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupClock();
    }

    private void setupClock() {
        // Update the clock every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateClock()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date());
        dateTimeLabel.setText(formattedDate);
    }
}
