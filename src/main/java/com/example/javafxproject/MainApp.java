package com.example.javafxproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Objects;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/javafxproject/generalScreen.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Areskiss");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}