package com.zizoh.ulesson.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zizoh on 17/January/2021.
 */

@Entity(tableName = "watched_topics")
data class WatchedTopicCacheModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val subjectName: String,
    val chapterId: Int,
    val watchedDate: Long
)