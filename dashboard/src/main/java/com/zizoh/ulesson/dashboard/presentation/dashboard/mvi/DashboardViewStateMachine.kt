package com.zizoh.ulesson.dashboard.presentation.dashboard.mvi

import com.zizoh.ulesson.dashboard.presentation.DashboardStateMachine
import com.zizoh.ulesson.dashboard.presentation.DashboardIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.DashboardStateReducer
import javax.inject.Inject

/**
 * Created by zizoh on 15/January/2021.
 */

class DashboardViewStateMachine @Inject constructor(
    intentProcessor: DashboardIntentProcessor,
    reducer: DashboardStateReducer
) : DashboardStateMachine(
    intentProcessor,
    reducer,
    SubjectViewIntent.LoadSubjects,
    DashboardViewState.Idle
)