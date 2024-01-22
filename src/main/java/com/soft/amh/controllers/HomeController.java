package com.soft.amh.controllers;

import com.soft.amh.service.CourseService;
import com.soft.amh.util.SharedBorderPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import org.fxmisc.richtext.InlineCssTextArea;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

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

        System.out.println(noteEditor.getText());
        System.out.println(noteEditor2.getText());
    }



    private TabPane tabPane;
    private ComboBox<Integer> fontSizeComboBox;
    private InlineCssTextArea noteEditor2;
    private InlineCssTextArea noteEditor;
    @FXML
    void onclickNoteBtn(MouseEvent event) {

        Font centuryBurma = Font.loadFont(getClass()
                .getResourceAsStream("/fonts/CenturyBurma.ttf"), 20);
        System.out.println(" font : "+centuryBurma.getFamily());
        tabPane = new TabPane();
        noteEditor = new InlineCssTextArea();
        noteEditor.setStyle("-fx-font-size: 25px;"+"-fx-font-family: '"+centuryBurma.getFamily()+"'");

        noteEditor.setWrapText(true);

        Tab tab1 = new Tab("Note 1");
        tab1.setContent(noteEditor);


        Image image = new Image(getClass().getResource("/img/MyProfile.jpeg").toString());

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);

        imageView.setOnMouseEntered(event1 -> {
            imageView.setStyle("-fx-cursor: hand;");
                }
        );

        imageView.setOnMouseExited(event1 -> {
                    imageView.setStyle("-fx-cursor: default;");
                }
        );

        noteEditor2 = new InlineCssTextArea();
        Tab tab2 = new Tab("Note 2");
        tab2.setContent(noteEditor2);

        noteEditor2.setStyle("-fx-font-size: 18px;"+"-fx-font-family: '"+centuryBurma.getFamily()+"'");
        noteEditor2.setWrapText(true);
        noteEditor2.appendText("\uD83D\uDCF7");
        noteEditor2.appendText("ma;io;ejiaf;ehfia;heif;haie;fhia;ehfi;aeh");

        noteEditor2.setParagraphGraphicFactory(line -> {
            // Check if this line contains a special marker that indicates an image
                return imageView; // Return the ImageView for the line containing the marke
        });


        fontSizeComboBox = createFontSizeComboBox();
        TilePane controlPane = new TilePane();
        controlPane.getChildren().add(fontSizeComboBox);


        tabPane.getTabs().addAll(tab1,tab2);

        borderPane.setTop(controlPane);
        borderPane.setCenter(tabPane);
    }

    private ComboBox<Integer> createFontSizeComboBox() {
        ComboBox<Integer> fontSizeComboBox = new ComboBox<>(FXCollections.observableArrayList(12, 14, 16, 18, 20, 25, 30));
        fontSizeComboBox.setValue(14); // Default font size
        fontSizeComboBox.setOnAction(event -> {
            int selectedFontSize = fontSizeComboBox.getValue();
            updateFontSize(getActiveTabContent());
        });
        return fontSizeComboBox;
    }

    private Node getActiveTabContent() {
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectedTab != null) {
            return selectedTab.getContent();
        }
        return null;
    }

    private void updateFontSize(Node content) {
        if (content instanceof InlineCssTextArea) {
            InlineCssTextArea textArea = (InlineCssTextArea) content;
            int selectedFontSize = fontSizeComboBox.getValue();
            String style = String.format("-fx-font-size: %dpx;-fx-font-family: 'Century Burma'", selectedFontSize);
            textArea.setStyle(style);
        }
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
