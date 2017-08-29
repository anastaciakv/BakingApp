package de.proximity.bakeme.items;


import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import de.proximity.bakeme.utils.RealmListIngredientParcelConverter;
import de.proximity.bakeme.utils.RealmListStepParcelConverter;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel()
public class Recipe extends RealmObject {
    @PrimaryKey
    public int id;
    public String name;
    @ParcelPropertyConverter(RealmListIngredientParcelConverter.class)
    public RealmList<Ingredient> ingredients;
    @ParcelPropertyConverter(RealmListStepParcelConverter.class)
    public RealmList<Step> steps;
    public int servings;
    public String image;
}
