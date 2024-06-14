package com.intfinity.myapi.pollonico.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PrincipalController extends Application {

    @FXML
    private Button btnCustomer;
    @FXML
    private Button btnSales;
    @FXML
    private Button btnExpenses;
    @FXML
    private VBox VBPrincipal;
    @FXML
    private ImageView image;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/intfinity/myapi/pollonico/Principal.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Principal");
            primaryStage.show();

            image.setImage(new Image("../resources/com/intfinity/myapi/Images/rg.jpg"));

            // Bind layoutX and layoutY properties to center the ImageView
            image.layoutXProperty().bind(scene.widthProperty().subtract(image.fitWidthProperty()).divide(2));
            image.layoutYProperty().bind(scene.heightProperty().subtract(image.fitHeightProperty()).divide(2));
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {

        VBPrincipal.setTranslateX(-230);

        // Agregar evento para cuando el mouse entra en el botón
        btnCustomer.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
            btnCustomer.getStyleClass().add("btnsVbox-hover");
        });

        // Agregar evento para cuando el mouse sale del botón
        btnCustomer.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            btnCustomer.getStyleClass().remove("btnsVbox-hover");
        });

        btnSales.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
            btnSales.getStyleClass().add("btnsVbox-hover");
        });

        // Agregar evento para cuando el mouse sale del botón
        btnSales.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            btnSales.getStyleClass().remove("btnsVbox-hover");
        });

        btnExpenses.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
            btnExpenses.getStyleClass().add("btnsVbox-hover");
        });

        // Agregar evento para cuando el mouse sale del botón
        btnExpenses.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            btnExpenses.getStyleClass().remove("btnsVbox-hover");
        });

        VBPrincipal.addEventFilter(MouseEvent.MOUSE_MOVED, event -> {
            if (event.getSceneX() <= 100) {  // Si el mouse está muy cerca del borde izquierdo
                VBPrincipal.setTranslateX(0);
            } else if (event.getSceneX() > 200) {  // Si el mouse está lejos del menú
                VBPrincipal.setTranslateX(-230);
            }
        });

    }
}
