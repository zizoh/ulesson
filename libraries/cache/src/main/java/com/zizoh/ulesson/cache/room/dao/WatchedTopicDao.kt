package com.zizoh.ulesson.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zizoh.ulesson.cache.models.WatchedTopicCacheModel
import com.zizoh.ulesson.cache.models.WatchedTopicDatabaseModel

/**
 * Created by zizoh on 17/January/2021.
 */

@Dao
interface WatchedTopicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWatchedTopic(topicCacheModel: WatchedTopicDatabaseModel)

    @Query("SELECT watched_topics.id AS id, lessons.name AS name, lessons.icon AS icon, subjects.name AS subjectName, watched_topics.watchedDate AS watchedDate FROM watched_topics , lessons, subjects LIMIT 2")
    suspend fun getMostRecentWatchedTopics(): List<WatchedTopicCacheModel>

    @Query("SELECT watched_topics.id AS id, lessons.name AS name, lessons.icon AS icon, subjects.name AS subjectName, watched_topics.watchedDate AS watchedDate FROM watched_topics, lessons, subjects")
    suspend fun getAllRecentWatchedTopics(): List<WatchedTopicCacheModel>
}