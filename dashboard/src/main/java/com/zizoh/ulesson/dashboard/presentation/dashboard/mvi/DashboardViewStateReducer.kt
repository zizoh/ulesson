package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.core.ext.errorMessage
import com.zizoh.ulesson.dashboard.presentation.DashboardStateReducer
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.WatchedTopicsResult
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.SubjectsResult
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.RecentTopicsViewState
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.SubjectsViewState
import com.zizoh.ulesson.dashboard.presentation.mappers.LessonModelMapper
import com.zizoh.ulesson.dashboard.presentation.mappers.SubjectModelMapper
import com.zizoh.ulesson.dashboard.presentation.mappers.WatchedTopicModelMapper
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class DashboardViewStateReducer @Inject constructor(
    private val subjectModelMapper: SubjectModelMapper,
    private val lessonModelMapper: WatchedTopicModelMapper
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
            WatchedTopicsResult.Empty -> RecentTopicsViewState.RecentTopicsEmpty
            WatchedTopicsResult.LoadingMostRecentWatchedTopics -> {
                RecentTopicsViewState.LoadingMostRecentWatchedTopics
            }
            is WatchedTopicsResult.MostRecentWatchedTopicsLoaded -> {
                RecentTopicsViewState.MostRecentWatchedTopicsLoaded(lessonModelMapper.mapToModelList(result.lessons))
            }
            WatchedTopicsResult.LoadingAllWatchedTopics -> {
                RecentTopicsViewState.LoadingAllWatchedTopics
            }
            is WatchedTopicsResult.AllWatchedTopicsLoaded -> {
                RecentTopicsViewState.AllWatchedTopicsLoaded(lessonModelMapper.mapToModelList(result.lessons))
            }
        }
    }
}