package com.example.javafxproject.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GeneralScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button openFillingTable;

    @FXML
    private Button openTableCreator;

    @FXML
    void onOpenFillingTable(ActionEvent event) {

    }

    @FXML
    void onOpenTableCreator(ActionEvent event) {

    }

    @FXML
    void initialize() {
        openTableCreator.setOnAction(actionEvent -> {
            openTableCreator.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("/com/example/javafxproject/createTableScreen.fxml"));
            loader.setLocation(getClass().getResource("/com/example/javafxproject/createTableScreenGreyTheme.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
