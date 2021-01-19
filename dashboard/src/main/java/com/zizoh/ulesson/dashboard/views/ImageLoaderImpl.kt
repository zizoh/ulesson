package com.zizoh.ulesson.dashboard.views

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.zizoh.ulesson.dashboard.R
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class ImageLoaderImpl @Inject constructor(private val picasso: Picasso) : ImageLoader {

    override fun loadImage(url: String, imageView: ImageView, curvature: Int) {
        picasso.load(url)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.rectangle_rounded_corners_interest_placeholder)
            .transform(RoundedCornersTransformation(curvature, 0))
            .into(imageView)
    }
}