package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.dashboard.presentation.models.LessonModel
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
        data class LessRecentTopicsLoaded(val lessons: List<LessonModel>) : RecentTopicsViewState()
        data class MoreRecentTopicsLoaded(val lessons: List<LessonModel>) : RecentTopicsViewState()
        object RecentTopicsEmpty : RecentTopicsViewState()
    }
}
