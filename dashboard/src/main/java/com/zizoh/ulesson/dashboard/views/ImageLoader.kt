package com.zizoh.ulesson.dashboard.views

import android.widget.ImageView

/**
 * Created by zizoh on 19/January/2021.
 */

interface ImageLoader {

    fun loadImage(url: String, imageView: ImageView, curvature: Int)
}