package com.intfinity.myapi.pollonico.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDataBase {

    private static String url = "jdbc:postgresql://localhost:5432/PollosNico";
    private static String user = "postgres";
    private static String password = "sasa";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null){
            connection = DriverManager.getConnection(url,user,password);
        }
        return connection;
    }
}
