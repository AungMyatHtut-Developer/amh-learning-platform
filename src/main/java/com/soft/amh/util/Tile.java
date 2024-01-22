package com.soft.amh.util;

import com.soft.amh.service.CourseService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tile extends VBox {

    private int courseId;
    private ImageView imageView;
    private Text descriptionText;

    private int videoId;


    public Tile(int courseId,ImageView imageView, String description, int textWrapping, int videoId) {
        this.imageView = imageView;
        this.descriptionText = new Text(description);
        this.courseId = courseId;
        this.videoId = videoId;

        setAlignment(Pos.CENTER);
        setMaxWidth(250);
        setSpacing(5);
        setPadding(new Insets(3));

        descriptionText.setWrappingWidth(textWrapping);

        getChildren().addAll(imageView, descriptionText);

        setOnMouseEntered(event -> {
            handleMouseEntered();
        });

        setOnMouseExited(event -> {
            handleMouseExited();
        });

        setOnMouseClicked(event -> {

            System.out.println(this.videoId);

            if (this.videoId != 0 ) {

                System.out.println(this.videoId);
                FXMLLoaderHelper fxmlLoaderHelper = new FXMLLoaderHelper();
                VBox vBox = (VBox) fxmlLoaderHelper.getView("/views/video-player");

                AnchorPane anchorPane = new AnchorPane();
                vBox.prefHeightProperty().bind(anchorPane.heightProperty());

                AnchorPane.setTopAnchor(vBox, 20.0);
                AnchorPane.setBottomAnchor(vBox, 10.0);
                AnchorPane.setLeftAnchor(vBox, 10.0);
                AnchorPane.setRightAnchor(vBox, 10.0);


                anchorPane.getChildren().add(vBox);

//                SharedBorderPane.getSharedBorderPane().setCenter(anchorPane);
//                SharedBorderPane.getSharedBorderPane().getLeft().toFront();


                Stage stage = new Stage();

                stage.setScene(new Scene(anchorPane));
                stage.setTitle("AMH Platform Video Player");
                stage.show();


            }else{
                SharedBorderPane.getSharedBorderPane().setCenter(new CourseService().getCourseLessonView());
            }


        });
    }



    private void handleMouseEntered(){
        setCursor(Cursor.HAND);
        setStyle("-fx-background-color: #ffffff;");
    }

    private void handleMouseExited() {
        setCursor(Cursor.DEFAULT);
        setStyle(null);
    }

}
