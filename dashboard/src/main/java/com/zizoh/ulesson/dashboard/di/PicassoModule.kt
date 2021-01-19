package com.zizoh.ulesson.dashboard.di

import com.squareup.picasso.Picasso
import com.zizoh.ulesson.dashboard.views.ImageLoader
import com.zizoh.ulesson.dashboard.views.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by zizoh on 19/January/2021.
 */

@InstallIn(ActivityComponent::class)
@Module
interface PicassoModule {

    @get:Binds
    val ImageLoaderImpl.imageLoader: ImageLoader

    companion object {
        @Provides
        fun providePicasso(): Picasso = Picasso.get()
    }
}