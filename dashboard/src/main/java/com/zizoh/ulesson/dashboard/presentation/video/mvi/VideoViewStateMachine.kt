package com.zizoh.ulesson.dashboard.presentation.video.mvi

import com.zizoh.ulesson.dashboard.presentation.VideoIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.VideoStateMachine
import com.zizoh.ulesson.dashboard.presentation.VideoStateReducer
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class VideoViewStateMachine @Inject constructor(
    intentProcessor: VideoIntentProcessor,
    reducer: VideoStateReducer
) : VideoStateMachine(
    intentProcessor,
    reducer,
    VideoViewIntent.Idle,
    VideoViewState.Idle
)