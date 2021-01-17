package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.dashboard.presentation.DashboardIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult.SubjectsResult
import com.zizoh.ulesson.domain.usecase.base.GetSubjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class DashboardViewIntentProcessor @Inject constructor(
    private val getSubjects: GetSubjects
) : DashboardIntentProcessor {

    override fun intentToResult(viewIntent: DashboardViewIntent): Flow<DashboardViewResult> {
        return when (viewIntent) {
            SubjectViewIntent.LoadSubjects -> loadSubjects()
            RecentTopicsViewIntent.LoadMostRecentTopics -> TODO()
            RecentTopicsViewIntent.LoadAllRecentTopics -> TODO()
        }
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
}