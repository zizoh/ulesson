package com.zizoh.ulesson.cache.di

import android.content.Context
import com.zizoh.ulesson.cache.impl.ChapterCacheImpl
import com.zizoh.ulesson.cache.impl.LessonCacheImpl
import com.zizoh.ulesson.cache.impl.SubjectCacheImpl
import com.zizoh.ulesson.cache.impl.WatchedTopicCacheImpl
import com.zizoh.ulesson.cache.room.ULessonDatabase
import com.zizoh.ulesson.cache.room.dao.ChapterDao
import com.zizoh.ulesson.cache.room.dao.LessonDao
import com.zizoh.ulesson.cache.room.dao.SubjectDao
import com.zizoh.ulesson.cache.room.dao.WatchedTopicDao
import com.zizoh.ulesson.data.contract.cache.ChapterCache
import com.zizoh.ulesson.data.contract.cache.LessonCache
import com.zizoh.ulesson.data.contract.cache.SubjectCache
import com.zizoh.ulesson.data.contract.cache.WatchedTopicCache
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
    val ChapterCacheImpl.chapterCache: ChapterCache

    @get:Binds
    val LessonCacheImpl.lessonCacheImpl: LessonCache

    @get:Binds
    val SubjectCacheImpl.subjectCacheImpl: SubjectCache

    @get:Binds
    val WatchedTopicCacheImpl.watchedTopicCache: WatchedTopicCache

    companion object {
        @[Provides Singleton]
        fun provideDatabase(@ApplicationContext context: Context): ULessonDatabase {
            return ULessonDatabase.build(context)
        }

        @[Provides Singleton]
        fun provideChapterDao(database: ULessonDatabase): ChapterDao {
            return database.chapterDao
        }

        @[Provides Singleton]
        fun provideLessonDao(database: ULessonDatabase): LessonDao {
            return database.lessonDao
        }

        @[Provides Singleton]
        fun provideSubjectDao(database: ULessonDatabase): SubjectDao {
            return database.subjectDao
        }

        @[Provides Singleton]
        fun provideHistoricalDataDao(database: ULessonDatabase): WatchedTopicDao {
            return database.watchedTopicDao
        }
    }
}