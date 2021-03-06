package de.proximity.bakeme.ui.recipelist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import de.proximity.bakeme.items.Recipe;

import static junit.framework.Assert.assertEquals;

public class RecipeAdapterTest {
    RecipeAdapter adapter;
    @Mock
    RecipeAdapter.RecipeClickCallback callback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        adapter = new RecipeAdapter(callback);
    }

    @Test
    public void getItemCount() throws Exception {
        List<Recipe> newItems = new ArrayList<>();
        newItems.add(new Recipe());
        newItems.add(new Recipe());
        adapter.update(newItems);
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void update() throws Exception {
        List<Recipe> newItems = new ArrayList<>();
        adapter.update(newItems);
        assertEquals(newItems, adapter.recipes);
    }

    @Test
    public void ignoreUpdateWithNull() throws Exception {
        List<Recipe> expected = new ArrayList<>();
        adapter.recipes = expected;
        adapter.update(null);
        assertEquals(expected, adapter.recipes);
    }


}