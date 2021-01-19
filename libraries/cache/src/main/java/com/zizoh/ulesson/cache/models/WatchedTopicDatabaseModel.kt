package com.zizoh.ulesson.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zizoh on 17/January/2021.
 */

@Entity(tableName = "watched_topics")
data class WatchedTopicDatabaseModel(
    @PrimaryKey
    val id: Int,
    val subjectId: Int,
    val watchedDate: Long
)

data class WatchedTopicCacheModel(
    val id: Int,
    val name: String,
    val icon: String,
    val subjectName: String,
    val watchedDate: Long
)