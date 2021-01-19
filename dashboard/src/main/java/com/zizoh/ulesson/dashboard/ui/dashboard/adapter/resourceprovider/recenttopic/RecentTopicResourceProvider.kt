package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.recenttopic

import android.graphics.drawable.Drawable

/**
 * Created by zizoh on 16/January/2021.
 */

interface RecentTopicResourceProvider {

    fun getPlayButtonDrawable(): Drawable?

    fun getColor(): Int
}