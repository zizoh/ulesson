package com.zizoh.ulesson.dashboard.presentation.video.mvi

import com.zizoh.ulesson.core.ext.errorMessage
import com.zizoh.ulesson.dashboard.presentation.VideoStateReducer
import com.zizoh.ulesson.dashboard.presentation.mappers.LessonModelMapper
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewState.*
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class VideoViewStateReducer @Inject constructor(
    private val mapper: LessonModelMapper
) : VideoStateReducer {

    override fun reduce(previous: VideoViewState, result: VideoViewResult): VideoViewState {
        return when (result) {
            VideoViewResult.Idle -> Idle
            VideoViewResult.Loading -> Loading
            is VideoViewResult.Loaded -> {
                val lesson = mapper.mapToModel(result.lesson)
                LessonLoaded(lesson)
            }
            is VideoViewResult.Error -> Error(result.throwable.errorMessage)
        }
    }
}