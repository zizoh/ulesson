package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.domain.models.Subject
import com.zizoh.ulesson.domain.models.WatchedTopic
import com.zizoh.ulesson.presentation.mvi.ViewResult

/**
 * Created by zizoh on 15/January/2021.
 */

sealed class DashboardViewResult : ViewResult {
    sealed class SubjectsResult : DashboardViewResult() {
        object Loading: SubjectsResult()
        object Empty : SubjectsResult()
        data class Error(val throwable: Throwable) : SubjectsResult()
        data class Success(val subjects: List<Subject>) : SubjectsResult()
    }

    sealed class WatchedTopicsResult : DashboardViewResult() {
        object LoadingMostRecentWatchedTopics: WatchedTopicsResult()
        object Empty : WatchedTopicsResult()
        data class MostRecentWatchedTopicsLoaded(val lessons: List<WatchedTopic>) : WatchedTopicsResult()
        object LoadingAllWatchedTopics: WatchedTopicsResult()
        data class AllWatchedTopicsLoaded(val lessons: List<WatchedTopic>) : WatchedTopicsResult()
    }
}
