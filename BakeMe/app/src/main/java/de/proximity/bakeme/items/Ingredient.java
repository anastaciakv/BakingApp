package de.proximity.bakeme.items;

import org.parceler.Parcel;

@Parcel
public class Ingredient {
    public double quantity;
    public String measure;
    public String ingredient;

    public String getQuantity() {
        if (quantity % 1 == 0) {
            return String.valueOf((int) quantity);
        }
        return String.valueOf(quantity);
    }
}
