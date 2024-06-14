package com.intfinity.myapi.pollonico.Controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController extends Application {

    @FXML private Button btnEnter;

    @FXML
    private void initialize() {
        btnEnter.setOnAction(event -> openSecondView());
    }

    public void openSecondView() {
        try {
            Stage stage = (Stage) btnEnter.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/intfinity/myapi/pollonico/Principal.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Principal");
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/intfinity/myapi/pollonico/hello-view.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Welcome");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}