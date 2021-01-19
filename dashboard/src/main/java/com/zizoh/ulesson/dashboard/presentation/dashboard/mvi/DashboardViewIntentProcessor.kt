package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.dashboard.presentation.DashboardIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.SubjectsResult
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.WatchedTopicsResult
import com.zizoh.ulesson.domain.usecase.GetAllWatchedTopics
import com.zizoh.ulesson.domain.usecase.GetMostRecentWatchedTopics
import com.zizoh.ulesson.domain.usecase.GetSubjects
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class DashboardViewIntentProcessor @Inject constructor(
    private val getSubjects: GetSubjects,
    private val getMostRecentWatchedTopics: GetMostRecentWatchedTopics,
    private val getAllWatchedTopics: GetAllWatchedTopics
) : DashboardIntentProcessor {

    override fun intentToResult(viewIntent: DashboardViewIntent): Flow<DashboardViewResult> {
        return when (viewIntent) {
            LoadData -> getData()
            SubjectViewIntent.LoadSubjects -> loadSubjects()
            RecentTopicsViewIntent.LoadMostRecentTopics -> loadMostRecentWatchedTopics()
            RecentTopicsViewIntent.LoadAllRecentTopics -> TODO()
        }
    }

    private fun getData(): Flow<DashboardViewResult> {
        return merge(loadSubjects(), loadMostRecentWatchedTopics())
    }

    private fun loadSubjects(): Flow<DashboardViewResult> {
        return getSubjects()
            .map { subjects ->
                if (subjects.isNotEmpty()) {
                    SubjectsResult.Success(subjects)
                } else {
                    SubjectsResult.Empty
                }
            }.onStart {
                emit(SubjectsResult.Loading)
            }.catch { error ->
                error.printStackTrace()
                emit(SubjectsResult.Error(error))
            }
    }

    private fun loadMostRecentWatchedTopics(): Flow<DashboardViewResult> {
        return getMostRecentWatchedTopics()
            .map { watchedTopics ->
                if (watchedTopics.isNotEmpty()) {
                    WatchedTopicsResult.MostRecentWatchedTopicsLoaded(watchedTopics)
                } else {
                    WatchedTopicsResult.Empty
                }
            }.onStart {
                emit(WatchedTopicsResult.LoadingMostRecentWatchedTopics)
            }.catch { error ->
                error.printStackTrace()
            }
    }
}