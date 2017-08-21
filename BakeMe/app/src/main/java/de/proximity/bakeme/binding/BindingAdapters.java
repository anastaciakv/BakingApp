package de.proximity.bakeme.binding;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        if (url == null || url.isEmpty()) return;
        Picasso.with(imageView.getContext())
                .load(url)
                .error(android.R.drawable.stat_notify_error)
                .into(imageView);
    }

    @BindingAdapter("fadeVisibleGone")
    public static void setFadeVisible(View view, boolean visible) {
        animateVisibility(view, visible, View.GONE);
    }

    private static void animateVisibility(final View view, boolean visible, final int hideVisibility) {
        if (view.getTag() == null) {
            view.setTag(true);
        } else {
            view.animate().cancel();
        }
        if (visible) {
            view.setVisibility(View.VISIBLE);
            view.animate().setDuration(300).alpha(1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setAlpha(1);
                }
            });

        } else {
            view.setAlpha(1);
            view.setVisibility(View.VISIBLE);
            view.animate().setDuration(300).alpha(0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setAlpha(0);
                    view.setVisibility(hideVisibility);
                }
            });
        }

    }
}
