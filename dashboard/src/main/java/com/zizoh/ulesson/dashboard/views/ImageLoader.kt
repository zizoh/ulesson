package com.zizoh.ulesson.dashboard.views

import android.widget.ImageView
import android.widget.TextView

/**
 * Created by zizoh on 19/January/2021.
 */

interface ImageLoader {

    fun loadImage(url: String, imageView: ImageView, curvature: Int)

    fun loadImage(url: String, textView: TextView)
}