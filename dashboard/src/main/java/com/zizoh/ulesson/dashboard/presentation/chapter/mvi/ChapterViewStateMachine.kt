package com.zizoh.ulesson.dashboard.presentation.chapter.mvi

import com.zizoh.ulesson.dashboard.presentation.ChapterIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.ChapterStateMachine
import com.zizoh.ulesson.dashboard.presentation.ChapterStateReducer
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class ChapterViewStateMachine @Inject constructor(
    intentProcessor: ChapterIntentProcessor,
    reducer: ChapterStateReducer
) : ChapterStateMachine(
    intentProcessor,
    reducer,
    ChapterViewIntent.Idle,
    ChapterViewState.Idle
)