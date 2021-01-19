package com.zizoh.ulesson.dashboard.presentation.chapter.mvi

import com.zizoh.ulesson.dashboard.presentation.ChapterIntentProcessor
import com.zizoh.ulesson.domain.models.Chapter
import com.zizoh.ulesson.domain.usecase.GetChaptersForSubject
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class ChapterViewIntentProcessor @Inject constructor(
    private val getChapters: GetChaptersForSubject
) : ChapterIntentProcessor {

    override fun intentToResult(viewIntent: ChapterViewIntent): Flow<ChapterViewResult> {
        return when (viewIntent) {
            ChapterViewIntent.Idle -> flowOf(ChapterViewResult.Idle)
            is ChapterViewIntent.LoadChapters -> {
                getChapters(viewIntent.subjectId).map<List<Chapter>, ChapterViewResult> {
                    ChapterViewResult.Loaded(it)
                }.onStart {
                    ChapterViewResult.Loading
                }.catch { error ->
                    error.printStackTrace()
                    emit(ChapterViewResult.Error(error))
                }
            }
        }
    }

}