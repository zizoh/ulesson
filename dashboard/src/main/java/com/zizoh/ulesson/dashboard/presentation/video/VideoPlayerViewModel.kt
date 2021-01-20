package com.zizoh.ulesson.dashboard.presentation.video

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zizoh.ulesson.dashboard.presentation.VideoStateMachine
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewIntent
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewState
import com.zizoh.ulesson.presentation.mvi.MVIPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

/**
 * Created by zizoh on 19/January/2021.
 */

class VideoPlayerViewModel @ViewModelInject constructor(
    private val stateMachine: VideoStateMachine
) : ViewModel(), MVIPresenter<VideoViewState, VideoViewIntent> {

    override val viewState: Flow<VideoViewState>
        get() = stateMachine.viewState

    init {
        stateMachine.processor.launchIn(viewModelScope)
    }

    override fun processIntent(intents: Flow<VideoViewIntent>) {
        stateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}