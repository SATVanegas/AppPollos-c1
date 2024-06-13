package com.intfinity.myapi.pollonico;

import com.intfinity.myapi.pollonico.Models.Customer;
import com.intfinity.myapi.pollonico.Models.Sales;
import com.intfinity.myapi.pollonico.Util.ConexionDataBase;
import com.intfinity.myapi.pollonico.interfaces.CustomerRepositorio;
import com.intfinity.myapi.pollonico.interfaces.RepositorioGenerico;
import com.intfinity.myapi.pollonico.interfaces.SalesRepositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Pruebas {
    public static void main(String[] args) throws SQLException {

        try(Connection conn = ConexionDataBase.getInstance()){
            RepositorioGenerico<Sales> reposales = new SalesRepositorio();
            reposales.findAll().forEach(System.out::println);
            Customer cs = new Customer(3);
            Sales sl = new Sales(1,cs,90000.92,10,true, Date.valueOf("2024-06-13"));
            reposales.save(sl);
            reposales.findAll().forEach(System.out::println);




        }

    }
}
