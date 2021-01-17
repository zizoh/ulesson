package com.zizoh.ulesson.dashboard.presentation

import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntent
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewResult
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewState
import com.zizoh.ulesson.presentation.mvi.IntentProcessor
import com.zizoh.ulesson.presentation.mvi.StateMachine
import com.zizoh.ulesson.presentation.mvi.ViewStateReducer

/**
 * Created by zizoh on 15/January/2021.
 */

typealias DashboardIntentProcessor =
        @JvmSuppressWildcards IntentProcessor<DashboardViewIntent, DashboardViewResult>

typealias DashboardStateReducer =
        @JvmSuppressWildcards ViewStateReducer<DashboardViewState, DashboardViewResult>

typealias DashboardStateMachine =
        @JvmSuppressWildcards StateMachine<DashboardViewIntent, DashboardViewState, DashboardViewResult>
