package com.zizoh.ulesson.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zizoh.ulesson.cache.BuildConfig
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel

/**
 * Created by zizoh on 18/January/2021.
 */

@Database(
    entities = [
        WatchedTopicCacheModel::class
    ],
    version = BuildConfig.databaseVersion,
    exportSchema = false
)
abstract class ULessonDatabase : RoomDatabase() {

    abstract val watchedTopicDao: WatchedTopicDao

    companion object {
        private const val DATABASE_NAME: String = "ulesson_db"
        fun build(context: Context): ULessonDatabase = Room.databaseBuilder(
            context.applicationContext,
            ULessonDatabase::class.java, DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}
