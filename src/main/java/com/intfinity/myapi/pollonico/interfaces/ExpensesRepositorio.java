package com.intfinity.myapi.pollonico.interfaces;

import com.intfinity.myapi.pollonico.Models.Customer;
import com.intfinity.myapi.pollonico.Models.Expenses;
import com.intfinity.myapi.pollonico.Util.ConexionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpensesRepositorio implements RepositorioGenerico<Expenses>{

    private Connection getConnection() throws SQLException {
        return ConexionDataBase.getInstance();
    }
    @Override
    public List<Expenses> findAll() {
        List<Expenses> exp = new ArrayList<>();
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery("select * from expenses")) {

            while (resultSet.next()){
                Expenses expenses = getExpenses(resultSet);
                exp.add(expenses);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exp;
    }

    @Override
    public Expenses searchById(Integer id) {
        Expenses expense = new Expenses();
        try (PreparedStatement ps = getConnection().prepareStatement("select * from expenses where id=?")) {
            ps.setInt(1,id);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    expense = getExpenses(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (expense.hasNullFields()){
            System.out.println("El elemento buscada no existe");
        }

        else {
            return expense;
        }

         return expense;
    }

    @Override
    public void save(Expenses expenses) {
        String sql = "";

        if (expenses.getId() != null && expenses.getId() > 0) {
            sql = "UPDATE expenses SET descripcion=?, fecha_gasto=?, total=? WHERE id=?";
        }else {
            sql = "INSERT INTO customer(descripcion, fecha_gasto, total) VALUES (?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            if (expenses.getId() != null && expenses.getId() > 0){
                stmt.setInt(4, expenses.getId());
                stmt.setString(1, expenses.getDescripcion());
                stmt.setDate(2, expenses.getDate());
                stmt.setDouble(3, expenses.getTotal());
            }else {
                stmt.setString(1, expenses.getDescripcion());
                stmt.setDate(2, expenses.getDate());
                stmt.setDouble(3, expenses.getTotal());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Integer id) {
        try (PreparedStatement pr = getConnection().prepareStatement("DELETE FROM expenses WHERE id = ?")) {
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Expenses getExpenses(ResultSet resultSet) throws SQLException {
        Expenses expenses = new Expenses();
        expenses.setId(resultSet.getInt("id"));
        expenses.setDescripcion(resultSet.getString("descripcion"));
        expenses.setDate(resultSet.getDate("fecha_gasto"));
        expenses.setTotal(resultSet.getDouble("total"));
        return expenses;
    }
}
