package com.zizoh.ulesson.dashboard.presentation.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zizoh.ulesson.dashboard.presentation.DashboardStateMachine
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState
import com.zizoh.ulesson.presentation.mvi.MVIPresenter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn

/**
 * Created by zizoh on 16/January/2021.
 */

class DashboardViewModel @ViewModelInject constructor(
    private val dashboardStateMachine: DashboardStateMachine
) : ViewModel(), MVIPresenter<DashboardViewState, DashboardViewIntent> {

    override val viewState: Flow<DashboardViewState>
        get() = dashboardStateMachine.viewState

    init {
        dashboardStateMachine.processor.launchIn(viewModelScope)
    }

    override fun processIntent(intents: Flow<DashboardViewIntent>) {
        dashboardStateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}