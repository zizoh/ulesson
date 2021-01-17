package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.core.ext.errorMessage
import com.zizoh.ulesson.dashboard.presentation.DashboardStateReducer
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.RecentTopicsResult
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.SubjectsResult
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.RecentTopicsViewState
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.SubjectsViewState
import com.zizoh.ulesson.dashboard.presentation.mappers.LessonModelMapper
import com.zizoh.ulesson.dashboard.presentation.mappers.SubjectModelMapper
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class DashboardViewStateReducer @Inject constructor(
    private val subjectModelMapper: SubjectModelMapper,
    private val lessonModelMapper: LessonModelMapper
) : DashboardStateReducer {

    override fun reduce(
        previous: DashboardViewState,
        result: DashboardViewResult
    ): DashboardViewState {
        return when (result) {
            SubjectsResult.Loading -> SubjectsViewState.Loading
            SubjectsResult.Empty -> SubjectsViewState.SubjectsEmpty
            is SubjectsResult.Error -> SubjectsViewState.Error(result.throwable.errorMessage)
            is SubjectsResult.Success -> {
                SubjectsViewState.SubjectsLoaded(subjectModelMapper.mapToModelList(result.subjects))
            }
            RecentTopicsResult.Empty -> RecentTopicsViewState.RecentTopicsEmpty
            is RecentTopicsResult.LessTopicsLoaded -> {
                RecentTopicsViewState.LessRecentTopicsLoaded(lessonModelMapper.mapToModelList(result.lessons))
            }
            is RecentTopicsResult.MoreTopicsLoaded -> {
                RecentTopicsViewState.MoreRecentTopicsLoaded(lessonModelMapper.mapToModelList(result.lessons))
            }
        }
    }
}