package com.ua.nure.entity;

import java.util.Objects;

public abstract class Clothes extends Product {
    private String mainColor;
    private ClothesSize size;

    protected Clothes() {}

    protected Clothes(String manufacturer, int price, String mainColor, ClothesSize clothesSize) {
        super(manufacturer, price);
        this.mainColor = mainColor;
        this.size = clothesSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clothes)) return false;
        if (!super.equals(o)) return false;
        Clothes clothes = (Clothes) o;
        return Objects.equals(mainColor, clothes.mainColor) && size == clothes.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mainColor, size);
    }

    @Override
    public String toString() {
        return  "Main color: " + mainColor + System.lineSeparator() +
                "Size: " + size + System.lineSeparator() +super.toString();
    }

    public enum ClothesSize {
        XS, S, M, L, XL, XXL;
    }
}
