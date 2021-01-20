package com.zizoh.ulesson.dashboard.presentation.video.mvi

import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.presentation.mvi.ViewResult

/**
 * Created by zizoh on 19/January/2021.
 */

sealed class VideoViewResult : ViewResult {
    object Idle : VideoViewResult()
    object Loading : VideoViewResult()
    data class Loaded(val lesson: Lesson) : VideoViewResult()
    data class Error(val throwable: Throwable) : VideoViewResult()
}