package com.intfinity.myapi.pollonico.Models;

import java.util.Date;

public class Sales {

    private Integer id,cantidad;
    private Customer customer;
    private double monto,peso;
    private boolean estado;
    private Date fechaCompra;

    public Sales(Integer id, Integer cantidad, Customer customer, double monto, double peso, boolean estado, Date fechaCompra) {
        this.id = id;
        this.cantidad = cantidad;
        this.customer = customer;
        this.monto = monto;
        this.peso = peso;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    public Sales(Integer cantidad, Customer customer, double monto, double peso, boolean estado, Date fechaCompra) {
        this.cantidad = cantidad;
        this.customer = customer;
        this.monto = monto;
        this.peso = peso;
        this.estado = estado;
        this.fechaCompra = fechaCompra;
    }

    public Sales(Integer id) {
        this.id = id;
    }

    public Sales(Customer customer) {
        this.customer = customer;
    }

    public Sales() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public java.sql.Date getFechaCompra() {
        return (java.sql.Date) fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", customer=" + customer +
                ", monto=" + monto +
                ", peso=" + peso +
                ", estado=" + estado +
                ", fechaCompra=" + fechaCompra +
                '}';
    }
}
