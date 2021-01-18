package com.zizoh.ulesson.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel

/**
 * Created by zizoh on 17/January/2021.
 */

@Dao
interface WatchedTopicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWatchedTopic(topicCacheModel: WatchedTopicCacheModel)

    @Query("SELECT * FROM watched_topics")
    suspend fun getMostRecentWatchedTopics(): List<WatchedTopicCacheModel>

    @Query("SELECT * FROM watched_topics")
    suspend fun getAllRecentWatchedTopics(): List<WatchedTopicCacheModel>
}