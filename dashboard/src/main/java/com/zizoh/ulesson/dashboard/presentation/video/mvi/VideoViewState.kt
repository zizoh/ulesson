package com.zizoh.ulesson.dashboard.presentation.video.mvi

import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
import com.zizoh.ulesson.presentation.mvi.ViewState

/**
 * Created by zizoh on 19/January/2021.
 */

sealed class VideoViewState : ViewState {
    object Idle : VideoViewState()
    object Loading : VideoViewState()
    data class LessonLoaded(val lesson: LessonModel) : VideoViewState()
    data class Error(val message: String) : VideoViewState()
}