package com.zizoh.ulesson.dashboard.di

import android.content.Context
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by zizoh on 20/January/2021.
 */

@InstallIn(ApplicationComponent::class)
@Module
class MediaPlayerModule {

    companion object {
        @Provides
        fun providesExoPlayer(@ApplicationContext context: Context): SimpleExoPlayer {
            return ExoPlayerFactory.newSimpleInstance(context)
        }

        @Provides
        fun providesDefaultBandwidthMeter(@ApplicationContext context: Context): DefaultBandwidthMeter {
            return DefaultBandwidthMeter.Builder(context).build()
        }
    }

}