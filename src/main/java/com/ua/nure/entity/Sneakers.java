package com.ua.nure.entity;


import java.util.Objects;

public class Sneakers extends Footwear {
    
    private FootFixing footFixing;
    private String insoleMaterial;

    public Sneakers() { }

    public Sneakers(String manufacturer, int price, String mainColor,
                    ClothesSize clothesSize, int footLength, int soleHeight,
                    int weight, FootFixing footFixing, String insoleMaterial) {
        super(manufacturer, price, mainColor, clothesSize, footLength, soleHeight, weight);
        this.footFixing = footFixing;
        this.insoleMaterial = insoleMaterial;
    }


    public FootFixing getFootFixing() {
        return footFixing;
    }

    public void setFootFixing(FootFixing footFixing) {
        this.footFixing = footFixing;
    }

    public String getInsoleMaterial() {
        return insoleMaterial;
    }

    public void setInsoleMaterial(String insoleMaterial) {
        this.insoleMaterial = insoleMaterial;
    }


    public enum FootFixing {
        NONE,
        LACING,
        VELCRO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sneakers)) return false;
        if (!super.equals(o)) return false;
        Sneakers sneakers = (Sneakers) o;
        return  footFixing == sneakers.footFixing &&
                Objects.equals(insoleMaterial, sneakers.insoleMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), footFixing, insoleMaterial);
    }

    @Override
    public String toString() {
        return "Foot fixing style: " + footFixing + System.lineSeparator() +
                "Insole material: " + insoleMaterial + System.lineSeparator() +
                super.toString();
    }
}
