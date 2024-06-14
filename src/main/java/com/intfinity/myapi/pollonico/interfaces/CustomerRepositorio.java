package com.intfinity.myapi.pollonico.interfaces;

import com.intfinity.myapi.pollonico.Help.Help;
import com.intfinity.myapi.pollonico.Models.Customer;
import com.intfinity.myapi.pollonico.Util.ConexionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositorio implements RepositorioGenerico <Customer>{

    private Connection getConnection() throws SQLException {
        return ConexionDataBase.getInstance();
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery("select * from customer")) {

            while (resultSet.next()){
                Customer customer = getCustomer(resultSet);
                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

    @Override
    public void searchById(Integer id) {
        Customer customer = new Customer();
        try (PreparedStatement ps = getConnection().prepareStatement("select * from customer where id=?")) {
            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    customer = getCustomer(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (customer.hasNullFields()){
            Help.displayMessage("Gasto no encontrado"
                    ,"verifica que se encunetre bien escrito",
                    "El gasto con el id" + id+ " no existe");
        }else {
            System.out.println(rObjectId(customer));
        }


    }

    @Override
    public Customer rObjectId(Customer customer) {
        return customer;
    }

    @Override
    public void save(Customer customer) {

        String sql = "";

        if (customer.getId() != null && customer.getId() > 0) {
            sql = "UPDATE customer SET nombre=?, telefono=?, direccion=? WHERE id=?";
        }else {
            sql = "INSERT INTO customer(nombre, telefono, direccion) VALUES (?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            if (customer.getId() != null && customer.getId() > 0){
                stmt.setInt(4, customer.getId());
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getPhone());
                stmt.setString(3, customer.getAddress());
            }else {
                stmt.setString(1, customer.getName());
                stmt.setString(2, customer.getPhone());
                stmt.setString(3, customer.getAddress());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remove(Integer id) {

        try (PreparedStatement pr = getConnection().prepareStatement("DELETE FROM customer WHERE id = ?")) {
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Customer getCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("nombre"));
        customer.setPhone(resultSet.getString("telefono"));
        customer.setAddress(resultSet.getString("direccion"));
        return customer;
    }
}
