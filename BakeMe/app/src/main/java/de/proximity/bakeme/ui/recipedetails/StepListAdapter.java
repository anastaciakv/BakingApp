package de.proximity.bakeme.ui.recipedetails;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.proximity.bakeme.R;
import de.proximity.bakeme.databinding.StepItemBinding;
import de.proximity.bakeme.items.Step;
import timber.log.Timber;


public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ViewHolder> {
    private final StepCallback callback;
    List<Step> steps = new ArrayList<>();

    public StepListAdapter(StepCallback callback, List<Step> steps) {
        this.callback = callback;
        this.steps = steps != null ? steps : new ArrayList<Step>();
    }

    public interface StepCallback {
        void onStepClick(Step step);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Timber.d("onCreateViewHolder");
        final StepItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.step_item, parent, false);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onStepClick(binding.getStep());
            }
        });
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Timber.d("onBindViewHolder");
        holder.bind(steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final StepItemBinding binding;

        public ViewHolder(StepItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Step step) {
            binding.setStep(step);
        }
    }
}
