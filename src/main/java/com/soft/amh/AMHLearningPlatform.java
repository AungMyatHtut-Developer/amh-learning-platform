package com.soft.amh;


import com.soft.amh.util.FXMLLoaderHelper;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AMHLearningPlatform extends Application {


    private static Stage primaryStage;

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    private FXMLLoaderHelper fxmlLoaderHelper;

    @Override
    public void start(Stage stage) throws Exception {


        fxmlLoaderHelper = new FXMLLoaderHelper();
        Node node = fxmlLoaderHelper.getView("/views/login-view");

        Scene scene = new Scene((Parent) node);


        primaryStage = stage;

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo.png")));
        primaryStage.setTitle("AMH Learning Platform");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
