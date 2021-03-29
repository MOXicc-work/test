package com.ua.nure.entity;

import java.util.Objects;

public abstract class Headdress extends Clothes {

    private int verticalCircumference;

    protected Headdress() {
    }

    public Headdress(String manufacturer, int price, String mainColor, ClothesSize clothesSize, int verticalCircumference) {
        super(manufacturer, price, mainColor, clothesSize);
        this.verticalCircumference = verticalCircumference;
    }

    public int getVerticalCircumference() {
        return verticalCircumference;
    }

    public void setVerticalCircumference(int verticalCircumference) {
        this.verticalCircumference = verticalCircumference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Headdress)) return false;
        if (!super.equals(o)) return false;
        Headdress headdress = (Headdress) o;
        return verticalCircumference == headdress.verticalCircumference;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), verticalCircumference);
    }
}
