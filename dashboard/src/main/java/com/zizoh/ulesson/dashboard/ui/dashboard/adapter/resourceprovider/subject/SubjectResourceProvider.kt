package com.zizoh.ulesson.dashboard.ui.dashboard.adapter.resourceprovider.subject

import android.graphics.drawable.Drawable

/**
 * Created by zizoh on 16/January/2021.
 */

interface SubjectResourceProvider {

    fun getBackgroundDrawable(): Drawable?

    fun getColor(): Int
}

object SubjectName {
    const val BIOLOGY = "Biology"
    const val CHEMISTRY = "Chemistry"
    const val ENGLISH = "English"
    const val MATHEMATICS = "Mathematics"
    const val PHYSICS = "Physics"
}
