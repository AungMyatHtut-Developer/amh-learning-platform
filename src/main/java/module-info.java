module org.amh.learningplatform.amhlearningplatform {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.apache.logging.log4j;

//    opens org.amh.learningplatform.amhlearningplatform to javafx.fxml;
    opens com.soft.amh to javafx.fxml;
    opens com.soft.amh.controllers to javafx.fxml;

    exports com.soft.amh;
    exports com.soft.amh.controllers;
}