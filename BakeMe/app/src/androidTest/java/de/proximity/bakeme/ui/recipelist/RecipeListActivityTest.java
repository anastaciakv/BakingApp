package de.proximity.bakeme.ui.recipelist;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.proximity.bakeme.R;
import de.proximity.bakeme.di.Injectable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest implements Injectable {
    @Rule
    public ActivityTestRule<RecipeListActivity> activityTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    //1. Find the view
    //2. Perform action on the view
    //3. Check if the view does what I expect

    @Test
    public void recipeListIsFilledWithData() throws Exception {
        onView(withId(R.id.rvRecipeList))
                .check(matches(hasDescendant(withText("Honeycomb"))));
        onView(withId(R.id.rvRecipeList))
                .check(matches(hasDescendant(withText("Oreo"))));
    }

    @Test
    public void whenClickRecipe_thenOpenDetails() throws Exception {


    }

}