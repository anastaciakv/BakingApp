package de.proximity.bakeme.ui.recipedetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import de.proximity.bakeme.R;
import de.proximity.bakeme.databinding.FragmentStepListBinding;
import de.proximity.bakeme.di.Injectable;

public class StepListFragment extends Fragment implements Injectable {
    @Inject
    RecipeDetailsViewModel model;

    private StepListAdapter.StepCallback callback;
    private FragmentStepListBinding binding;
    private StepListAdapter adapter;


    public StepListFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new StepListAdapter(callback, model.recipe.get().steps);
        binding.rvStepList.setHasFixedSize(true);
        binding.rvStepList.setAdapter(adapter);
        binding.setModel(model);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_list, container, false);
        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof StepListAdapter.StepCallback) {
            callback = (StepListAdapter.StepCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }
}
