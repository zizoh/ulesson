package com.zizoh.ulesson.cache.di

import android.content.Context
import com.zizoh.ulesson.cache.impl.SubjectCacheImpl
import com.zizoh.ulesson.cache.room.ULessonDatabase
import com.zizoh.ulesson.cache.room.WatchedTopicDao
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by zizoh on 18/January/2021.
 */

@InstallIn(SingletonComponent::class)
@Module
interface CacheModule {

    @get:Binds
    val SubjectCacheImpl.subjectCache: SubjectCache

    companion object {
        @[Provides Singleton]
        fun provideDatabase(@ApplicationContext context: Context): ULessonDatabase {
            return ULessonDatabase.build(context)
        }

        @[Provides Singleton]
        fun provideHistoricalDataDao(database: ULessonDatabase): WatchedTopicDao {
            return database.watchedTopicDao
        }
    }
}