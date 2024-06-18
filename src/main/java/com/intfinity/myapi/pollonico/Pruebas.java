package com.intfinity.myapi.pollonico;

import com.intfinity.myapi.pollonico.Models.Customer;
import com.intfinity.myapi.pollonico.Models.Expenses;
import com.intfinity.myapi.pollonico.Models.Sales;
import com.intfinity.myapi.pollonico.Util.ConexionDataBase;
import com.intfinity.myapi.pollonico.interfaces.CustomerRepositorio;
import com.intfinity.myapi.pollonico.interfaces.ExpensesRepositorio;
import com.intfinity.myapi.pollonico.interfaces.RepositorioGenerico;
import com.intfinity.myapi.pollonico.interfaces.SalesRepositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Pruebas {
    public static void main(String[] args) throws SQLException {

        try(Connection conn = ConexionDataBase.getInstance()){
            RepositorioGenerico<Sales> repoSales = new SalesRepositorio();
            RepositorioGenerico<Expenses> repoExpenses = new ExpensesRepositorio();
            RepositorioGenerico<Customer> repoCustomer = new CustomerRepositorio();

        }

    }
}
