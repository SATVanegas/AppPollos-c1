package com.intfinity.myapi.pollonico.Controllers;

import com.intfinity.myapi.pollonico.Help.Help;
import com.intfinity.myapi.pollonico.Models.Customer;
import com.intfinity.myapi.pollonico.Util.ConexionDataBase;
import com.intfinity.myapi.pollonico.interfaces.CustomerRepositorio;
import com.intfinity.myapi.pollonico.interfaces.RepositorioGenerico;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class PrincipalController extends Application {

    List<Customer> customers;
    private int actualizacion = 0;
    private final RepositorioGenerico<Customer> repoCustomer = new CustomerRepositorio();


    @FXML
    public TextField txtNameCus;
    @FXML
    public TextField tfPhoneCus;
    @FXML
    public TextField tfAddressCus;
    @FXML
    private TextField txtSearchUpdateCus;
    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnAddCus;
    @FXML
    private Button btnSearchUpdateCus;
    @FXML
    private TextField txtNameUpdateCus;
    @FXML
    private  TextField txtPhoneUpdateCus;
    @FXML
    private TextField txtAddressUpdateCus;
    @FXML
    private TextField txtUpdateIdCus;
    @FXML
    private Button btnSales;
    @FXML
    private Button btnExpenses;
    @FXML
    private VBox VBPrincipal;
    @FXML
    private TabPane TabPaneCus;
    @FXML
    private Tab seeAllCustomer;
    @FXML
    private TabPane TabPaneSales;
    @FXML
    private TabPane TabPaneExpenses;

    @FXML
    private TableView<Customer> Allcustomers;

    @FXML
    private TableColumn<Customer, Integer> Cid;

    @FXML
    private TableColumn<Customer, String> Cname;

    @FXML
    private TableColumn<Customer, String> Caddress;

    @FXML
    private TableColumn<Customer, String> Cphone;
    @FXML
    private Button UpdateCus;

    public static Customer cs = null;


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
    private void searchCustomer(){
        repoCustomer.searchById(Integer.valueOf(txtSearchUpdateCus.getText()));
        if(cs.getId() > 0){
            txtPhoneUpdateCus.setText(cs.getPhone());
            txtAddressUpdateCus.setText(cs.getAddress());
            txtNameUpdateCus.setText(cs.getName());
            txtUpdateIdCus.setText(String.valueOf(cs.getId()));
        }

    }

    @FXML
    private void updateCustomer(){
        if (txtNameUpdateCus.getText().equals(cs.getName())
                || txtPhoneUpdateCus.getText().equals(cs.getPhone())
                || txtAddressUpdateCus.getText().equals(cs.getAddress())){
            Help.displayWarning("No a realizado ningun cambio","Porfavor realice cambios para actualizar","tonto");
        }else{
            Customer customer = new Customer(txtNameUpdateCus.getText(),txtPhoneUpdateCus.getText(),txtAddressUpdateCus.getText());
            customer.setId(cs.getId());
            repoCustomer.save(customer);
        }

    }

    @FXML
    private void AddCustomer() throws SQLException {

        Customer cs = new Customer();
        cs.setName(txtNameCus.getText());
        cs.setAddress(tfAddressCus.getText());
        cs.setPhone(tfPhoneCus.getText());
        try(Connection conn = ConexionDataBase.getInstance()){
            RepositorioGenerico<Customer> repoCustomer = new CustomerRepositorio();
            repoCustomer.save(cs);
            txtNameCus.clear();
            tfPhoneCus.clear();
            tfAddressCus.clear();
        }
    }

    @FXML
    public void initialize() {


        Cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        Cname.setCellValueFactory(new PropertyValueFactory<>("name"));
        Caddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        Cphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        VBPrincipal.setTranslateX(-230);
        txtPhoneUpdateCus.setText("");
        txtAddressUpdateCus.setText("");
        txtNameCus.setText("");
        txtUpdateIdCus.setText("");

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
    private void loadCustomers() {
        // Obtener la lista de clientes
        List<Customer> customers = repoCustomer.findAll();

        // Convertir la lista a un ObservableList
        ObservableList<Customer> observableCustomerList = FXCollections.observableArrayList(customers);

        // Establecer los datos en la tabla
        Allcustomers.setItems(observableCustomerList);
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