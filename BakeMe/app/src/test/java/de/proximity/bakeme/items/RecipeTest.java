package de.proximity.bakeme.items;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;

public class RecipeTest {
    Recipe recipe;

    @Before
    public void setUp() throws Exception {
        recipe = new Recipe();
    }

    @Test
    public void given_recipeWithNullIngredients_when_getIngredientsAsString_then_returnEmptyString() throws Exception {
        assertEquals("", recipe.getIngredientsAsString());
    }

    @Test
    public void given_recipeWithEmptyIngredients_when_getIngredientsAsString_then_returnEmptyString() throws Exception {
        recipe.ingredients = new ArrayList<>();
        assertEquals("", recipe.getIngredientsAsString());
    }

}