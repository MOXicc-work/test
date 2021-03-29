package com.ua.nure.entity;

import java.util.Objects;

public abstract class Product {

    private static int idGenerator = 0;

    private final int id;
    private String manufacturer;
    private int price;


    protected Product() {
        id = idGenerator++;
    }

    protected Product(String manufacturer, int price) {
        this();
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product goods = (Product) o;
        return price == goods.price && Objects.equals(manufacturer, goods.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, price);
    }

    @Override
    public String toString() {
        return  "Price: " + price + System.lineSeparator() +
                "Manufacturer: " + manufacturer + System.lineSeparator();
    }
}
