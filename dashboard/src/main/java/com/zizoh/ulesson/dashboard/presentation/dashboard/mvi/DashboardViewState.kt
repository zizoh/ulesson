package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.dashboard.presentation.models.WatchedTopicModel
import com.zizoh.ulesson.dashboard.presentation.models.SubjectModel
import com.zizoh.ulesson.presentation.mvi.ViewState

/**
 * Created by zizoh on 15/January/2021.
 */

sealed class DashboardViewState : ViewState {
    object Idle : DashboardViewState()
    sealed class SubjectsViewState : DashboardViewState() {
        object Loading : SubjectsViewState()
        data class SubjectsLoaded(val subjects: List<SubjectModel>) : SubjectsViewState()
        object SubjectsEmpty : SubjectsViewState()
        data class Error(val message: String) : SubjectsViewState()
    }

    sealed class RecentTopicsViewState : DashboardViewState() {
        object LoadingMostRecentWatchedTopics : RecentTopicsViewState()
        data class MostRecentWatchedTopicsLoaded(val lessons: List<WatchedTopicModel>) : RecentTopicsViewState()
        object RecentTopicsEmpty : RecentTopicsViewState()
        object LoadingAllWatchedTopics : RecentTopicsViewState()
        data class AllWatchedTopicsLoaded(val lessons: List<WatchedTopicModel>) : RecentTopicsViewState()
    }
}
