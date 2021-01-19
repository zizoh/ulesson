package com.zizoh.ulesson.dashboard.presentation.chapter.mvi

import com.zizoh.ulesson.presentation.mvi.ViewIntent

/**
 * Created by zizoh on 19/January/2021.
 */

sealed class ChapterViewIntent : ViewIntent {
    object Idle : ChapterViewIntent()
    data class LoadChapters(val subjectId: Int) : ChapterViewIntent()
}