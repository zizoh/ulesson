package com.zizoh.ulesson.dashboard.di

import com.zizoh.ulesson.dashboard.presentation.DashboardIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.DashboardStateMachine
import com.zizoh.ulesson.dashboard.presentation.DashboardStateReducer
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewStateMachine
import com.zizoh.ulesson.dashboard.presentation.dashboard.mvi.DashboardViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by zizoh on 17/January/2021.
 */

@InstallIn(ActivityRetainedComponent::class)
@Module
interface DashboardModule {

    @get:Binds
    val DashboardViewIntentProcessor.intentProcessor: DashboardIntentProcessor

    @get:Binds
    val DashboardViewStateReducer.reducer: DashboardStateReducer

    @get:Binds
    val DashboardViewStateMachine.stateMachine: DashboardStateMachine
}