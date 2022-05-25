module com.example.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.example.javafxproject to javafx.fxml;
    exports com.example.javafxproject;
    exports com.example.javafxproject.controller;
    opens com.example.javafxproject.controller to javafx.fxml;
    exports com.example.javafxproject.model;
    opens com.example.javafxproject.model to javafx.fxml;
}