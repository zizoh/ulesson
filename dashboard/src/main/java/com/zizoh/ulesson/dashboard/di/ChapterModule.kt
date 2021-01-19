package com.zizoh.ulesson.dashboard.di

import com.zizoh.ulesson.dashboard.presentation.ChapterIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.ChapterStateMachine
import com.zizoh.ulesson.dashboard.presentation.ChapterStateReducer
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewIntentProcessor
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewStateMachine
import com.zizoh.ulesson.dashboard.presentation.chapter.mvi.ChapterViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by zizoh on 17/January/2021.
 */

@InstallIn(ActivityRetainedComponent::class)
@Module
interface ChapterModule {

    @get:Binds
    val ChapterViewIntentProcessor.intentProcessor: ChapterIntentProcessor

    @get:Binds
    val ChapterViewStateReducer.reducer: ChapterStateReducer

    @get:Binds
    val ChapterViewStateMachine.stateMachine: ChapterStateMachine
}