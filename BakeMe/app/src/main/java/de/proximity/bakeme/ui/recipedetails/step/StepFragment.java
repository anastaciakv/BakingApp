package de.proximity.bakeme.ui.recipedetails.step;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.proximity.bakeme.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepFragment extends Fragment {
    private static final String EXTRA_STEP = "step";

    private String step;

    public StepFragment() {
        // Required empty public constructor
    }

    public static StepFragment newInstance(String stepTitle) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_STEP, stepTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            step = getArguments().getString(EXTRA_STEP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_step, container, false);
        ((TextView) v.findViewById(R.id.tvStepTitle)).setText(step);
        return v;
    }

}
