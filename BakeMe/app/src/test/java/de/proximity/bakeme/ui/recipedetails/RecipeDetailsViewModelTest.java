package de.proximity.bakeme.ui.recipedetails;

import org.junit.Before;
import org.junit.Test;

import de.proximity.bakeme.items.Recipe;

import static junit.framework.Assert.assertEquals;

public class RecipeDetailsViewModelTest {
    RecipeDetailsViewModel model;

    @Before
    public void setUp() throws Exception {
        model = new RecipeDetailsViewModel();
    }

    @Test
    public void setsRecipe() {
        Recipe expected = new Recipe();
        model.setRecipe(expected);
        assertEquals(expected, model.recipe.get());
    }

}