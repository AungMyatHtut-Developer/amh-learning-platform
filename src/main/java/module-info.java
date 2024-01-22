module org.amh.learningplatform.amhlearningplatform {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires org.fxmisc.richtext;
    requires org.controlsfx.controls;
    requires org.apache.logging.log4j;

    opens com.soft.amh to javafx.fxml;
    opens com.soft.amh.controllers to javafx.fxml;

    exports com.soft.amh;
    exports com.soft.amh.controllers;
}