package com.alexsukharev.beerapp.ui.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DataBindingAdapters {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(final ImageView imageView, final String url) {
        if (url != null) {
            Picasso.with(imageView.getContext())
                    .load(url)
                    .fit()
                    .centerInside()
                    .into(imageView);
        } else {
            imageView.setImageDrawable(null);
        }
    }
}
