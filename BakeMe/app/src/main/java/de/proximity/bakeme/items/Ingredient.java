package de.proximity.bakeme.items;

import org.parceler.Parcel;

import io.realm.RealmObject;

@Parcel
public class Ingredient extends RealmObject {
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
