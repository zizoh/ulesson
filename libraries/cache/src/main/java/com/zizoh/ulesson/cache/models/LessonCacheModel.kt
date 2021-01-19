package com.zizoh.ulesson.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zizoh on 18/January/2021.
 */

@Entity(tableName = "lessons")
data class LessonCacheModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val icon: String,
    val mediaUrl: String,
    val subjectId: Int,
    val chapterId: Int
)