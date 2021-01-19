package com.zizoh.ulesson.dashboard.presentation.video.mvi

import com.zizoh.ulesson.presentation.mvi.ViewIntent

/**
 * Created by zizoh on 19/January/2021.
 */

sealed class VideoViewIntent : ViewIntent {
    object Idle : VideoViewIntent()
    data class LoadLesson(val lessonId: Int) : VideoViewIntent()
}