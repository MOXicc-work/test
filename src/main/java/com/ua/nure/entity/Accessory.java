package com.ua.nure.entity;

import java.util.Objects;

public abstract class Accessory extends Product {
    private String material;

    protected Accessory() {}
    protected Accessory(String manufacturer, int price, String material) {
        super(manufacturer, price);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accessory)) return false;
        if (!super.equals(o)) return false;
        Accessory accessory = (Accessory) o;
        return Objects.equals(material, accessory.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), material);
    }

    @Override
    public String toString() {
        return "Material: " + material + System.lineSeparator() +
                super.toString();
    }
}
