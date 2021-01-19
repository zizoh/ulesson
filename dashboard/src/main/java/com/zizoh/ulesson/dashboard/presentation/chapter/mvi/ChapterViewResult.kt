package com.zizoh.ulesson.dashboard.presentation.chapter.mvi

import com.zizoh.ulesson.domain.models.Chapter
import com.zizoh.ulesson.presentation.mvi.ViewResult

/**
 * Created by zizoh on 19/January/2021.
 */

sealed class ChapterViewResult : ViewResult {
    object Idle : ChapterViewResult()
    object Loading : ChapterViewResult()
    data class Loaded(val chapters: List<Chapter>) : ChapterViewResult()
    data class Error(val throwable: Throwable) : ChapterViewResult()
}