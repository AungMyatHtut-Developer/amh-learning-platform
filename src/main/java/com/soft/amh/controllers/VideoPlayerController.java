package com.soft.amh.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;

public class VideoPlayerController implements Initializable {

    @FXML
    private Button buttonPPR;

    @FXML
    private HBox hboxControls;

    @FXML
    private HBox hboxVolume;

    @FXML
    private Label labelCurrentTIme;

    @FXML
    private Label labelFullScreen;

    @FXML
    private Label labelSpeed;

    @FXML
    private Label labelTotalTime;

    @FXML
    private Label labelVolume;

    @FXML
    private MediaView mvVideo;
    private MediaPlayer mpVideo;
    private Media mediaVideo;

    private boolean atEndOfVideo = false;
    private boolean isPlaying = true;
    private boolean isMuted = true;

    @FXML
    private Slider sliderTime;

    @FXML
    private Slider sliderVolume;

    @FXML
    private VBox vBoxParent;

    private String play="▶";
    private String stop="⏹";
    private String restart = "\uD83D\uDD04";
    private String volume = "\uD83D\uDD08";
    private String fullScreen = "⛶";
    private String smallScreen = "\uD83D\uDD32";
    private String mute = "\uD83D\uDD07";
    private String exit = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


//        mediaVideo = new Media(getClass().getResource("/video/lesson3.mp4").toString());


//
        mediaVideo = new Media("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");

//        mediaVideo = new Media("https://drive.google.com/file/d/1QSOwE_1BlHBnmZ6mF59ftLMdqpcaYVJI/view?usp=drive_link");
        mpVideo = new MediaPlayer(mediaVideo);
        mvVideo.setMediaPlayer(mpVideo);

        buttonPPR.setText(play);
        labelVolume.setText(volume);
        labelSpeed.setText("1X");
        labelFullScreen.setText(fullScreen);


        buttonPPR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Button buttonPlay = (Button) actionEvent.getSource();

                if(atEndOfVideo){
                    sliderTime.setValue(0);
                    atEndOfVideo = false;
                    isPlaying = false;
                }

                if(isPlaying){
                    buttonPlay.setText(play);
                    mpVideo.pause();
                    isPlaying = false;
                }else{
                    buttonPlay.setText(stop);
                    mpVideo.play();
                    isPlaying = true;
                }
            }
        });



        hboxVolume.getChildren().remove(sliderVolume);
        mpVideo.volumeProperty().bindBidirectional(sliderVolume.valueProperty());
        bindCurrentTimeLabel();

        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mpVideo.setVolume(sliderVolume.getValue());

                if (mpVideo.getVolume() != 0.0) {
                    labelVolume.setText(volume);
                    isMuted = false;
                }else{
                    labelVolume.setText(mute);
                    isMuted = true;
                }
            }
        });

        labelSpeed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(labelSpeed.getText().equals("1X")){
                    labelSpeed.setText("2X");
                    mpVideo.setRate(2.0);
                }else{
                    labelSpeed.setText("1X");
                    mpVideo.setRate(1.0);
                }

            }
        });

            labelVolume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(isMuted){
                    labelVolume.setText(volume);
                    sliderVolume.setValue(0.2);
                    isMuted = false;
                }else{
                    labelVolume.setText(mute);
                    sliderVolume.setValue(0);
                    isMuted = true;
                }
            }
        });

        labelVolume.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(hboxControls.lookup("#sliderVolume") == null){
                    hboxVolume.getChildren().add(sliderVolume);
                    sliderVolume.setValue(mpVideo.getVolume());
                }
            }
        });

        hboxVolume.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                hboxVolume.getChildren().remove(sliderVolume);
            }
        });


        vBoxParent.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene oldScene, Scene newScene) {
                if(oldScene == null && newScene != null){
                    mvVideo.fitHeightProperty().bind(newScene.heightProperty().subtract(hboxControls.heightProperty().add(100)));
                }
            }
        });

        labelFullScreen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Label label = (Label) event.getSource();
                Stage stage = (Stage) label.getScene().getWindow();
                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                    labelFullScreen.setText(fullScreen);
                }else{
                    stage.setFullScreen(true);
                    labelFullScreen.setText(smallScreen);
                }
                stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if(keyEvent.getCode() == KeyCode.ESCAPE){
                            labelFullScreen.setText(fullScreen);
                        }
                    }
                });
            }
        });


        mpVideo.totalDurationProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
                    sliderTime.setMax(newDuration.toSeconds());
                    labelTotalTime.setText(getTime(newDuration));

            }
        });

        sliderTime.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
                if (!isChanging) {
                    mpVideo.seek(Duration.seconds(sliderTime.getValue()));
                }
            }
        });

        sliderTime.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                double currentTime = mpVideo.getCurrentTime().toSeconds();
                if(Math.abs(currentTime - newValue.doubleValue()) > 0.5){
                    mpVideo.seek(Duration.seconds(newValue.doubleValue()));
                }
                labelEndMatchingVideo(labelCurrentTIme.getText(), labelTotalTime.getText());

            }
        });

        mpVideo.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
                if(!sliderTime.isValueChanging()){
                    sliderTime.setValue(newTime.toSeconds());
                }

                labelEndMatchingVideo(labelCurrentTIme.getText(), labelTotalTime.getText());
            }
        });

        mpVideo.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                buttonPPR.setText(restart);
                atEndOfVideo = true;

                if(!labelCurrentTIme.textProperty().equals(labelTotalTime.textProperty())){
                    labelCurrentTIme.textProperty().unbind();
                    labelCurrentTIme.setText(getTime(mpVideo.getTotalDuration()) + " / ");
                }
            }
        });
    }

    private void labelEndMatchingVideo(String labelTime, String labelTotalTime) {
        for (int i = 0; i < labelTotalTime.length(); i++) {

            if(labelTime.charAt(i) != labelTotalTime.charAt(i)){
                atEndOfVideo = false;
                if(isPlaying) buttonPPR.setText(stop);
                else buttonPPR.setText(play);
                    break;
            }else{
                atEndOfVideo = true;
                buttonPPR.setText(restart);
            }
        }

    }

    public void bindCurrentTimeLabel(){
        labelCurrentTIme.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(mpVideo.getCurrentTime()) + " / ";
            }
        }, mpVideo.currentTimeProperty()));
    }

    public String getTime(Duration time){
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if(seconds > 59) seconds = seconds %60;
        if(minutes > 59) minutes = minutes % 60;
        if(hours > 59) hours = hours % 60;

        if(hours > 0) return String.format("%d:%02d:%02d", hours,minutes,seconds);
        else return String.format("%02d:%02d",minutes,seconds);


    }
}
