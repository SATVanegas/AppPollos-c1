package com.intfinity.myapi.pollonico.Controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private TabPane TabPaneCus;
    @FXML
    private TabPane TabPaneSales;
    @FXML
    private TabPane TabPaneExpenses;





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

    @FXML
    private void ActivarPaneCustomer() {
        ControladorDeTabs(TabType.CUSTOMER);
    }

    @FXML
    private void ActivarPaneSales() {
        ControladorDeTabs(TabType.SALES);
    }

    @FXML
    private void ActivarPaneExpenses() {
        ControladorDeTabs(TabType.EXPENSES);
    }

    private void ControladorDeTabs(TabType tab) {
        setTabVisibility(tab == TabType.CUSTOMER, tab == TabType.SALES, tab == TabType.EXPENSES);
    }

    private void setTabVisibility(boolean showCustomer, boolean showSales, boolean showExpenses) {
        TabPaneCus.setVisible(showCustomer);
        TabPaneSales.setVisible(showSales);
        TabPaneExpenses.setVisible(showExpenses);
    }
}

