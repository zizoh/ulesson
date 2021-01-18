package com.zizoh.ulesson.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by zizoh on 18/January/2021.
 */

@Entity(tableName = "chapters")
data class ChapterCacheModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val lessons: List<LessonCacheModel>
)