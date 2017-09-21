package de.proximity.bakeme.ui.recipedetails.step;


import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.parceler.Parcels;

import de.proximity.bakeme.R;
import de.proximity.bakeme.databinding.FragmentStepBinding;
import de.proximity.bakeme.items.Step;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StepFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StepFragment extends Fragment {
    private static final String EXTRA_STEP = "step";

    private Step step;
    private SimpleExoPlayer player;
    private FragmentStepBinding binding;
    private MediaSource mediaSource;

    public StepFragment() {
    }

    public static StepFragment newInstance(Step step) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STEP, Parcels.wrap(step));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            step = Parcels.unwrap(getArguments().getParcelable(EXTRA_STEP));
            if (step.videoURL != null)
                setupPlayer();
        }
    }

    private void setupPlayer() {
        if (player == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
            mediaSource = new ExtractorMediaSource(Uri.parse(step.videoURL), new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step, container, false);
        binding.setStep(step);
        if (player != null) {
            binding.playerView.setPlayer(player);
            player.prepare(mediaSource);
            //    player.setPlayWhenReady(true);
        }
        return binding.getRoot();
    }
}