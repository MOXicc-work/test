package com.ua.nure.entity;


import java.util.Objects;

public class Hat extends Headdress {
    private int depth;
    private String seasonality;

    public Hat() { }

    public Hat(String manufacturer, int price, String mainColor, ClothesSize clothesSize, int verticalCircumference, int depth, String seasonality) {
        super(manufacturer, price, mainColor, clothesSize, verticalCircumference);
        this.depth = depth;
        this.seasonality = seasonality;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getSeasonality() {
        return seasonality;
    }

    public void setSeasonality(String seasonality) {
        this.seasonality = seasonality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hat)) return false;
        if (!super.equals(o)) return false;
        Hat hat = (Hat) o;
        return depth == hat.depth && Objects.equals(seasonality, hat.seasonality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), depth, seasonality);
    }
}
