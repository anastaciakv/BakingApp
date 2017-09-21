package de.proximity.bakeme.ui.recipedetails;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import de.proximity.bakeme.items.Step;

import static junit.framework.Assert.assertEquals;

public class StepListAdapterTest {
    StepListAdapter adapter;
    @Mock
    StepListAdapter.StepCallback callback;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void given_nullStepsList_when_getCount_then_zero() throws Exception {
        adapter = new StepListAdapter(callback, null);
        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void given_StepsList_when_getCount_then_listSize() throws Exception {
        List<Step> stepList = new ArrayList<>();
        stepList.add(new Step());
        stepList.add(new Step());
        stepList.add(new Step());
        adapter = new StepListAdapter(callback, stepList);
        assertEquals(3, adapter.getItemCount());
    }

}