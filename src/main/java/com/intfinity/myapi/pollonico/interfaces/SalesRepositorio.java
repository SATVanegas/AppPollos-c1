package com.intfinity.myapi.pollonico.interfaces;

import com.intfinity.myapi.pollonico.Help.Help;
import com.intfinity.myapi.pollonico.Models.Customer;
import com.intfinity.myapi.pollonico.Models.Sales;
import com.intfinity.myapi.pollonico.Util.ConexionDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.intfinity.myapi.pollonico.Controllers.PrincipalController.sl;
public class SalesRepositorio implements RepositorioGenerico <Sales>{

    private Connection getConnection() throws SQLException {
        return ConexionDataBase.getInstance();
    }
    @Override
    public List<Sales> findAll() {
        List<Sales> sales = new ArrayList<>();
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery("SELECT customer.id, customer.direccion, customer.telefono, customer.nombre," +
                " sales.sales_id, sales.monto, sales.peso_total," +
                " sales.cantidad, sales.peso_total, sales.fecha, " +
                "sales.estado_entregado FROM sales INNER JOIN customer ON customer.id = sales.id_customer")) {

            while (resultSet.next()){
                Sales sale = getSales(resultSet);
                sales.add(sale);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sales;
    }

    @Override
    public void searchById(Integer id) {
        Sales sales = new Sales();

        String query = "SELECT customer.id, customer.direccion, customer.telefono," +
                " customer.nombre, sales.sales_id, sales.monto, sales.peso_total, sales.cantidad," +
                " sales.peso_total, sales.fecha, sales.estado_entregado FROM sales INNER JOIN customer " +
                "ON customer.id = sales.id_customer WHERE sales_id = ?;";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sales = getSales(rs);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (sales.hasNullFields()){
            Help.displayMessage("Gasto no encontrado"
                    ,"verifica que se encunetre bien escrito",
                    "El gasto con el id" + id+ " no existe");
        }else {
            rObjectId(sales);
        }
    }

    @Override
    public void rObjectId(Sales sales) {
       sl = sales;
    }


    @Override
    public void save(Sales sales) {
        String sql = "";

        if (sales.getId() != null && sales.getId() > 0) {
            sql = "UPDATE sales SET cantidad=?, id_customer=?, monto=?, peso_total=?, estado_entregado=?, fecha=? WHERE sales_id=?";
        }else {
            sql = "INSERT INTO sales(id_customer, monto, cantidad, peso_total, estado_entregado, fecha) VALUES (?, ?, ?, ?, ?, ?)";
        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            if (sales.getId() != null && sales.getId() > 0){
                stmt.setInt(7, sales.getId());
                stmt.setInt(1, sales.getCantidad());
                stmt.setInt(2, sales.getCustomer().getId());
                stmt.setDouble(3, sales.getMonto());
                stmt.setDouble(4, sales.getPeso());
                stmt.setBoolean(5, sales.isEstado());
                stmt.setDate(6, sales.getFechaCompra());
            }else {

                stmt.setInt(1, sales.getCustomer().getId());
                stmt.setInt(3, sales.getCantidad());
                stmt.setDouble(2, sales.getMonto());
                stmt.setDouble(4, sales.getPeso());
                stmt.setBoolean(5, sales.isEstado());
                stmt.setDate(6, sales.getFechaCompra());
            }
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Integer id) {
        try (PreparedStatement pr = getConnection().prepareStatement("DELETE FROM sales WHERE sales_id = ?")) {
            pr.setInt(1,id);
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Sales getSales(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        Sales sales = new Sales();
        sales.setId(resultSet.getInt("sales_id"));
        sales.setCantidad(resultSet.getInt("cantidad"));
        customer.setName(resultSet.getString("nombre"));
        customer.setAddress(resultSet.getString("direccion"));
        customer.setPhone(resultSet.getString("telefono"));
        customer.setId(resultSet.getInt("id"));
        sales.setCustomer(customer);
        sales.setMonto(resultSet.getDouble("monto"));
        sales.setPeso(resultSet.getDouble("peso_total"));
        sales.setEstado(resultSet.getBoolean("estado_entregado"));
        sales.setFechaCompra(resultSet.getDate("fecha"));
        return sales;
    }
}
