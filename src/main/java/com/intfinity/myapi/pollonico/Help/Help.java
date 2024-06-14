package com.intfinity.myapi.pollonico.Help;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class Help {

    public static void displayWarning(String title, String headerText, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    public static void displayError(String title, String headerText, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }

    public static void displayMessage(String title, String headerText, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(content);
            alert.showAndWait();
        });
    }
}
