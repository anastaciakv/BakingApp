package de.proximity.bakeme.items;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class StepTest {
    @Test
    public void hasVideo() throws Exception {
        Step step = new Step();
        step.videoURL = null;
        assertFalse(step.hasVideo());
        step.videoURL = "";
        assertFalse(step.hasVideo());
        step.videoURL = "http...";
        assertTrue(step.hasVideo());
    }

}