package com.ua.nure.entity;

import java.util.Objects;

public class Glasses extends Accessory{
   private String lens;
   private String rim;

   public Glasses() { }

   public Glasses(String manufacturer, int price, String material, String lens, String rim) {
      super(manufacturer, price, material);
      this.lens = lens;
      this.rim = rim;
   }

   public String getLens() {
      return lens;
   }

   public void setLens(String lens) {
      this.lens = lens;
   }

   public String getRim() {
      return rim;
   }

   public void setRim(String rim) {
      this.rim = rim;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Glasses)) return false;
      if (!super.equals(o)) return false;
      Glasses glasses = (Glasses) o;
      return Objects.equals(lens, glasses.lens) && Objects.equals(rim, glasses.rim);
   }

   @Override
   public int hashCode() {
      return Objects.hash(super.hashCode(), lens, rim);
   }

   @Override
   public String toString() {
      return  "Lens: " + lens + System.lineSeparator() +
              "Rim: " + rim + System.lineSeparator() +
              super.toString();
   }
}

