package com.zizoh.ulesson.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zizoh.ulesson.cache.BuildConfig
import com.zizoh.ulesson.cache.models.ChapterCacheModel
import com.zizoh.ulesson.cache.models.LessonCacheModel
import com.zizoh.ulesson.cache.models.SubjectCacheModel
import com.zizoh.ulesson.cache.models.WatchedTopicDatabaseModel
import com.zizoh.ulesson.cache.room.dao.ChapterDao
import com.zizoh.ulesson.cache.room.dao.LessonDao
import com.zizoh.ulesson.cache.room.dao.SubjectDao
import com.zizoh.ulesson.cache.room.dao.WatchedTopicDao

/**
 * Created by zizoh on 18/January/2021.
 */

@Database(
    entities = [
        ChapterCacheModel::class,
        LessonCacheModel::class,
        SubjectCacheModel::class,
        WatchedTopicDatabaseModel::class
    ],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class ULessonDatabase : RoomDatabase() {

    abstract val chapterDao: ChapterDao

    abstract val lessonDao: LessonDao

    abstract val subjectDao: SubjectDao

    abstract val watchedTopicDao: WatchedTopicDao

    companion object {
        private const val DATABASE_NAME: String = "ulesson_db"
        fun build(context: Context): ULessonDatabase = Room.databaseBuilder(
            context.applicationContext,
            ULessonDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
