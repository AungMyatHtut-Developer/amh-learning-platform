package com.soft.amh.service;

import com.soft.amh.util.SharedBorderPane;
import com.soft.amh.util.Tile;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class CourseService {

    public ScrollPane getCourseLessonView(){
        AnchorPane root = new AnchorPane();

        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(3);
        tilePane.setHgap(20);
        tilePane.setVgap(20);
        tilePane.setPadding(new Insets(10));

        // Add video thumbnails to the TilePane

        for (int i = 1; i <= 20; i++) {

                Tile tile = createVideoTile(String.valueOf(
                                "/img/javaSE.png"),
                        200,
                        120,
                        "part "+i ,i,i);

                tilePane.getChildren().add(tile);
        }


        // Bind TilePane size to AnchorPane size
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(root.heightProperty());

        Button backButton = new Button("Back");
        backButton.setOnAction(event ->{
            SharedBorderPane.getSharedBorderPane().setCenter(getCourseView());
        });


        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);




        // Create a ScrollPane to handle scrolling if needed
        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return scrollPane;
    }

    public ScrollPane getCourseView(){
        AnchorPane root = new AnchorPane();

        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(3);
        tilePane.setHgap(20);
        tilePane.setVgap(20);
        tilePane.setPadding(new Insets(10));

        // Add video thumbnails to the TilePane

        for (int i = 1; i <= 6; i++) {
            Tile tile = createVideoTile(String.valueOf("/img/course-"+i+".png"),
                            330,230,
                    "Course Title: JavaSE Fundamentals \uD83D\uDE80\n" +
                            "\n" +
                            "Description:\n" +
                            "Dive into the exciting world of Java Standard Edition (JavaSE) " +
                            "with our comprehensive course! \uD83C\uDF93 Whether you're a " +
                            "coding novice or looking to level up your programming skills, " +
                            "this course covers key concepts such as variables, " +
                            "control flow, and object-oriented programming. " +
                            "\uD83D\uDCBB Get ready for hands-on experience through practical " +
                            "exercises and real-world projects, equipping you with the knowledge " +
                            "to build robust Java applications. \uD83D\uDEE0\uFE0F Join us on a " +
                            "journey to master the fundamentals of JavaSE and unlock the potential " +
                            "to create versatile and powerful software solutions. \uD83C\uDF10",
                    i,0);
            tilePane.getChildren().add(tile);
        }


        // Bind TilePane size to AnchorPane size
        tilePane.prefWidthProperty().bind(root.widthProperty());
        tilePane.prefHeightProperty().bind(root.heightProperty());

        // Create a ScrollPane to handle scrolling if needed
        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        return scrollPane;
    }

    private ImageView createVideoThumbnail(String imageUrl, int imageWidth, int imageHeight) {
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(imageWidth);
        imageView.setFitHeight(imageHeight);

        return imageView;
    }

    private Tile createVideoTile(String imageUrl,int imageWidth, int imageHeight, String description, int courseId, int videoId) {
        ImageView imageView = createVideoThumbnail(imageUrl, imageWidth, imageHeight);

        // Create a Tile (custom container for ImageView and Label)
        Tile tile = new Tile(courseId,imageView, description, imageWidth, videoId);

        return tile;
    }

}
