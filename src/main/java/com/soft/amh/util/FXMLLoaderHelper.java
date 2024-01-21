package com.soft.amh.util;

import com.soft.amh.AMHLearningPlatform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;



public class FXMLLoaderHelper {


    public Node getView(String viewName){
        FXMLLoader loader = new FXMLLoader(AMHLearningPlatform.class.getResource(viewName+".fxml"));

        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error : FXML could file couldn't load: "+viewName);
        }

        return null;

    }
}
