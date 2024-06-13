package com.intfinity.myapi.pollonico.Models;

import java.util.Date;

public class Expenses {
    private Integer id;
    private String descripcion;
    private Date date;
    private double total;

    public Expenses(Integer id, String descripcion, Date date, double total) {
        this.id = id;
        this.descripcion = descripcion;
        this.date = date;
        this.total = total;
    }

    public Expenses(Integer id) {
        this.id = id;
    }

    public Expenses() {
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
    public boolean hasNullFields() {

         return id == null || descripcion == null || date == null;
    }

}
