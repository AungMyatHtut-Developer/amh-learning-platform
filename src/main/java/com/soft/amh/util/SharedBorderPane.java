package com.soft.amh.util;

import javafx.scene.layout.BorderPane;

public class SharedBorderPane {

    private static BorderPane sharedBorderPane;

    public static BorderPane getSharedBorderPane() {
        return sharedBorderPane;
    }

    public static void setSharedBorderPane(BorderPane borderPane) {
        sharedBorderPane = borderPane;
    }
}
