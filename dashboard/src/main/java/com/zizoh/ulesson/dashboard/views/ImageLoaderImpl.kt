package com.zizoh.ulesson.dashboard.views

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
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

    override fun loadImage(url: String, textView: TextView) {
        picasso.load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val drawable = BitmapDrawable(textView.context.resources, bitmap)
                textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }
        })
    }
}