package com.ua.nure.entity;

import java.util.Objects;

public abstract class Footwear extends Clothes {

    private int footLength;
    private int soleHeight;
    private int weight;

    public Footwear(String manufacturer, int price, String mainColor, ClothesSize clothesSize, int footLength, int soleHeight, int weight) {
        super(manufacturer, price, mainColor, clothesSize);
        this.footLength = footLength;
        this.soleHeight = soleHeight;
        this.weight = weight;
    }

    protected Footwear() { }

    public int getFootLength() {
        return footLength;
    }

    public void setFootLength(int footLength) {
        this.footLength = footLength;
    }

    public int getSoleHeight() {
        return soleHeight;
    }

    public void setSoleHeight(int soleHeight) {
        this.soleHeight = soleHeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Footwear)) return false;
        if (!super.equals(o)) return false;
        Footwear footwear = (Footwear) o;
        return footLength == footwear.footLength && soleHeight == footwear.soleHeight && weight == footwear.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), footLength, soleHeight, weight);
    }

    @Override
    public String toString() {
        return  "Foot length: " + footLength + System.lineSeparator() +
                "Sole Height: " + soleHeight + System.lineSeparator() +
                "Weight: " + weight + System.lineSeparator() +
                super.toString();
    }
}
