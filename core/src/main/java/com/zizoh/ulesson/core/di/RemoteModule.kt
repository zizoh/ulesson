package com.zizoh.ulesson.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.zizoh.ulesson.core.BuildConfig
import com.zizoh.ulesson.data.contract.remote.SubjectRemote
import com.zizoh.ulesson.remote.ApiService
import com.zizoh.ulesson.remote.ApiServiceFactory
import com.zizoh.ulesson.remote.impl.SubjectRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by zizoh on 17/January/2021.
 */

@InstallIn(SingletonComponent::class)
@Module
interface RemoteModule {

    @get:[Binds Singleton]
    val SubjectRemoteImpl.bindSubjectRemote: SubjectRemote

    companion object {
        @get:[Provides Singleton]
        val provideMoshi: Moshi
            get() = Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()

        @[Provides Singleton]
        fun provideApiService(moshi: Moshi): ApiService =
            ApiServiceFactory.createApiService(BuildConfig.DEBUG, moshi)
    }
}