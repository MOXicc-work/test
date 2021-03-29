package com.ua.nure.entity;


import java.util.Objects;

public class Cap extends Headdress {

    private int visorLength;
    private String visorColor;
    private String visorMaterial;

    public Cap() {
    }

    public int getVisorLength() {
        return visorLength;
    }

    public void setVisorLength(int visorLength) {
        this.visorLength = visorLength;
    }

    public String getVisorColor() {
        return visorColor;
    }

    public void setVisorColor(String visorColor) {
        this.visorColor = visorColor;
    }

    public String getVisorMaterial() {
        return visorMaterial;
    }

    public void setVisorMaterial(String visorMaterial) {
        this.visorMaterial = visorMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cap))return false;
        if (!super.equals(o)) return false;
        Cap cap = (Cap) o;
        return visorLength == cap.visorLength &&
                Objects.equals(visorColor, cap.visorColor) &&
                Objects.equals(visorMaterial, cap.visorMaterial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), visorLength, visorColor, visorMaterial);
    }

    @Override
    public String toString() {
        return  "Visor length: " + visorLength + System.lineSeparator() +
                "Visor color: " + visorColor + System.lineSeparator() +
                "Visor material: " + visorMaterial + System.lineSeparator() +
                super.toString();
    }

}
