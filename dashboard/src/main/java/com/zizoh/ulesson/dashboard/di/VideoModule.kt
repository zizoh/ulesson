package com.zizoh.ulesson.dashboard.di

import com.zizoh.ulesson.dashboard.presentation.VideoIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.VideoStateMachine
import com.zizoh.ulesson.dashboard.presentation.VideoStateReducer
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewStateMachine
import com.zizoh.ulesson.dashboard.presentation.video.mvi.VideoViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by zizoh on 19/January/2021.
 */

@InstallIn(ActivityRetainedComponent::class)
@Module
interface VideoModule {

    @get:Binds
    val VideoViewIntentProcessor.intentProcessor: VideoIntentProcessor

    @get:Binds
    val VideoViewStateReducer.reducer: VideoStateReducer

    @get:Binds
    val VideoViewStateMachine.stateMachine: VideoStateMachine
}