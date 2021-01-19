package com.zizoh.ulesson.dashboard.presentation.chapter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zizoh.ulesson.dashboard.presentation.ChapterStateMachine
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewIntent
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewState
import com.zizoh.ulesson.presentation.mvi.MVIPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

/**
 * Created by zizoh on 19/January/2021.
 */

class ChapterViewModel @ViewModelInject constructor(
    private val chapterStateMachine: ChapterStateMachine
) : ViewModel(), MVIPresenter<ChapterViewState, ChapterViewIntent> {

    override val viewState: Flow<ChapterViewState>
        get() = chapterStateMachine.viewState

    init {
        chapterStateMachine.processor.launchIn(viewModelScope)
    }

    override fun processIntent(intents: Flow<ChapterViewIntent>) {
        chapterStateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}