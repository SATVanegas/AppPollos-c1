package com.intfinity.myapi.pollonico.Models;

public class Customer {
    private String name, phone, address;
    private Integer id;

    public Customer(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Customer(String name, String phone, String address, Integer id) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.id = id;
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
    public  boolean hasNullFields() {
        return id == null;
    }
}
