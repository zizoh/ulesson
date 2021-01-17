package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.subject

import android.content.Context
import android.graphics.drawable.Drawable
import com.zizoh.ulesson.core.ext.getColorResId
import com.zizoh.ulesson.core.ext.getImage
import com.zizoh.ulesson.dashboard.R

/**
 * Created by zizoh on 16/January/2021.
 */

class ChemistryResourceProvider(private val context: Context) : SubjectResourceProvider {

    override fun getBackgroundDrawable(): Drawable? =
        context.getImage(R.drawable.subject_background_chemistry)

    override fun getColor(): Int = context.getColorResId(R.color.subject_chemistry)

}