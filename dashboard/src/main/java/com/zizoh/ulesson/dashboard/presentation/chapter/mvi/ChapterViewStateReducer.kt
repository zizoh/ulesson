package com.zizoh.ulesson.dashboard.presentation.chapter.mvi

import com.zizoh.ulesson.core.ext.errorMessage
import com.zizoh.ulesson.dashboard.presentation.ChapterStateReducer
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewState.*
import com.zizoh.ulesson.dashboard.presentation.mappers.ChapterModelMapper
import com.zizoh.ulesson.dashboard.presentation.models.ChapterModel
import javax.inject.Inject

/**
 * Created by zizoh on 19/January/2021.
 */

class ChapterViewStateReducer @Inject constructor(
    private val mapper: ChapterModelMapper
) : ChapterStateReducer {

    override fun reduce(previous: ChapterViewState, result: ChapterViewResult): ChapterViewState {
        return when (result) {
            ChapterViewResult.Idle -> Idle
            ChapterViewResult.Loading -> Loading
            is ChapterViewResult.Loaded -> {
                val chapters: List<ChapterModel> = mapper.mapToModelList(result.chapters)
                ChaptersLoaded(chapters)
            }
            is ChapterViewResult.Error -> Error(result.throwable.errorMessage)
        }
    }
}