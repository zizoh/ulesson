package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.recenttopic

import android.content.Context
import android.graphics.drawable.Drawable
import com.zizoh.ulesson.core.ext.getColorResId
import com.zizoh.ulesson.core.ext.getImage
import com.zizoh.ulesson.dashboard.R

/**
 * Created by zizoh on 16/January/2021.
 */

class BiologyLessonResourceProvider(private val context: Context) : RecentTopicResourceProvider {

    override fun getPlayButtonDrawable(): Drawable? =
        context.getImage(R.drawable.play_button_biology)

    override fun getColor(): Int = context.getColorResId(R.color.subject_biology)

}