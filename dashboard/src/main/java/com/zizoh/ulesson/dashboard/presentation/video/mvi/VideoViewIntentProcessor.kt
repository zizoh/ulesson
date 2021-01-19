package com.zizoh.ulesson.dashboard.presentation.video.mvi

import com.zizoh.ulesson.dashboard.presentation.VideoIntentProcessor
import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.usecase.GetLesson
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class VideoViewIntentProcessor @Inject constructor(
    private val getLesson: GetLesson
) : VideoIntentProcessor {

    override fun intentToResult(viewIntent: VideoViewIntent): Flow<VideoViewResult> {
        return when (viewIntent) {
            VideoViewIntent.Idle -> flowOf(VideoViewResult.Idle)
            is VideoViewIntent.LoadLesson -> {
                getLesson(viewIntent.lessonId).map<Lesson, VideoViewResult> {
                    VideoViewResult.Loaded(it)
                }.onStart {
                    VideoViewResult.Loading
                }.catch { error ->
                    error.printStackTrace()
                    emit(VideoViewResult.Error(error))
                }
            }
        }
    }

}