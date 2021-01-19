package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.presentation.mvi.ViewIntent

/**
 * Created by zizoh on 15/January/2021.
 */

sealed class DashboardViewIntent : ViewIntent
object LoadData : DashboardViewIntent()
sealed class SubjectViewIntent : DashboardViewIntent() {
    object LoadSubjects : SubjectViewIntent()
}

sealed class RecentTopicsViewIntent : DashboardViewIntent() {
    object LoadMostRecentTopics : RecentTopicsViewIntent()
    object LoadAllRecentTopics : RecentTopicsViewIntent()
}