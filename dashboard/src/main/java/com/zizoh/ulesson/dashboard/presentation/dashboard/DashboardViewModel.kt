package com.zizoh.ulesson.dashboard.presentation.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zizoh.ulesson.dashboard.presentation.DashboardStateMachine
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.RecentTopicsViewState
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState.SubjectsViewState
import com.zizoh.ulesson.presentation.mvi.MVIPresenter
import kotlinx.coroutines.flow.*

/**
 * Created by zizoh on 16/January/2021.
 */

class DashboardViewModel @ViewModelInject constructor(
    private val dashboardStateMachine: DashboardStateMachine
) : ViewModel(), MVIPresenter<DashboardViewState, DashboardViewIntent> {

    private val dashboardViewState: MutableStateFlow<DashboardViewState> =
        MutableStateFlow(DashboardViewState.Idle)

    private val subjectsViewState: MutableStateFlow<SubjectsViewState> =
        MutableStateFlow(SubjectsViewState.Loading)

    private val recentTopicsViewState: MutableStateFlow<RecentTopicsViewState> =
        MutableStateFlow(RecentTopicsViewState.LoadingMostRecentWatchedTopics)

    override val viewState: Flow<DashboardViewState>
        get() = merge(
            dashboardViewState,
            subjectsViewState,
            recentTopicsViewState
        )

    init {
        dashboardStateMachine.processor.launchIn(viewModelScope)
        makeViewState()
    }

    private fun makeViewState() {
        dashboardStateMachine.viewState.onEach { state ->
            when (state) {
                is SubjectsViewState -> subjectsViewState.value = state
                is RecentTopicsViewState -> recentTopicsViewState.value = state
                else -> dashboardViewState.value = state
            }
        }.launchIn(viewModelScope)
    }

    override fun processIntent(intents: Flow<DashboardViewIntent>) {
        dashboardStateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}