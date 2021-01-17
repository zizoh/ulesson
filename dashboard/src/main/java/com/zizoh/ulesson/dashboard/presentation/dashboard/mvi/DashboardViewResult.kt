package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.domain.models.Lesson
import com.zizoh.ulesson.domain.models.Subject
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

    sealed class RecentTopicsResult : DashboardViewResult() {
        object Empty : RecentTopicsResult()
        data class LessTopicsLoaded(val lessons: List<Lesson>) : RecentTopicsResult()
        data class MoreTopicsLoaded(val lessons: List<Lesson>) : RecentTopicsResult()
    }
}
