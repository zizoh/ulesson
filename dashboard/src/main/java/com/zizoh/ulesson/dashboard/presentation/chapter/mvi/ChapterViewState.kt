package com.zizoh.ulesson.dashboard.presentation.chapter.mvi

import com.zizoh.ulesson.dashboard.presentation.models.ChapterModel
import com.zizoh.ulesson.presentation.mvi.ViewState

/**
 * Created by zizoh on 19/January/2021.
 */

sealed class ChapterViewState : ViewState {
    object Idle : ChapterViewState()
    object Loading : ChapterViewState()
    data class ChaptersLoaded(val chapters: List<ChapterModel>) : ChapterViewState()
    data class Error(val message: String) : ChapterViewState()
}